import imagereader.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by zhoujihao on 15-8-23.
 */
public class IImageProcessor_new implements imagereader.IImageProcessor {
    private int width;
    private int height = 1;
    private int bitcount;
    private int imagesize;

    public byte[] getImageData(Image image) {
        BufferedImage bimg = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bimg, "bmp", stream);
            byte[] bm = stream.toByteArray();
            /*int temp = bm[100];
            System.out.println(temp + " " + bm.length);*/
            // Get bitmap FileHeader
            int bflen = 14;
            byte[] bf = new byte[bflen];
            /*for (int i = 0; i < bflen; i++) {
                bf[i] = bm[i];
            }*/
            System.arraycopy(bm, 0, bf, 0, bflen);
            // Get bitmap InfoHeader
            int bilen = 40;
            byte[] bi = new byte[bilen];
            /*for (int i = bflen, j = 0; j < bilen; i++, j++) {
                bi[j] = bm[i];
            }*/
            System.arraycopy(bm, bflen - 1, bi, 0, bilen);
            // get basic bitmap Information.
            width = (int)(bi[7] & 0xff) << 24
                    | (int)(bi[6] & 0xff) << 16
                    | (int)(bi[5] & 0xff) << 8
                    | (int)(bi[4] & 0xff);
            System.out.println("Bitmap Width: " + width);
            height = (int)(bi[11] & 0xff) << 24
                    | (int)(bi[10] & 0xff) << 16
                    | (int)(bi[9] & 0xff) << 8
                    | (int)(bi[8] & 0xff);
            System.out.println("Bitmap Width: " + height);
            bitcount = (int)(bi[15] & 0xff) << 8
                    | (int)(bi[14] & 0xff);
            System.out.println("Bitmap bit count: " + bitcount);
            imagesize = (int)(bi[23] & 0xff) << 24
                    | (int)(bi[22] & 0xff) << 16
                    | (int)(bi[21] & 0xff) << 8
                    | (int)(bi[20] & 0xff);
            System.out.println("Bitmap size: " + imagesize);

            byte[] rgb = new byte[imagesize];
            System.arraycopy(bm, bflen + bilen, rgb, 0, imagesize);
            /*for (int i = bilen + bflen, j = 0; j < imagesize; i++, j++) {
                rgb[j] = bm[i];
            }*/
            System.out.println(rgb.length + " " + (int)rgb[20]);
            return rgb;
        } catch (Exception e) {
            System.out.println("Exception!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Image showChanelR(Image image) {
        byte[] rgb = getImageData(image);
        int[] rgbint = new int[width * height];
        int index = 0;
        int blank = imagesize / height - width * 3;
        for (int h = height - 1; h >= 0; h--) {
            for (int w = 0; w < width; w++) {
                //  读入三原色
                int blue = rgb[index];
                int green = rgb[index + 1];
                int red = rgb[index + 2];
                rgbint[width * h + w] = 0xff << 24
                        | (red & 0xff) << 16
                        | (green & 0xff) << 8
                        | (blue & 0xff);
                index += 3;
            }
            index += blank;
        }
        Toolkit kit = Toolkit.getDefaultToolkit();
        image = kit.createImage(new MemoryImageSource(width, height, rgbint, 0, width));
        return image;
    }

    @Override
    public Image showChanelG(Image image) {
        return null;
    }

    @Override
    public Image showChanelB(Image image) {
        return null;
    }

    @Override
    public Image showGray(Image image) {
        return null;
    }
}
