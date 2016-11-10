package cs.algorithm;


import java.util.Random;

import imss.ConvertArrays;


public class CuckooSearchOpt extends OptimizationAlgorithm {

    private CSSolutionSet solutions;
    private int N_NESTS;					//number of nests (solutions)
    private int N_OPTIMIZATIONS;			//number of generations
    private double ABANDON_PROBABILITY;		//percentage of worst solutions discarded
    private int MAX_RANDOM_ATTEMPTS;			//maximum attempts to create a new random solution
    
    public CuckooSearchOpt( ) {
    	N_NESTS = 15;
    	//56000
		N_OPTIMIZATIONS = 100;
		ABANDON_PROBABILITY = 0.25;
		MAX_RANDOM_ATTEMPTS = 1000;
    }
    
    public CuckooSearchOpt(int nNests, int nOptimisations, double abandonProb, int maxRandomAttempts ) {
    	this.N_NESTS = nNests;
		this.N_OPTIMIZATIONS = nOptimisations;
		this.ABANDON_PROBABILITY= abandonProb;
		MAX_RANDOM_ATTEMPTS = maxRandomAttempts;
    }
   
	public void solve(OptimizationProblem optProb) {
		/* 
		 * Objective function 
		 * Generate an initial population of host nests; 
		 * While (t<MaxGeneration) or (stop criterion)
		 *    Get a cuckoo randomly (say, i) and replace its solution by performing L�vy flights;
		 *    Evaluate its quality/fitness F_i
		 *          [For maximization, -F_i];
		 *    Choose a nest among n (say, j) randomly;
		 *    if (F_i > F_j),
		 *           Replace j by the new solution;
		 *    end if
		 *    A fraction (P_a) of the worse nests are abandoned and new ones are built;
		 *    Keep the best solutions/nests;
		 *    Rank the solutions/nests and find the current best;
		 *    Pass the current best solutions to the next generation;
		 * end while
		*/

		//for collecting data - delete afterwards
		//fitnesses = new ArrayList<Double>(N_OPTIMIZATIONS/NUM_DATA+1);
		
		//nombre de variables utilis�es dans le probleme
		int NUM_VAR = optProb.getNumVar();
		
		//creation d'une liste de solution avec un certains nombre de nids (N_NESTS)
		solutions = new CSSolutionSet(N_NESTS, NUM_VAR);
				
		//initialisation al�atoire des solution
		solutions.initializeWithRandomSols(optProb);
		
		Random rand = new Random();

		int numRuns = 0;
		int tries;
		for (int t = 0; t < N_OPTIMIZATIONS; t++) {
			
			//retourne une solution al�atoire parmis la liste des solutions du probleme
			CSSolution i = solutions.getRandSol();
			CSSolution newSol;
			tries = 0;
			do {
				if (tries > MAX_RANDOM_ATTEMPTS) {
		   //       TODO  throw new Exception("Could not generate new random solution! Perhaps you should widen your constraints.");
		            System.out.printf("Could not generate new random solution! Perhaps you should widen your constraints.");
		            System.exit(1);;
				}
				//newsol == nouvelle solution calcul�e a partir de i
    		    newSol = i.randomWalk(optProb, "");
    		    /* If the random walk resulted in a solution that is not within constraints,
    		     * then try another random walk from the original solution. */
    		    tries++;
    		    
    		    //while tant qu'on ne respecte pas les contraintes
			} while(!optProb.withinConstraints(newSol));
		    
		    int j = rand.nextInt(solutions.getNumSols());
		    Solution jSol = solutions.getSol(j); //correspond au y newsol correspond au x
		    
		    // TODO: use solutions' instance data to get the fitness values to avoid unnecessary calculations
		    if (optProb.fitness(newSol) > jSol.getFitness()) {
		    	solutions.replace(j, newSol);
		    	solutions.getSol(j).evalFitness(optProb);
		    }
		        
		    
		    // Resets worst solutions to random values.
		    solutions.abandonWorstSols(optProb, ABANDON_PROBABILITY);
		    
		    numRuns++;
		    solutions.setNumRuns(numRuns);
		    
		    //for collecting data - TODO: delete afterwards
		    /*
		    if((t+1)%(N_OPTIMIZATIONS/NUM_DATA)==0) {
		    	fitnesses.add(new Double(solutions.getMostFitSolution(optProb).getFitness()));
		    }*/
		    System.out.println("nb var : "+solutions.getSolutions().get(1).getVars().size());
		    System.out.println("nb solution : "+solutions.getSolutions().size());
		}
		
	}
	public double[][] solve(double[][] population){
		
		int num_var=population[0].length;
		
		
		
		
		
		return null;
	}
	// TODO: prevent returning null. Instead throw an exception. 
	public CSSolutionSet getSolutions(OptimizationProblem optProb) {
	    solutions.sortByFitness(optProb);
        return solutions;
    }
}
