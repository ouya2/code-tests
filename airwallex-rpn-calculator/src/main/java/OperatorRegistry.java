import com.google.common.collect.ImmutableMap;
import rpn.operator.*;

import java.util.Map;

/**
 * Operator registration for supported operations
 */
public final class OperatorRegistry
{
    private static Map<String, Operator> registry = ImmutableMap.<String, Operator>builder()
                                                    .put(OperatorConstants.PLUS, new AdditionOperator())
                                                    .put(OperatorConstants.MINUS, new SubtractOperator())
                                                    .put(OperatorConstants.MULTIPLY, new MultiplyOperator())
                                                    .put(OperatorConstants.DIVIDE, new DivisionOperator())
                                                    .put(OperatorConstants.SQRT, new SquareRootOperator())
                                                    .put(OperatorConstants.POWER, new PowerOperator())
                                                    .put(OperatorConstants.UNDO, new UndoOperator())
                                                    .put(OperatorConstants.CLEAR, new ClearOperator())
                                                    .build();

    public static Operator getOperator(String symbol)
    {
        return registry.get(symbol);
    }
}
