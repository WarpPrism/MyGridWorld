import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 8/22/15.
 */
public class IImageIO implements imagereader.IImageIO {
    @Override
    public Image myRead(String s) throws IOException {
        Image image = null;
        File srcimg = new File(s);
        System.out.println("Loading: " + srcimg.getName());
        try {
            FileInputStream fis = new FileInputStream(srcimg);
            DataInputStream dis = new DataInputStream(fis);
            int bflen = 14;
            byte[] bf = new byte[bflen];
            dis.read(bf, 0, bflen);
            int bilen = 40;
            byte[] bi = new byte[bilen];
            dis.read(bi, 0, bilen);

            int image_width=ChangeInt(bi,7);

            System.out.println("宽:" + image_width);

            int image_height=ChangeInt(bi,11);
            System.out.println("高:"+image_height);
            //位数
            int nbitcount=(((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
            System.out.println("位数:"+nbitcount);
            //源图大小
            int nsizeimage=ChangeInt(bi,23);
            System.out.println("图像大小:"+nsizeimage);
            //判断是否后面有补0 的情况
            int skip_width = 0;
            if(!(image_width * 3 % 4==0)){
                skip_width = 4 - image_width * 3 % 4;
            }
            //装载RGB颜色的数据数组
            int [][] imageR = new int[image_height][image_width];
            int [][] imageG = new int[image_height][image_width];
            int [][] imageB = new int[image_height][image_width];
            int[] data = new int[image_width * image_height];
            //按行读取 如果H,W为正则倒着来
            for (int h = image_height - 1; h >= 0; h--){
                for (int w = 0; w < image_width; w++){
                    //  读入三原色
                    int blue  = dis.read();
                    int green = dis.read();
                    int red = dis.read();
                    data[image_width * h + w] = 0xff << 24
                            | (red & 0xff) << 16
                            | (green & 0xff) << 8
                            | (blue & 0xff);
                    imageB[h][w]=blue;
                    imageG[h][w]=green;
                    imageR[h][w]=red;
                    if(w == image_width - 1){//跳过补0项
                        dis.skipBytes(skip_width);
                    }
                }
            }
            Toolkit kit = Toolkit.getDefaultToolkit();
            image = kit.createImage(new MemoryImageSource(image_width, image_height, data, 0, image_width));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Image myWrite(Image image, String s) throws IOException {
        BufferedImage img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        File f = new File(s);
        Graphics2D g2 = img.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        ImageIO.write(img, "bmp", f);
        return img;
    }

    public int ChangeInt(byte[] bi,int start){
        return (((int)bi[start]&0xff)<<24)
                | (((int)bi[start-1]&0xff)<<16)
                | (((int)bi[start-2]&0xff)<<8)
                | (int)bi[start-3]&0xff;
    }
}
