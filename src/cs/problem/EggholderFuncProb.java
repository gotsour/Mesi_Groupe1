package cs.problem;

import java.util.ArrayList;

import cs.algorithm.Constraint;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;


/*
 * Eggholder function
 * http://en.wikipedia.org/wiki/File:Eggholder_function.pdf
 * 
 * Minimum: f(512,404.2319)=-959.6407
 */
public class EggholderFuncProb extends OptimizationProblem {

	//Default constructor for setting constraints
	public EggholderFuncProb() {
		this.constraints.add(new Constraint(0, -512, 512));
		this.constraints.add(new Constraint(1, -512, 512));
	}
	
	@Override
	public int getNumVar() {
		return 2;
	}

	private double eggholderFunction(Solution s) {
		ArrayList<Double> vars = s.getVars();
		double x = vars.get(0);
		double y = vars.get(1);
		return -(y+47)*Math.sin(Math.sqrt(Math.abs(y+x/2+47)))
					-x*Math.sin(Math.sqrt(Math.abs(x-(y+47))));
	}
	
	@Override
	public double fitness(Solution s) {
		return -eggholderFunction(s);
	}

	@Override
	public boolean withinCustomConstraints(Solution s) {
		return true;
	}

	@Override
	public String solToString(Solution s) {
		ArrayList<Double> vars = s.getVars();
	    return String.format("x = %f\ny = %f\nf(x,y) = %f\n", 
	    		vars.get(0), vars.get(1),s.getFitness());
	}
	

}
