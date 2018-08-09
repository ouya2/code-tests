import org.junit.Test;
import rpn.operator.Operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static rpn.operator.OperatorConstants.*;

public class OperatorRegistryTest
{
    @Test
    public void testOperatorRegistry()
    {
        Operator operator = OperatorRegistry.getOperator("+");
        assertEquals(PLUS, operator.getSymbol());
        operator = OperatorRegistry.getOperator("undo");
        assertEquals(UNDO, operator.getSymbol());
        operator = OperatorRegistry.getOperator("clear");
        assertEquals(CLEAR, operator.getSymbol());
        operator = OperatorRegistry.getOperator("sqrt");
        assertEquals(SQRT, operator.getSymbol());
        operator = OperatorRegistry.getOperator("pow");
        assertEquals(POWER, operator.getSymbol());
        operator = OperatorRegistry.getOperator("-");
        assertEquals(MINUS, operator.getSymbol());

        operator = OperatorRegistry.getOperator("reverse");
        assertNull(operator);
    }
}
