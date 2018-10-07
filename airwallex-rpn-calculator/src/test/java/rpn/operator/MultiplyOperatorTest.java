package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static rpn.operator.OperatorConstants.DIVIDE;
import static rpn.operator.OperatorConstants.MINUS;
import static rpn.operator.OperatorConstants.MULTIPLY;
import static rpn.operator.OperatorConstants.PLUS;

public class MultiplyOperatorTest
{
    MultiplyOperator operator;
    @Before
    public void setup()
    {
        operator = new MultiplyOperator();
    }

    @Test
    public void testMultiplyOperation() {
        assertEquals(MULTIPLY, operator.getSymbol());
        assertEquals(DIVIDE, operator.getReverseOperatorSymbol());
    }
}
