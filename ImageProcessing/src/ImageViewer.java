import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 8/22/15.
 */
public class ImageViewer extends JFrame {
    private String path;
    private FileInputStream fis;
    private DataInputStream dis;
    private byte[] bf;
    private byte[] bi;
    private int image_width;
    private int image_height;
    private int[][] imageR;
    private int[][] imageG;
    private int[][] imageB;
    private Graphics g;

    public ImageViewer() {
        path = "/home/Administrator/Desktop/ImageProcessing/bmptest/2.bmp";
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dis = new DataInputStream(fis);
        int bflen = 14;
        bf = new byte[bflen];
        try {
            dis.read(bf, 0, bflen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int bilen = 40;
        bi = new byte[bilen];
        try {
            dis.read(bi, 0, bilen);
        } catch (IOException e) {
            e.printStackTrace();
        }

        image_width=ChangeInt(bi,7);

        System.out.println("宽:"+image_width);

        image_height=ChangeInt(bi,11);
        System.out.println("高:"+image_height);
        //位数
        int nbitcount=(((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
        System.out.println("位数:"+nbitcount);
        //源图大小
        int nsizeimage=ChangeInt(bi,23);
        System.out.println("源图大小:"+nsizeimage);
    }

    //转成int
    public int ChangeInt(byte[] bi,int start){
        return (((int)bi[start]&0xff)<<24)
                | (((int)bi[start-1]&0xff)<<16)
                | (((int)bi[start-2]&0xff)<<8)
                | (int)bi[start-3]&0xff;
    }

    public void showRGB24(DataInputStream dis) throws IOException {
        this.setTitle(path);
        this.setSize(image_width, image_height);
        this.setResizable(false);
        this.setVisible(true);
        g = this.getGraphics();
        int skip_width = 0;
        if(!(image_width*3 % 4==0)){//图片的宽度不为0
            skip_width = 4 - image_width * 3 % 4;
        }//判断是否后面有补0 的情况

        //装载RGB颜色的数据数组
        imageR = new int[image_height][image_width];
        imageG = new int[image_height][image_width];
        imageB = new int[image_height][image_width];

        //按行读取 如果H,W为正则倒着来
        for (int h = image_height - 1; h >= 0; h--){
            for (int w = 0; w < image_width; w++){
                //  读入三原色
                int blue  = dis.read();
                int green = dis.read();
                int red = dis.read();
                imageB[h][w]=blue;
                imageG[h][w]=green;
                imageR[h][w]=red;
                if(w==0){//跳过补0项
                    // System.out.println(dis.skipBytes(skip_width));
                    dis.skipBytes(skip_width);
                    ;
                }
            }
        }
        repaint();
    }

    public void paint(java.awt.Graphics g){
        for (int h=0;h<image_height;h++){
            for (int w=0;w<image_width;w++){
                g.setColor(new java.awt.Color(imageR[h][w],imageG[h][w],imageB[h][w]));
                g.fillOval(w, h, 1, 1);
            }
        }
    }

    public DataInputStream getDIS() {
        return this.dis;
    }

    public Graphics getG() {
        return g;
    }
}
