package OptimizerFactory;

import TestFunctionFactory.*;
import com.sun.istack.internal.NotNull;

import java.util.Random;

public class CS extends Optimizer {

    static final int n_nest = 25;

    static final double tolerance = 1.0E-5;

    static final int Lb = -2;

    static final int Ub = 2;

    static final int max_iter = 1000;

    static final double beta = 1.5;

    static final double pa = 0.25; // discovery rate

    private static int[] get_sequence(@NotNull int n){

        int [] ret = new int[n];

        for (int i = 0; i < n; i++)
            ret[i] = i;

        return(ret);
    }

    private static void shuffle(@NotNull int[] array){
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }


    public void optimize(TestFunction fobj, int nvars) {

        double[][] nest = new double[n_nest][nvars];

        double[][] new_nest = new double[n_nest][nvars];

        double[] best_nest = new double[nvars];

        double[] fitness = new double[n_nest];

        double fmin = 10e10;

        double sigma = 0.0;

        Random rnd = new Random();

        Gamma gamma = new Gamma();


        if (nvars != 2)
            throw new IllegalArgumentException("Number of vars have to be 2 for Cuckoo search");

        // initialize
        sigma = (gamma.gamma(1 + beta) * Math.sin(Math.PI * beta / 2));
        sigma = Math.pow(sigma / (gamma.gamma((1 + beta) / 2) * beta * Math.pow(2, ((beta - 1) / 2))), (1 / beta));

        for (int i = 0; i < n_nest; i++) {
            for (int j = 0; j < nvars; j++) {
                nest[i][j] = Lb + (Ub - Lb) * rnd.nextDouble();
                new_nest[i][j] = Lb + (Ub - Lb) * rnd.nextDouble();
            }

            fitness[i] = Math.pow(10, 10);
        }

        for (int i = 0; i < n_nest; i++) {
            double fnew = fobj.evaluate(new_nest[i]);

            if (fnew <= fitness[i]) {
                fitness[i] = fnew;
                nest[i] = new_nest[i].clone();
            }
        }

        best_nest = nest[0].clone();
        fmin = fitness[0];

        int n_iter = 0;

        while (n_iter < max_iter || fmin > tolerance) {


            // get the cuckoos
            for (int i = 0; i < n_nest; i++) {
                double[] s = nest[i];

                // Levi flights
                double[] u = {sigma * rnd.nextGaussian(), sigma * rnd.nextGaussian()};
                double[] v = {rnd.nextGaussian(), rnd.nextGaussian()};

                double[] step = {u[0] / Math.pow(Math.abs(v[0]), (1 / beta)),
                        u[1] / Math.pow(Math.abs(v[1]), (1 / beta))};

                double[] step_size = {0.01 * step[0] * (s[0] - best_nest[0]),
                        0.01 * step[1] * (s[1] - best_nest[1])};

                s[0] = s[0] + step_size[0] * rnd.nextGaussian();
                s[0] = (s[0] < Lb) ? Lb : s[0];
                s[0] = (s[0] > Ub) ? Ub : s[0];

                s[1] = s[1] + step_size[1] * rnd.nextGaussian();
                s[1] = (s[1] < Lb) ? Lb : s[1];
                s[1] = (s[1] > Ub) ? Ub : s[1];

                new_nest[i] = s;
            }

            // get best nest
            for (int i = 0; i < n_nest; i++) {
                double fnew = fobj.evaluate(new_nest[i]);

                if (fnew < fitness[i]) {
                    fitness[i] = fnew;
                    nest[i] = new_nest[i].clone();
                }

                if (fitness[i] < fmin) {
                    fmin = fitness[i];
                    best_nest = nest[i];
                }
            }

            // empty nest
            int[] arr1 = get_sequence(n_nest);
            shuffle(arr1);

            int[] arr2 = get_sequence(n_nest);
            shuffle(arr2);

            for (int i = 0; i < n_nest; i++) {

                double r = rnd.nextGaussian();

                if (r > pa) {

                    double[] step_size = {rnd.nextDouble() * (nest[arr1[i]][0] - nest[arr2[i]][0]),
                            rnd.nextDouble() * (nest[arr1[i]][1] - nest[arr2[i]][1])};

                    new_nest[i][0] = nest[i][0] + r * step_size[0];
                    new_nest[i][0] = (new_nest[i][0] < Lb) ? Lb : new_nest[i][0];
                    new_nest[i][0] = (new_nest[i][0] > Ub) ? Ub : new_nest[i][0];

                    new_nest[i][1] = nest[i][1] + r * step_size[1];
                    new_nest[i][1] = (new_nest[i][1] < Lb) ? Lb : new_nest[i][1];
                    new_nest[i][1] = (new_nest[i][1] > Ub) ? Ub : new_nest[i][1];


                }

            }

            // evaluate
            for (int i = 0; i < n_nest; i++) {
                double fnew = fobj.evaluate(new_nest[i]);

                if (fnew < fitness[i]) {
                    fitness[i] = fnew;
                    nest[i] = new_nest[i].clone();
                }

                if (fitness[i] < fmin) {
                    fmin = fitness[i];
                    best_nest = nest[i];
                }
            }

            n_iter++;

        }

        System.out.println("Evaluations: " + n_iter);
        System.out.println("Best solution :");

        for (int i = 0; i < best_nest.length; i++) {
            System.out.println("x" + (i + 1) + ':' + Math.round(best_nest[i] * Math.pow(10, 5)) / Math.pow(10, 5));
        }
        System.out.println("Best fobj = " + Math.round(fmin * Math.pow(10, 3)) / Math.pow(10, 3));
    }

}
