package cs.algorithm;


import java.util.ArrayList;
import java.util.Random;

import cmaes2.fitness.IObjectiveFunction;
import imss.ConvertArrays;


public class CuckooSearchOpt extends OptimizationAlgorithm {

    private CSSolutionSet solutions;
    private int N_NESTS;					//number of nests (solutions)
    private double ABANDON_PROBABILITY;		//percentage of worst solutions discarded
    private int MAX_RANDOM_ATTEMPTS;
    private final int numVar;
    private OptimizationProblem optProb;//maximum attempts to create a new random solution
    private Random rand;
    private int numRuns;
    
    public CuckooSearchOpt() {
    	
		numVar=0;
		
    }
    
    
    public CuckooSearchOpt(int taillePop, OptimizationProblem probleme) {
    	this.N_NESTS = taillePop;
    	this.optProb=probleme;
    	ABANDON_PROBABILITY = 0.25;
		MAX_RANDOM_ATTEMPTS = 1000;
		numVar=probleme.getNumVar();
		rand = new Random();
		numRuns = 0;
    }
   
	public void solve(double[][] population) {
		//creation d'une liste de solution avec un certains nombre de nids (N_NESTS)
		solutions = new CSSolutionSet(N_NESTS, numVar);
				
		//initialisation aléatoire des solution
		solutions.initializeWithRandomSols(optProb);
		
		int tries;
			
			//retourne une solution aléatoire parmis la liste des solutions du probleme
			CSSolution i = solutions.getRandSol();
			CSSolution newSol;
			tries = 0;
			do {
				if (tries > MAX_RANDOM_ATTEMPTS) {
		   //       TODO  throw new Exception("Could not generate new random solution! Perhaps you should widen your constraints.");
		            System.out.printf("Could not generate new random solution! Perhaps you should widen your constraints.");
		            System.exit(1);;
				}
				//newsol == nouvelle solution calculée a partir de i
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
	}
	
	public double[][] listToArray(){
		 ArrayList<CSSolution> listeSolutions=solutions.getSolutions();
		 double[][] tabSolutions=new double[listeSolutions.size()][numVar];
		 
			for(int i=0;i<listeSolutions.size();i++){
				ArrayList<Double> vars=listeSolutions.get(i).getVars();
				for(int j=0;j<vars.size();j++){
					tabSolutions[i][j]=vars.get(j);
				}
			}
			return tabSolutions;
		}
	
	public void arrayToList(double[][] tabSolutions){
		for(int i=0;i<tabSolutions.length;i++){
			for(int j=0;j<tabSolutions[i].length;j++){
				solutions.getSolutions().get(i).getVars().set(j, tabSolutions[i][j]);
			}
		}
	}
	
	
	

	// TODO: prevent returning null. Instead throw an exception. 
	public CSSolutionSet getSolutions(OptimizationProblem optProb) {
	    solutions.sortByFitness(optProb);
        return solutions;
    }

	@Override
	public void solve(OptimizationProblem optProb) {
		// TODO Auto-generated method stub
		
	}
}
