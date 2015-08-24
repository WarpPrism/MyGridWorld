import imagereader.Runner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        /*ImageViewer v = new ImageViewer();
        try {
            v.showRGB24(v.getDIS());
        } catch (IOException e) {
            e.printStackTrace();
        }
        v.paint(v.getG());*/

        IImageIO io = new IImageIO();
        IImageProcessor processor = new IImageProcessor();
        Runner.run(io, processor);
    }
}
