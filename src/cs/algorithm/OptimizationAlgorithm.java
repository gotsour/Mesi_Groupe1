package cs.algorithm;


import java.util.ArrayList;


public abstract class OptimizationAlgorithm {
	//for collecting data - delete afterwards
	public final int NUM_DATA = 200;
    protected ArrayList<Double> fitnesses;
    
    public ArrayList<Double> getFitnesses() {
    	return fitnesses;
    }
	
	public abstract void solve(OptimizationProblem optProb);
    public abstract SolutionSet getSolutions(OptimizationProblem optProb);
}
