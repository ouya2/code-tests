import rpn.operator.Operator;

import static rpn.operator.OperatorConstants.MAX_NUMBER_OF_PARAMETER;
import static rpn.operator.OperatorConstants.MIN_NUMBER_OF_PARAMETER;

/**
 * This entity keeps track of operation performed on a number
 * and generate undo expression when undo is triggered.
 */
public class Instruction
{
    private Operator operator;
    private Double number;

    public Instruction(Operator operator, Double number)
    {
        this.operator = operator;
        this.number = number;
    }

    /**
     * Get the reverse instruction for
     *
     * @return String
     */
    public String getReverseInstruction()
    {
        if (operator.getNumberOfParameters() < MIN_NUMBER_OF_PARAMETER)
        {
            throw new IllegalStateException(String.format("Invalid operation for operator %s", operator.getSymbol()));
        }

        return (operator.getNumberOfParameters() < MAX_NUMBER_OF_PARAMETER) ?
                String.format("%s", operator.getReverseOperatorSymbol()) :
                String.format("%.15f %s %.15f", number, operator.getReverseOperatorSymbol(), number);
    }
}
