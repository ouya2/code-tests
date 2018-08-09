package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SubtractionTest
{
    Subtraction operator;
    @Before
    public void setup()
    {
        operator = new Subtraction();
    }

    @Test
    public void testCalculate()
    {
        double firstParameter = 46537892323d;
        double secondParameter = 412345687987435d;
        assertEquals(4.12299150095112E14, operator.calculate(firstParameter, secondParameter));
        firstParameter = 3.7654334d;
        secondParameter = 12.6546789234123445d;
        assertEquals(8.889245523412345, operator.calculate(firstParameter, secondParameter));
    }
}
