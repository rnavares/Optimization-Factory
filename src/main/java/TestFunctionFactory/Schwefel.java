package TestFunctionFactory;

public class Schwefel extends TestFunction {

    public double evaluate(double[] vars){

        double res = 0;

        for (int i = 0; i < vars.length; i++){
            res = res + vars[i] * Math.sin(Math.sqrt(Math.abs(vars[i])));
        }

        res = 418.9829 * vars.length - res;

        return(res);
    }
}
