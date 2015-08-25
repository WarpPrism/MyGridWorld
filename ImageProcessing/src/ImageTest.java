import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhoujihao on 15-8-25.
 * Open source,open mind.
 */
public class ImageTest {
    @Before
    public void setUp() {

    }
    @Test
    public void testRed() {
        String source = "bmptest/my/red.bmp";
        String goal = "bmptest/goal/2_red_goal.bmp";
        byte[] srcData = getImageData(source);
        byte[] goalData = getImageData(goal);
        assertEquals(goalData.length, srcData.length);
        for (int i = 0; i < goalData.length / 4; i++) {
            assertEquals(goalData[i], srcData[i]);
        }
    }

    @Test
    public void testGreen() {
        String source = "bmptest/my/green.bmp";
        String goal = "bmptest/goal/2_green_goal.bmp";
        byte[] srcData = getImageData(source);
        byte[] goalData = getImageData(goal);
        assertEquals(goalData.length, srcData.length);
        for (int i = 0; i < goalData.length / 4; i++) {
            assertEquals(goalData[i], srcData[i]);
        }
    }

    @Test
    public void testBlue() {
        String source = "bmptest/my/blue.bmp";
        String goal = "bmptest/goal/2_blue_goal.bmp";
        byte[] srcData = getImageData(source);
        byte[] goalData = getImageData(goal);
        assertEquals(goalData.length, srcData.length);
        for (int i = 0; i < goalData.length / 4; i++) {
            assertEquals(goalData[i], srcData[i]);
        }
    }

    @Test
    public void testGray() {
        String source = "bmptest/my/gray.bmp";
        String goal = "bmptest/goal/2_gray_goal.bmp";
        byte[] srcData = getImageData(source);
        byte[] goalData = getImageData(goal);
        assertEquals(goalData.length, srcData.length);
        for (int i = 0; i < goalData.length / 4; i++) {
            assertEquals((int)goalData[i], (int)srcData[i]);
        }
    }
    @After
    public void endEachTest() {

    }

    private byte[] getImageData(String path) {
        File f = new File(path);
        byte[] data = new byte[2500000];
        try {
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            // Read Bitmap File Header.
            int bflen = 14;
            byte[] bf = new byte[bflen];
            dis.read(bf, 0, bflen);
            // Read Bitmap Info Header.
            int bilen = 40;
            byte[] bi = new byte[bilen];
            dis.read(bi, 0, bilen);
            dis.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
