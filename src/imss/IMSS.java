package imss;

import cmaes.PseudoRandom;
import cs.algorithm.CSSolutionSet;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;

//maximum attempts to create a new random solution

public class IMSS {
	private CSSolutionSet solutions;
    protected final int N_NESTS;
	protected final int N_OPTIMIZATIONS;			//number of generations
	protected final double ABANDON_PROBABILITY;		//percentage of worst solutions discarded
	private final int MAX_RANDOM_ATTEMPTS;	
	private Solution QTable[][];
	 protected final int CS=0;
	 protected final int CMAES=1;
	 protected int SEcmaes;
	 protected int SEcs;
	
	
	public IMSS() {
    	N_NESTS = 15;
		N_OPTIMIZATIONS = 100000;
		ABANDON_PROBABILITY = 0.25;
		MAX_RANDOM_ATTEMPTS = 1000;
		QTable=new Solution[N_NESTS][2];
		SEcmaes=0;
		SEcs=0;
	
    }
	
	public void solve(OptimizationProblem optProb) {
		/*int NUM_VAR = optProb.getNumVar();
		//creation d'une liste de solution avec un certains nombre de nids (N_NESTS)
		solutions = new CSSolutionSet(N_NESTS, NUM_VAR);
				
		//  initialisation aléatoire des solution
		solutions.initializeWithRandomSols(optProb);*/
		
		for (int t = 0; t < N_OPTIMIZATIONS; t++) {
			if(SEcmaes>SEcs){
				SEcmaes=0;
				/*population_ = samplePopulation(); // get a new population of solutions

			      for(int i = 0; i < populationSize; i++) {

			        problem_.evaluate(population_.get(i));

			        counteval += populationSize;

			      }*/
				
				
				
			}
			else{
				
			}
			
		}
	}
}
