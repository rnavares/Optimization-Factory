package TestFunctionFactory;

public class Sphere  extends TestFunction{

    public double evaluate(double[] vars){

        double res = 0;

        for (int i = 0; i < vars.length; i++){
            res = res + (vars[i] * vars[i]);
        }

        return(res);
    }


}
