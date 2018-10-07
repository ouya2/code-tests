package calculation_strategy;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.Add;

public class AddTest
{
    Add operator;
    @Before
    public void setup()
    {
        operator = new Add();
    }

    @Test
    public void testCalculate()
    {
        double firstParameter = 412345687987435d;
        double secondParameter = 46537892323d;
        assertEquals(4.12392225879758E14, operator.calculate(firstParameter, secondParameter));
        firstParameter = 12.751216546789234d;
        secondParameter = 3.7654334d;
        assertEquals(16.516649946789233, operator.calculate(firstParameter, secondParameter));
    }
}
