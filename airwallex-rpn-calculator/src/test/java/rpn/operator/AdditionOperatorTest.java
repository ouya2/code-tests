package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static rpn.operator.OperatorConstants.MINUS;
import static rpn.operator.OperatorConstants.PLUS;

public class AdditionOperatorTest
{
    AdditionOperator operator;
    @Before
    public void setup()
    {
        operator = new AdditionOperator();
    }

    @Test
    public void testAdditionOperation() {
        assertEquals(PLUS, operator.getSymbol());
        assertEquals(MINUS, operator.getReverseOperatorSymbol());
    }
}
