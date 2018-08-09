package rpn.operator;

abstract class GeneralOperator implements Operator
{
    private String symbol;
    private String opposite;
    private int numberOfParameters;

    public GeneralOperator(String symbol, String opposite, int numberOfParameters)
    {
        this.symbol = symbol;
        this.opposite = opposite;
        this.numberOfParameters = numberOfParameters;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public String getReverseOperatorSymbol()
    {
        return opposite;
    }

    public int getNumberOfParameters()
    {
        return numberOfParameters;
    }

    @Override
    public String toString()
    {
        return symbol;
    }
}
