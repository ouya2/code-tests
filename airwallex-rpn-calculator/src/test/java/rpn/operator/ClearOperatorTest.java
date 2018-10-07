package rpn.operator;

import static junit.framework.TestCase.assertEquals;
import static rpn.operator.OperatorConstants.CLEAR;
import static rpn.operator.OperatorConstants.MINUS;
import static rpn.operator.OperatorConstants.PLUS;

import org.junit.Before;
import org.junit.Test;

public class ClearOperatorTest
{
    ClearOperator operator;

    @Before
    public void setup()
    {
        operator = new ClearOperator();
    }

    @Test
    public void testClearOperation() {
        assertEquals(CLEAR, operator.getSymbol());
    }
}
