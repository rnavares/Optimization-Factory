package TestFunctionFactory;

public class Rastrigin extends TestFunction {

    public double evaluate(double[] vars){

        double res = 0;
        int A = 10;

        for (int i = 0; i < vars.length; i++){
            res = res + vars[i] * vars[i] - (A *  Math.cos(2 * Math.PI * vars[i]));
        }

        res = (A * vars.length) + res;

        return(res);
    }
}
