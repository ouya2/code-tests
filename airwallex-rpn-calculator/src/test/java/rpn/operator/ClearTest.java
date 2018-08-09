package rpn.operator;

import org.junit.Before;
import org.junit.Test;

public class ClearTest
{
    Clear operator;

    @Before
    public void setup()
    {
        operator = new Clear();
    }

    @Test (expected = ArithmeticException.class)
    public void testCalculate() throws Exception
    {
       operator.calculate(0.0, 1.0);
    }
}
