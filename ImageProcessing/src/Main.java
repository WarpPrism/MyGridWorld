import imagereader.Runner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        IImageIO io = new IImageIO();
        IImageProcessor processor = new IImageProcessor();
        Runner.run(io, processor);
    }
}
