package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static rpn.operator.OperatorConstants.DIVIDE;
import static rpn.operator.OperatorConstants.MINUS;
import static rpn.operator.OperatorConstants.MULTIPLY;
import static rpn.operator.OperatorConstants.PLUS;

public class DivisionOperatorTest
{
    DivisionOperator operator;

    @Before
    public void setup()
    {
        operator = new DivisionOperator();
    }

    @Test
    public void testDivisionOperation() {
        assertEquals(DIVIDE, operator.getSymbol());
        assertEquals(MULTIPLY, operator.getReverseOperatorSymbol());
    }
}
