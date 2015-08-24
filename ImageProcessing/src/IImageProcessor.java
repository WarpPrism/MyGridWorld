import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.MemoryImageSource;
import java.awt.image.RGBImageFilter;

/**
 * Created by ZhouJihao on 8/22/15.
 */


public class IImageProcessor implements imagereader.IImageProcessor {
    class RedFilter extends RGBImageFilter {
        public RedFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int i, int i1, int rgb) {
            return rgb & 0xffff0000;
        }
    }

    class GreenFilter extends RGBImageFilter {
        public GreenFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int i, int i1, int rgb) {
            return rgb & 0xff00ff00;
        }
    }

    class BlueFilter extends RGBImageFilter {
        public BlueFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int i, int i1, int rgb) {
            return rgb & 0xff0000ff;
        }
    }

    class GrayFilter extends RGBImageFilter {
        public GrayFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int i, int i1, int rgb) {
            int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587
                    + (rgb & 0x000000ff) * 0.114);
            return 0xff000000 + (gray << 16) + (gray << 8) + gray;
            /*int gray = (int) (((rgb & 0x00ff0000) >> 16) * 0.299
                    + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);
            return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;*/
        }
    }
    @Override
    public Image showChanelR(Image image) {
        RedFilter filter = new RedFilter();
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.createImage(new FilteredImageSource(image.getSource(), filter));
    }

    @Override
    public Image showChanelG(Image image) {
        GreenFilter filter = new GreenFilter();
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.createImage(new FilteredImageSource(image.getSource(), filter));
    }

    @Override
    public Image showChanelB(Image image) {
        BlueFilter filter = new BlueFilter();
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.createImage(new FilteredImageSource(image.getSource(), filter));
    }

    @Override
    public Image showGray(Image image) {
        GrayFilter filter = new GrayFilter();
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.createImage(new FilteredImageSource(image.getSource(), filter));
    }
}
