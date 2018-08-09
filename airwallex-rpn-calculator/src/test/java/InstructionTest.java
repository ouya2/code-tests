import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rpn.operator.Operator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstructionTest
{
    @Mock
    private Operator operator;

    private Instruction instruction;

    @Before
    public void setup(){
        instruction = new Instruction(operator, 11.1245871425d);
    }

    @Test
    public void testReverseTwoParameter()
    {
        when(operator.getNumberOfParameters()).thenReturn(2);
        when(operator.getReverseOperatorSymbol()).thenReturn("+");
        assertEquals(String.format("%.15f %s %.15f", 11.1245871425d, "+", 11.1245871425d), instruction.getReverseInstruction());
    }

    @Test
    public void testReverseOneParameter()
    {
        when(operator.getNumberOfParameters()).thenReturn(1);
        when(operator.getReverseOperatorSymbol()).thenReturn("pow");

        assertEquals("pow",instruction.getReverseInstruction());
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidParameterNumber()
    {
        when(operator.getNumberOfParameters()).thenReturn(0);
        instruction.getReverseInstruction();
    }


}
