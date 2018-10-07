package rpn.operator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static rpn.operator.OperatorConstants.UNDO;

import org.junit.Before;
import org.junit.Test;

public class UndoOperatorTest
{
    UndoOperator operator;
    @Before
    public void setup()
    {
        operator = new UndoOperator();
    }

    @Test
    public void testClearOperation() {
        assertEquals(UNDO, operator.getSymbol());
        assertNull(operator.getReverseOperatorSymbol());
    }
}
