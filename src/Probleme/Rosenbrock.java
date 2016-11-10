package Probleme;

import cmaes2.fitness.IObjectiveFunction;

public class Rosenbrock implements IObjectiveFunction { // meaning implements methods valueOf and isFeasible
	public double valueOf (double[] x) {
		double res = 0;
		for (int i = 0; i < x.length-1; ++i)
			res += 100 * (x[i]*x[i] - x[i+1]) * (x[i]*x[i] - x[i+1]) + 
			(x[i] - 1.) * (x[i] - 1.);
		return res;
	}
	public boolean isFeasible(double[] x) {return true; } // entire R^n is feasible
}
