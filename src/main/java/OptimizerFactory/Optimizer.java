package OptimizerFactory;
import TestFunctionFactory.*;

public abstract class Optimizer {
    public abstract void optimize(TestFunction fobj, int nvars);
}
