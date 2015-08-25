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
 * Open source,open mind.
 */
public class IImageIO implements imagereader.IImageIO {
    @Override
    public Image myRead(String s) throws IOException {
        Image image = null;
        File srcimg = new File(s);
        System.out.println("图片加载中: " + srcimg.getName());
        try {

            // The First Method Using Java API to load the Image
            /*FileInputStream fis = new FileInputStream(srcimg)
            // return BufferedImage
            return ImageIO.read(fis)*/

            // The Second Method Using datastream parsing the image.
            FileInputStream fis = new FileInputStream(srcimg);
            // 将文件流包装成一个可以写基本数据类型的输出流
            DataInputStream dis = new DataInputStream(fis);
            // Read Bitmap File Header.
            int bflen = 14;
            byte[] bf = new byte[bflen];
            dis.read(bf, 0, bflen);
            // Read Bitmap Info Header.
            int bilen = 40;
            byte[] bi = new byte[bilen];
            dis.read(bi, 0, bilen);

            // Get Image Information.
            int width=ChangeInt(bi,7);
            System.out.println("像素宽: " + width);
            int height=ChangeInt(bi,11);
            System.out.println("像素高: "+height);
            int nbitcount=(((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
            System.out.println("图像位数: "+nbitcount);
            int nsizeimage=ChangeInt(bi,23);
            System.out.println("图像大小: "+nsizeimage);
            //判断是否后面有补0 的情况
            int skip_width = 0;
            if(!(width * 3 % 4==0)){
                skip_width = 4 - width * 3 % 4;
            }
            int[] data = new int[width * height];
            //按行读取
            for (int h = height - 1; h >= 0; h--){
                for (int w = 0; w < width; w++){
                    //  读入三原色
                    int blue  = dis.read();
                    int green = dis.read();
                    int red = dis.read();
                    // 将三原色转为RGB整数值。
                    data[width * h + w] = 0xff << 24
                            |(red & 0xff) << 16
                            | (green & 0xff) << 8
                            | (blue & 0xff);
                    //跳过补0项
                    if(w == width - 1){
                        dis.skipBytes(skip_width);
                    }
                }
            }
            // Use ToolKit creating the (ToolKitImage)Image
            Toolkit kit = Toolkit.getDefaultToolkit();
            image = kit.createImage(new MemoryImageSource(width, height, data, 0, width));
            return image;
        } catch (Exception e) {
            System.out.println("MyRead() Exception!");
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
        int temp = (((int)bi[start]&0xff)<<24)
                | (((int)bi[start-1]&0xff)<<16)
                | (((int)bi[start-2]&0xff)<<8)
                | (int)bi[start-3]&0xff;
        return temp;
    }
}
