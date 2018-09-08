package TestFunctionFactory;

public class Rosenbrock extends TestFunction {

    public double evaluate(double[] vars){

        double res = Math.pow((vars[0] - 1), 2);

        for (int i = 1; i < vars.length; i++){
            res = res + 100 * Math.pow((vars[i] - Math.pow(vars[i - 1], 2)), 2);
        }

        return(res);
    }
}

