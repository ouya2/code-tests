import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Stack;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppLauncherTest
{
    private AppLauncher launcher;

    @Mock
    private RPNCalculator calculator;

    @Before
    public void setup()
    {
        launcher = new AppLauncher(calculator);
    }

    @Test
    public void testProcess() throws Exception
    {
       String inputString = "12 15 10 5 +";
       Stack<Double> expectedNumberStack = new Stack<>();
       expectedNumberStack.push(12d);
       expectedNumberStack.push(15d);
       expectedNumberStack.push(15d);
       when(calculator.getNumbersStack()).thenReturn(expectedNumberStack);

       launcher.start(inputString);

       verify(calculator, times(1)).evaluateRPNExpression(inputString);
    }
}
