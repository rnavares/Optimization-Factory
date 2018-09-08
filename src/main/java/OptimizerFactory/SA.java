package OptimizerFactory;

import TestFunctionFactory.*;
import java.util.Random;

public class SA extends Optimizer{

    // simulated annealing constants
    static final int LB = -2;

    static final int UB = 2;

    static final double T_init = 1.0;

    static final double T_min = 1.0e-10;

    static final double F_min = -1.0e+100; // minimum value of the function

    static final int max_rejections = 2350;

    static final int max_runs = 2250;

    static final int max_acceptance = 1250;

    static final int k = 1; // Boltzmann constant

    static final double alpha = 0.95;

    static final double E_norm = 1e-2; // energy norm

    public void optimize(TestFunction fobj, int nvars){

        int i = 0, j = 0, accept = 0, total_eval = 0;

        double T = T_init;

        double[] guess = new double[nvars];
        double[] new_sol = new double[nvars];
        double[] s = new double[nvars];

        Random rnd = new Random();

        int[] LowerBound = new int[nvars];

        int[] UpperBound = new int[nvars];

        for (int n = 0; n < nvars; n++){
            LowerBound[n] = LB;
            UpperBound[n] = UB;
        }


        for (int l = 0; l < s.length; l++) {
            guess[l] = LowerBound[l] + (UpperBound[l] - LowerBound[l]) * rnd.nextDouble();
        }

        double E_old =fobj.evaluate(guess);
        double E_new = E_old;

        double[] best_sol = guess.clone(); // initial guessed solution

        while ((T > T_min) && (j <= max_rejections) && (E_new > F_min)) {
            i++;

            if ((i >= max_runs) || (accept >= max_acceptance)){
                // Cooling schedule
                T = alpha * T;
                total_eval = total_eval + i;
                // reset the counters
                i = 1;
                accept = 1;
            }

            // evaluation at new locations
            for (int l = 0; l < s.length; l++) {
                s[l] = 0.01 * (UpperBound[l] - LowerBound[l]);
            }

            for (int l = 0; l < s.length; l++) {
                new_sol[l] = best_sol[l]  + s[l] * rnd.nextGaussian();
            }


            E_new = fobj.evaluate(new_sol);
            double DeltaE = E_new - E_old;

            // Update values
            if ((-1 * DeltaE) > E_norm){
                best_sol = new_sol.clone();
                E_old = E_new;
                accept = accept + 1;
                j = 0;
            }

            // Accept with small prob if not improved
            if ((DeltaE <= E_norm) && (Math.exp(-DeltaE / (k * T)) > rnd.nextDouble())){
                best_sol = new_sol.clone();
                E_old = E_new;
                accept = accept + 1;
            } else {
                j++;
            }
        }

        System.out.println("Evaluations: " + total_eval);
        System.out.println("Best solution :");

        for (i = 0; i < best_sol.length; i++){
            System.out.println("x" + (i+1) + ':' + Math.round(best_sol[i] *  Math.pow(10, 5)) / Math.pow(10, 5));
        }
        System.out.println("Best fobj = " + Math.round(E_old *  Math.pow(10, 3)) / Math.pow(10, 3));

    }



}
