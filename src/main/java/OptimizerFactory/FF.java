package OptimizerFactory;

import TestFunctionFactory.*;
import java.util.Arrays;
import java.util.Random;

public class FF extends Optimizer{

    static final int n_fireflies = 12;

    static final int max_generation = 2000;

    static final int[] range = {-2, 2, -2, 2};

    static final double alpha0 = 1.0;

    static final double gamma = 1.0;

    static final double delta = 0.97;

    public void optimize(TestFunction fobj, int nvars){

        double alpha = alpha0;

        double xrange = (double)(range[1] - range[0]);
        double yrange = (double)(range[3] - range[2]);

        Random rnd = new Random();

        double[] xn = new double[n_fireflies];
        double[] yn = new double[n_fireflies];
        double[] light_n = new double[n_fireflies];
        double[] solutions = new double[n_fireflies];

        double[] x0 = new double[n_fireflies];
        double[] y0 = new double[n_fireflies];
        double[] light_0 = new double[n_fireflies];

        // initialize fireflies positions
        for (int i = 0; i < n_fireflies; i++){
            xn[i] = rnd.nextDouble() * xrange + range[0];
            yn[i] = rnd.nextDouble() * yrange + range[2];
            light_n[i] = 0.0;
        }

        for (int i = 0; i < max_generation; i++){

            for (int j = 0; j < n_fireflies; j++){
                double[] sol = {xn[j], yn[j]};

                solutions[j] = fobj.evaluate(sol);
            }

            double[] sorted_solutions = solutions.clone();

            Arrays.sort(sorted_solutions);

            for (int k = 0; k < sorted_solutions.length; k++){
                for (int l = 0; l < xn.length; l++) {
                    if (solutions[l] == sorted_solutions[k]){
                        x0[k] = xn[l];

                        y0[k] = yn[l];

                        light_0[k] = light_n[l];

                    }

                }
            }

            xn = x0.clone();

            yn = y0.clone();

            light_n = light_0.clone();

            // move the fireflies
            for(int j = 0; j < xn.length; j++){
                for(int k = 0; k < x0.length; k++){
                    double r = Math.sqrt(Math.pow(xn[j] - x0[k],2) + Math.pow(yn[j] - y0[k],2));

                    if(light_n[j] < light_0[k]){
                        double beta0 = 1.0;
                        double beta = beta0 * Math.exp(-1 * gamma * Math.pow(r, 2));

                        xn[j] = xn[j] * (1 - beta) + x0[k] * beta + alpha * (rnd.nextDouble() - 0.5);

                        yn[j] = yn[j] * (1 - beta) + y0[k] * beta + alpha * (rnd.nextDouble() - 0.5);

                    }
                }
            }

            for (int j =0; j < xn.length; j++){
                if(xn[j] < range[0]) xn[j] = range[0];
                if(xn[j] > range[1]) xn[j] = range[1];
                if(yn[j] < range[2]) yn[j] = range[2];
                if(yn[j] > range[3]) yn[j] = range[3];
            }

            // reduce randomness
            alpha = alpha * delta;

        }

        System.out.println("x1: " + Math.round(x0[0] *  Math.pow(10, 5)) / Math.pow(10, 5));
        System.out.println("x2: " + Math.round(y0[0] *  Math.pow(10, 5)) / Math.pow(10, 5));
        double[] sol_best = {x0[0], y0[0]};
        System.out.println("Best fobj = " + Math.round(fobj.evaluate(sol_best) *  Math.pow(10, 3)) / Math.pow(10, 3));


    }
}
