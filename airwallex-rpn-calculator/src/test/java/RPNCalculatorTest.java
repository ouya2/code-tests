import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RPNCalculatorTest
{
    @Test
    public void testArithmeticOperators() throws Exception {
        RPNCalculator calculator = new RPNCalculator();

        calculator.evaluateRPNExpression("5 2");
        assertEquals(5, calculator.getNumbersStack().get(0), 0);
        assertEquals(2, calculator.getStackItemAt(1), 0);

        // subtraction
        calculator.evaluateRPNExpression("clear");
        calculator.evaluateRPNExpression("5 2 -");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(3, calculator.getStackItemAt(0), 0);
        calculator.evaluateRPNExpression("3 -");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(0, calculator.getStackItemAt(0), 0);

        // negative
        calculator.evaluateRPNExpression("clear");
        calculator.evaluateRPNExpression("1 2 3 4 5 *");
        assertEquals(4, calculator.getNumbersStack().size());
        calculator.evaluateRPNExpression("clear 3 4 -");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(-1, calculator.getStackItemAt(0), 0);


        // division
        calculator.evaluateRPNExpression("clear");
        calculator.evaluateRPNExpression("7 12 2 /");
        assertEquals(7, calculator.getStackItemAt(0), 0);
        assertEquals(6, calculator.getStackItemAt(1), 0);
        calculator.evaluateRPNExpression("*");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(42, calculator.getStackItemAt(0), 0);
        calculator.evaluateRPNExpression("4 /");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(10.5, calculator.getStackItemAt(0), 0);

        //multiplication
        calculator.evaluateRPNExpression("clear");
        calculator.evaluateRPNExpression("1 2 3 4 5");
        calculator.evaluateRPNExpression("* * * *");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(120, calculator.getStackItemAt(0), 0);

    }

    @Test
    public void testSqrt() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("2 sqrt");
        calculator.evaluateRPNExpression("clear 9 sqrt");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(3, calculator.getStackItemAt(0), 0);
    }

    @Test
    public void testInsufficientParameters() {
        RPNCalculator calculator = new RPNCalculator();
        try {
            calculator.evaluateRPNExpression("1 2 3 * 5 + * * 6 5");
        } catch (Exception e) {
            assertEquals("operator * (position: 8): insufficient parameters", e.getMessage());
        }
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(11, calculator.getStackItemAt(0), 0);
    }

    @Test
    public void testUndo() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("5 4 3 2");
        assertEquals(4, calculator.getNumbersStack().size());
        calculator.evaluateRPNExpression("undo undo *");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(20, calculator.getStackItemAt(0), 0);
        calculator.evaluateRPNExpression("5 *");
        assertEquals(1, calculator.getNumbersStack().size());
        assertEquals(100, calculator.getStackItemAt(0), 0);
        calculator.evaluateRPNExpression("undo");
        assertEquals(2, calculator.getNumbersStack().size());
        assertEquals(20, calculator.getStackItemAt(0), 0);
        assertEquals(5, calculator.getStackItemAt(1), 0);
        calculator.evaluateRPNExpression("+ undo - undo / undo * undo sqrt undo pow undo");
        assertEquals(2, calculator.getNumbersStack().size());
        assertEquals(20, calculator.getStackItemAt(0), 0.0000000001);
        assertEquals(5, calculator.getStackItemAt(1), 0.0000000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnlyOperators() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("+ +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCharacters() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("2 a +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoSpaces() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("22+");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoSpaces2() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("2 2+ 3");
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression("1 0 /");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() throws Exception {
        RPNCalculator calculator = new RPNCalculator();
        calculator.evaluateRPNExpression(null);
    }
}
