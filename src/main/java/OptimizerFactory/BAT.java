package OptimizerFactory;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collections;

import java.util.Random;
import TestFunctionFactory.*;

public class BAT extends Optimizer{

    static final int LB = -2;

    static final int UB = 2;

    static final int n = 20; // populations size

    static final int n_gen = 1000; // number of generations

    static final double A = 0.5; // loudness

    static final double r = 0.5; // pulse rate

    static final int Qmin = 0; // frequency minimum

    static final int Qmax = 2; // frequency max

    private double[][] apply_limits(@NotNull double[][] s){
        for (int i = 0; i < s.length; i++)
            for (int j = 0; j < s[0].length; j++) {
                if (s[i][j] < LB) s[i][j] = LB;

                if (s[i][j] > UB) s[i][j] = UB;
            }

        return(s);
    }

    public void optimize(TestFunction fobj, int nvars) {

        double[][] population = new double[n][nvars];

        double[][] population_aux = new double[n][nvars];

        double[] fitness = new double[n];

        double fitness_new;

        double[] Q = new double[n]; // frequencies

        double[][] V = new double[n][nvars]; // velocities

        int[] LowerBound = new int[nvars];

        int[] UpperBound = new int[nvars];

        double fmin = 1.0e10;

        double[] best = new double[nvars];

        int n_iter = 0; // number of iterations

        Random rnd = new Random();

        // initialization
        for (int i = 0; i < nvars; i++){
            LowerBound[i] = LB;
            UpperBound[i] = UB;

        }

        for (int i = 0; i < n; i++){ // initialize population
            Q[i] = 0;

            for (int j = 0; j < nvars; j++){
                population[i][j] = LowerBound[j] + (UpperBound[j] - LowerBound[j]) * rnd.nextDouble(); // random walk
                V[i][j] = 0;
            }

            fitness[i] = fobj.evaluate(population[i]);
        }


        for(int i  = 0; i < n; i++){
            if (fitness[i] < fmin){
                fmin = fitness[i];
                best = population[i].clone();
            }
        }

        for (int gen = 0; gen < n_gen; gen++){ // generarions

            for (int i = 0; i < n; i++){ // loop individuals
                Q[i] = Qmin + (Qmax - Qmin) * rnd.nextDouble();

                for(int j =0; j < nvars; j++){
                    V[i][j] = V[i][j] + (population[i][j] - best[j]) * Q[i];

                    population_aux[i][j] = population[i][j] + V[i][j];
                }

                population = apply_limits(population).clone();

                // pulse rate
                if (rnd.nextDouble() > r){
                    // factor 0.001 limits the step sizes of random walks
                    for (int l = 0; l < nvars; l++)
                        population_aux[i][l] = best[l] + 0.001 * rnd.nextGaussian();

                }

                fitness_new = fobj.evaluate(population_aux[i]);

                // Update if the solution improves, or not too loud
                if ((fitness_new < fitness[i]) && (rnd.nextDouble() < A)){
                    population[i] = population_aux[i].clone();
                    fitness[i] = fitness_new;
                }

                if (fitness_new < fmin){
                    best = population_aux[i];
                    fmin = fitness_new;
                }

            }

            n_iter++;

        }

        System.out.println("Evaluations: " + n_iter);
        System.out.println("Best solution :");

        for (int i = 0; i < best.length; i++){
            System.out.println("x" + (i+1) + ':' +  Math.round(best[i] *  Math.pow(10, 5)) / Math.pow(10, 5));
        }
        System.out.println("Best fobj = " + Math.round(fmin *  Math.pow(10, 3)) / Math.pow(10, 3));

    }






}
