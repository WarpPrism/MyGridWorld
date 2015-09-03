
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by zhoujihao on 15-8-23.
 * Open source,open mind.
 */
public class IImageProcessorNew implements imagereader.IImageProcessor {
    // Image Information
    private int width;
    private int height;
    private int off;
    private int rowByteNum;

    private byte[] getImageData(Image img) {
        BufferedImage bfimg = toBufferedImage(img);
        /*BufferedImage bfimg = (BufferedImage)img*/
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = null;
        try {
            ImageIO.write(bfimg, "bmp", bos);
            data = bos.toByteArray();
        } catch (IOException e) {
            /*System.out.println("GetImageData: IO Exception!")*/
        }
        return data;
    }

    private void parseImageInformation(byte[] data) {
        width = changeInt(data, 21);
        height = changeInt(data, 25);
        /*int imagefilesize = (data[2] & 0xff) | ((data[3] & 0xff) << 8)
                | ((data[4] & 0xff) << 16) | ((data[5] & 0xff) << 24)
        int imagesize = (data[34] & 0xff) | ((data[35] & 0xff) << 8)
                | ((data[36] & 0xff) << 16) | ((data[37] & 0xff) << 24)*/
        off = changeInt(data, 13);
        rowByteNum = width * 3;
        if (rowByteNum % 4 != 0) {
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        }
        /*System.out.println("Image Width: " + width)
        System.out.println("Image Height: " + height)
        System.out.println("Image File size: " + filesize)
        System.out.println("Image size: " + imagesize)
        System.out.println("Image Data Offset: " + off)*/
    }

    public int changeInt(byte[] bi,int start){
        return (((int)bi[start]&0xff)<<24)
                | (((int)bi[start-1]&0xff)<<16)
                | (((int)bi[start-2]&0xff)<<8)
                | (int)bi[start-3]&0xff;
    }

    public BufferedImage createImage(byte[] data) {
        BufferedImage image = null;
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            /*System.out.println("CreateImage : IO Exception!")*/
        }
        return image;
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image without transparency RGB form.
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    @Override
    public Image showChanelR(Image image) {
        byte data[] = getImageData(image);
        parseImageInformation(data);
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                //Set the chanel to 0 except red
                data[off + h * rowByteNum + w * 3] = 0;
                data[off + h * rowByteNum + w * 3 + 1] = 0;
            }
        }
        return createImage(data);
    }

    @Override
    public Image showChanelG(Image image) {
        byte data1[] = getImageData(image);
        parseImageInformation(data1);
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                //Set the chanel to 0 except green
                data1[off + h * rowByteNum + w * 3] = 0;
                data1[off + h * rowByteNum + w * 3 + 2] = 0;
            }
        }
        return createImage(data1);
    }

    @Override
    public Image showChanelB(Image image) {
        byte data2[] = getImageData(image);
        parseImageInformation(data2);
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                //Set the chanel to 0 except blue
                data2[off + h * rowByteNum + w * 3 + 1] = 0;
                data2[off + h * rowByteNum + w * 3 + 2] = 0;
            }
        }
        return createImage(data2);
    }

    @Override
    public Image showGray(Image image) {
        byte data3[] = getImageData(image);
        parseImageInformation(data3);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                // Caculate the gray value
                double gray = (double)(data3[off + i * rowByteNum + j * 3] & 0xff) * 0.1140 + (double)(data3[off + i * rowByteNum + j * 3 + 1] & 0xff) * 0.5870 + (double)(data3[off + i * rowByteNum + j * 3 + 2] & 0xff) * 0.2990;
                //Set the chanel of each pixel to gray value
                data3[off + i * rowByteNum + j * 3 + 2] = (byte)(int)gray;
                data3[off + i * rowByteNum + j * 3 + 1] = (byte)(int)gray;
                data3[off + i * rowByteNum + j * 3] = (byte)(int)gray;
            }
        }
        return createImage(data3);
    }
}
