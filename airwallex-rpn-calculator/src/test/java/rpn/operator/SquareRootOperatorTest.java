package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static rpn.operator.OperatorConstants.MINUS;
import static rpn.operator.OperatorConstants.PLUS;
import static rpn.operator.OperatorConstants.POWER;
import static rpn.operator.OperatorConstants.SQRT;

public class SquareRootOperatorTest
{
    SquareRootOperator operator;

    @Before
    public void setup()
    {
        operator = new SquareRootOperator();
    }

    @Test
    public void testSquareRootOperation() {
        assertEquals(SQRT, operator.getSymbol());
        assertEquals(POWER, operator.getReverseOperatorSymbol());
    }
}
