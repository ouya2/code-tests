import com.google.common.collect.ImmutableMap;
import rpn.operator.*;

import java.util.Map;

/**
 * Operator registration for supported operations
 */
public final class OperatorRegistry
{
    private static Map<String, Operator> registry = ImmutableMap.<String, Operator>builder()
                                                    .put(OperatorConstants.PLUS, new Addition())
                                                    .put(OperatorConstants.MINUS, new Subtraction())
                                                    .put(OperatorConstants.MULTIPLY, new Multiplication())
                                                    .put(OperatorConstants.DIVIDE, new Division())
                                                    .put(OperatorConstants.SQRT, new SquareRoot())
                                                    .put(OperatorConstants.POWER, new Power())
                                                    .put(OperatorConstants.UNDO, new Undo())
                                                    .put(OperatorConstants.CLEAR, new Clear())
                                                    .build();

    public static Operator getOperator(String symbol)
    {
        return registry.get(symbol);
    }
}
