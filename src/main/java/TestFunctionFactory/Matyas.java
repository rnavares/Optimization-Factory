package TestFunctionFactory;

public class Matyas extends TestFunction {

    public double evaluate(double[] vars){

        double res = 0;

        if (vars.length != 2)
            throw new IllegalArgumentException("Number of vars have to be 2 for Matyas function");

        res = 0.26 * (vars[0] * vars[0] + vars[1] * vars[1]) - 0.48 * vars[0] * vars[1];

        return(res);
    }
}
