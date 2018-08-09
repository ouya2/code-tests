import java.io.Console;
import java.text.DecimalFormat;
import java.util.Stack;

import static java.lang.System.exit;

/**
 * Application launcher
 */
public class AppLauncher
{
    private static final String DECIMAL_PLACES_FORMAT = "0.##########";

    private RPNCalculator calculator;

    public AppLauncher(RPNCalculator calculator)
    {
        this.calculator = calculator;
    }

    public static void main(String[] args)
    {
        Console inputReader = System.console();

        if (inputReader == null) {
          System.err.println("Input reader does not exist.");
          exit(1);
        }

        System.out.println("Enter the expression or 'exist' it");
        AppLauncher launcher = new AppLauncher(new RPNCalculator());
        boolean isRunning = true;
        while(isRunning)
        {
            String inputString = inputReader.readLine(": ");
            if ("exit".equalsIgnoreCase(inputString)) {
                isRunning = false;
            }
            else {
                launcher.start(inputString);
            }
        }
    }

    public void start(String inputString)
    {
        Stack<Double> stack = getNumbersStack(inputString);
        System.out.print("Stack: ");
        DecimalFormat fmt = new DecimalFormat(DECIMAL_PLACES_FORMAT);
        stack.stream().forEach(e -> {
            System.out.print(fmt.format(e));
            System.out.print(" ");
        });
        System.out.printf("%n");
    }

    Stack<Double> getNumbersStack(String inputString)
    {
        try {
            calculator.evaluateRPNExpression(inputString);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        return calculator.getNumbersStack();
    }
}
