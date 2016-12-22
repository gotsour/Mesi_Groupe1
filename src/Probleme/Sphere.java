package Probleme;

import cmaes2.fitness.IObjectiveFunction;

public class Sphere  implements IObjectiveFunction {

	@Override
	public double valueOf(double[] x) {
		double sum=0;
		for(int i=0;i<x.length;i++){
			sum+=x[i]*x[i];
		}
		return sum-450;
	}

	@Override
	public boolean isFeasible(double[] x) {
		// TODO Auto-generated method stub
		return true;
	}

}
