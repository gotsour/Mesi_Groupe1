package Probleme;

import cmaes2.fitness.IObjectiveFunction;

/* 
 * f(x)=A*n + Sum_1^n [x_i^2 - A*Cos(2*Pi*x_i)] 
 */

public class Rastrigin implements IObjectiveFunction {
	
	private final int A=10;

	@Override
	public double valueOf(double[] x) {
		double sum = A*x.length;
		
		for (int i = 0; i < x.length; ++i) {
			sum += Math.pow(x[i], 2) - A * Math.cos(2 * Math.PI * x[i]);
		}
		return sum-330;
	}

	@Override
	public boolean isFeasible(double[] x) {
		return true;
	}

}
