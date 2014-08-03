import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {
    @Test
    public void testConstant() {
        assertEquals(0, 0);
    }

    @Test
    public void testHello() {
        HelloWorld hw = new HelloWorld();
        int result = hw.forTest();
        assertEquals("HelloWorld != 1?!", result, 1);
    }
}
