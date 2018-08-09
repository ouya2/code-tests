package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DivisionTest
{
    Division operator;

    @Before
    public void setup()
    {
        operator = new Division();
    }

    @Test
    public void testCalculate() throws Exception
    {
        double firstParameter = 2d;
        double secondParameter = 412345687987435d;
        assertEquals(2.061728439937175E14, operator.calculate(firstParameter, secondParameter));
        firstParameter = 3.541245d;
        secondParameter = 12.6546789234d;
        assertEquals(3.573511271713762, operator.calculate(firstParameter, secondParameter), 0.000000000000001);
    }

    @Test(expected = ArithmeticException.class)
    public void testInvalidCalculate()
    {
        operator.calculate(0d, 1235d);
    }
}
