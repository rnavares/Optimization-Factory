import TestFunctionFactory.*;
import OptimizerFactory.*;

public class OptimizerTest {
    public static void main(String[] args){

        TestFunction rosenbrock = new Rosenbrock();
        TestFunction sphere = new Sphere();
        TestFunction rastrigin = new Rastrigin();
        TestFunction matyas = new Matyas();


        Optimizer simulated_annealing = new SA();
        Optimizer bat_algorithm = new BAT();
        Optimizer firefly_algorithm = new FF();
        Optimizer cuckoo_search = new CS();

        System.out.println("=== Evaluation Rosenbrock with fmin: 0 at (0,0) ==");
        System.out.println();
        System.out.println("Simulated Annealing....");
        simulated_annealing.optimize(rosenbrock, 2);
        System.out.println();

        System.out.println("Bat Algorithm....");
        bat_algorithm.optimize(rosenbrock, 2);
        System.out.println();

        System.out.println("Firefly....");
        firefly_algorithm.optimize(rosenbrock, 2);
        System.out.println();

        System.out.println("Cuckoo....");
        cuckoo_search.optimize(rosenbrock, 2);
        System.out.println();

        System.out.println("=== Evaluation Sphere with fmin: 0 at (0,0, ..., 0) ==");
        System.out.println();

        System.out.println("Simulated Annealing....");
        simulated_annealing.optimize(sphere, 5);
        System.out.println();

        System.out.println("Bat Algorithm....");
        bat_algorithm.optimize(sphere, 5);
        System.out.println();

        System.out.println("Firefly....");
        firefly_algorithm.optimize(sphere, 2);
        System.out.println();

        System.out.println("Cuckoo....");
        cuckoo_search.optimize(sphere, 2);
        System.out.println();

        System.out.println("=== Evaluation Rastrigin with fmin ~ 0.0 at (0,..., 0) ==");
        System.out.println();

        System.out.println("Simulated Annealing....");
        simulated_annealing.optimize(rastrigin, 2);
        System.out.println();

        System.out.println("Bat Algorithm....");
        bat_algorithm.optimize(rastrigin, 2);
        System.out.println();

        System.out.println("Firefly....");
        firefly_algorithm.optimize(rastrigin, 2);
        System.out.println();

        System.out.println("Cuckoo....");
        cuckoo_search.optimize(rastrigin, 2);
        System.out.println();

        System.out.println("=== Evaluation Matyas with fmin: 0 at (0, 0) ==");
        System.out.println();

        System.out.println("Simulated Annealing....");
        simulated_annealing.optimize(matyas, 2);
        System.out.println();

        System.out.println("Bat Algorithm....");
        bat_algorithm.optimize(matyas, 2);
        System.out.println();

        System.out.println("Firefly....");
        firefly_algorithm.optimize(matyas, 2);
        System.out.println();

        System.out.println("Cuckoo....");
        cuckoo_search.optimize(matyas, 2);
        System.out.println();


    }
}
