import static calculator.CalculatorPanel.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void Trivialtest() {
        double delta = 1E-6;
        double result = 0.0;
       
        System.out.println("Testing calculate()"); 
        result = calculate(1.0, "+", 1.0); 
        assertEquals("Failure[+]: 1.0 + 1.0 falis.", result, 1.0 + 1.0, delta);

        result = calculate(1.0, "-", 1.0); 
        assertEquals("Failure[-]: 1.0 - 1.0 falis.", result, 1.0 - 1.0, delta);

        result = calculate(1.0, "*", 1.0); 
        assertEquals("Failure[*]: 1.0 * 1.0 falis.", result, 1.0 * 1.0, delta);

        result = calculate(1.0, "/", 1.0); 
        assertEquals("Failure[/]: 1.0 / 1.0 falis.", result, 1.0 / 1.0, delta);
    }
}
