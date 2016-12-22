package cs.problem;

import java.util.ArrayList;

import cs.algorithm.Constraint;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;

public class SphereProb extends OptimizationProblem {
	int N;
	public SphereProb(int numVar){
		N = numVar;
		
        for(int i=0; i<N; i++) {
        	this.constraints.add(new Constraint(i, -100, 100));
        }
	}
	@Override
	public int getNumVar() {
		return N;
	}

	@Override
	public double fitness(Solution s) {
		ArrayList<Double> vars = s.getVars();
		double fitness = 0;
		for (int i = 0; i < N; i++){
			double gene = vars.get(i);
			fitness += gene*gene;
		}
		 return fitness-450;
	}

	@Override
	public boolean withinCustomConstraints(Solution s) {
		return true;
	}

	@Override
	public String solToString(Solution s) {
		return null;
	}

}
