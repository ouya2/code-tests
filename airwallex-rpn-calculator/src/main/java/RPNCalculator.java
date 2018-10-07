import rpn.calculation_strategy.CalculateStrategy;
import rpn.operator.*;

import java.util.Stack;

public class RPNCalculator {

  private Stack<Double> numbersStack = new Stack<>();
  private Stack<Instruction> instructionsStack = new Stack<>();
  private int currentTokenIndex = 0;

  /**
   * Returns the values numbersStack
   */
  public Stack<Double> getNumbersStack() {
    return numbersStack;
  }

  /**
   * Helper method to return a specific item in the numbersStack
   *
   * @param index index of the element to return
   */
  public Double getStackItemAt(int index) {
    return numbersStack.get(index);
  }

  /**
   * Evaluate a RPN expression and pushes the result into the numbersStack
   *
   * @param input valid RPN expression
   */
  public void evaluateRPNExpression(String input) throws Exception {
    evaluateRPNExpression(input, false);
  }

  /**
   * Evaluate a RPN expression and pushes the result into the numbersStack
   *
   * @param inputExpression valid RPN expression
   * @param isUndoOperation indicates if the operation is an undo operation. undo operations use the
   * same evaluation functions as the standard ones but they are not pushed into instructionsStack
   */
  private void evaluateRPNExpression(String inputExpression, boolean isUndoOperation)
      throws Exception {
    if (inputExpression == null) {
      throw new IllegalArgumentException("Input cannot be null.");
    }
    currentTokenIndex = 0;
    String[] result = inputExpression.split("\\s");
    for (String eachSection : result) {
      currentTokenIndex++;
      Double value = parseDouble(eachSection);
      if (value == null) {
        processOperator(eachSection, isUndoOperation);
      } else {
        // it's a digit
        numbersStack.push(Double.parseDouble(eachSection));
        if (!isUndoOperation) {
          instructionsStack.push(null);
        }
      }
    }
  }

  private Double parseDouble(String str) {
    try {
      return Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      return null;
    }
  }

  /**
   * Executes an operation on the stack
   *
   * @param operatorString RPN valid operator
   * @param isUndoOperation indicates if the operation is an undo operation.
   */
  private void processOperator(String operatorString, boolean isUndoOperation) throws Exception {
    // check if there is an empty stack
    if (numbersStack.isEmpty()) {
      throw new IllegalArgumentException("empty stack");
    }

    // searching for the operator
    Operator operator = OperatorRegistry.getOperator(operatorString);
    if (operator == null) {
      throw new IllegalArgumentException(
          "Operator can not be found for operator: " + operatorString);
    }

    // clear value stack and instructions stack
    if (operator instanceof ClearOperator) {
      clearStacks();
      return;
    }

    // undo evaluates the last instruction in stack
    if (operator instanceof UndoOperator) {
      undoLastInstruction();
      return;
    }

    // Checking that there are enough operand for the operation
    if (operator.getNumberOfParameters() > numbersStack.size()) {
      throw new IllegalStateException(
          String.format("operator %s (position: %d): insufficient parameters",
              operator, currentTokenIndex));
    }

    // getting parameters
    Double firstParameter = numbersStack.pop();
    Double secondParameter = (operator.getNumberOfParameters() > 1) ? numbersStack.pop() : null;
    // calculate
    CalculateStrategy strategy = operator.getCalculationStrategy();
    Double result = strategy.calculate(firstParameter, secondParameter);

    if (result != null) {
      numbersStack.push(result);
      if (!isUndoOperation) {
        instructionsStack
            .push(new Instruction(OperatorRegistry.getOperator(operatorString), firstParameter));
      }
    }
  }

  private void undoLastInstruction() throws Exception {
    if (instructionsStack.isEmpty()) {
      throw new IllegalStateException("no operations to undo");
    }

    Instruction lastInstruction = instructionsStack.pop();
    if (lastInstruction == null) {
      numbersStack.pop();
    } else {
      evaluateRPNExpression(lastInstruction.getReverseInstruction(), true);
    }
  }

  private void clearStacks() {
    numbersStack.clear();
    instructionsStack.clear();
  }
}
