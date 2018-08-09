package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UndoTest
{
    Undo operator;
    @Before
    public void setup()
    {
        operator = new Undo();
    }

    @Test (expected = ArithmeticException.class)
    public void testCalculate() throws Exception
    {
       operator.calculate(0.0, 1.0);
    }
}
