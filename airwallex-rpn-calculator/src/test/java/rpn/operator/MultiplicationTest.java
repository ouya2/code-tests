package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MultiplicationTest
{
    Multiplication operator;
    @Before
    public void setup()
    {
        operator = new Multiplication();
    }

    @Test
    public void testCalculate()
    {
        double firstParameter = 2d;
        double secondParameter = 412345687987435d;
        assertEquals(8.2469137597487E14, operator.calculate(firstParameter, secondParameter));
        firstParameter = 3.5412457d;
        secondParameter = 12.6546789234d;
        assertEquals(44.81332732237088, operator.calculate(firstParameter, secondParameter), 0.000000000000001);
    }
}
