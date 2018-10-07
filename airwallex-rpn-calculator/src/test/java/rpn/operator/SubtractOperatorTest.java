package rpn.operator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static rpn.operator.OperatorConstants.MINUS;
import static rpn.operator.OperatorConstants.PLUS;

public class SubtractOperatorTest
{
    SubtractOperator operator;
    @Before
    public void setup()
    {
        operator = new SubtractOperator();
    }

    @Test
    public void testSubtractionOperation() {
        assertEquals(MINUS, operator.getSymbol());
        assertEquals(PLUS, operator.getReverseOperatorSymbol());
    }
}
