package cs.algorithm;


import java.util.ArrayList;
import java.util.Random;

import cmaes2.fitness.IObjectiveFunction;
import imss.ConvertArrays;
import tools.QLearning;


public class CuckooSearchOpt extends OptimizationAlgorithm {

    private CSSolutionSet solutions;
    private int N_NESTS;					//number of nests (solutions)
    private double ABANDON_PROBABILITY;		//percentage of worst solutions discarded
    private int MAX_RANDOM_ATTEMPTS;
    private final int numVar;
    private OptimizationProblem optProb;//maximum attempts to create a new random solution
    private Random rand;
    private int numRuns;
    private QLearning ql;
    private double fitness[];
    private double population[][];
    
    public CuckooSearchOpt() {
    	
		numVar=0;
		
    }
    
    
    public CuckooSearchOpt(int taillePop, OptimizationProblem probleme,QLearning ql) {
    	this.N_NESTS = taillePop;
    	this.optProb=probleme;
    	ABANDON_PROBABILITY = 0.25;
		MAX_RANDOM_ATTEMPTS = 10;
		numVar=probleme.getNumVar();
		rand = new Random();
		numRuns = 0;
		this.ql=ql;
		fitness=new double[taillePop];
		population=new double[taillePop][numVar];
		solutions = new CSSolutionSet(N_NESTS, numVar);
    }
   
	public double[][] solve(double[][] oldpopulation,double[] oldfitness, double[][] Q) {
		//creation d'une liste de solution avec un certains nombre de nids (N_NESTS)
		fitness=oldfitness;
		population=oldpopulation;
		//ajoute nouvelle population
		arrayToList(oldpopulation,solutions);
		
		int tries;
		double newFitness=0;	
			//retourne une solution aléatoire parmis la liste des solutions du probleme
		
		for(int k=0;k<population.length;k++){
			//oldFitness
			solutions.getSolutions().get(k).evalFitness(optProb);
			CSSolution i = solutions.getSolutions().get(k);
			
			CSSolution newSol;
			tries = 0;
			do {
				if (tries > MAX_RANDOM_ATTEMPTS) {
		   //       TODO  throw new Exception("Could not generate new random solution! Perhaps you should widen your constraints.");
		            System.out.printf("Could not generate new random solution! Perhaps you should widen your constraints.");
		            System.exit(1);;
				}
				//newsol == nouvelle solution calculée a partir de i
    		    newSol = i.randomWalk(optProb, "levy");
    		    /* If the random walk resulted in a solution that is not within constraints,
    		     * then try another random walk from the original solution. */
    		    tries++;
    		    
    		    //while tant qu'on ne respecte pas les contraintes
			} while(!optProb.withinConstraints(newSol));
			newFitness=optProb.fitness(newSol);
			System.out.println("new fitness "+newFitness+" old fitness "+fitness[k] );//+" population : "+this.population[k][0]+" "+this.population[k][1]+" "+this.population[k][2] );
		    if ( newFitness < fitness[k]) {
		    	solutions.replace(k, newSol);
		    	solutions.getSol(k).evalFitness(optProb);;
		    	Q=ql.UpdateQTable(Q,0,  fitness[k], newFitness,k);
		    	fitness[k]=newFitness;
		    	population[k]=listToArray(k);
		    	//System.out.println("change fitness ");
		    }
		    else{
		    Q=ql.UpdateQTable(Q,0,  fitness[k], fitness[k],k);
		    }
		    

		        
		    /*
		     * 
		     * à faire
		     * 
		     */
		    // Resets worst solutions to random values.
		    //solutions.abandonWorstSols(optProb, ABANDON_PROBABILITY);
		    
		 
		    
		}
		
		
		return Q;
	}
	
	public double[] listToArray(int k){
		 ArrayList<CSSolution> listeSolutions=solutions.getSolutions();
		 double[] tabSolutions=new double[numVar];
		 
			
				ArrayList<Double> vars=listeSolutions.get(k).getVars();
				for(int j=0;j<numVar;j++){
					tabSolutions[j]=vars.get(j);
				}
			
			return tabSolutions;
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
	
	public void arrayToList(double[][] tabSolutions,CSSolutionSet sol){
		System.out.println("num var "+tabSolutions[0].length);
		for(int i=0;i<tabSolutions.length;i++){
			//System.out.println("ligne ++:"+sol.getSolutions().get(0).getVars().size());
			for(int j=0;j<tabSolutions[i].length;j++){
			//ArrayList<Double> vars = solutions.getSolutions().get(i).getVars();
			//System.out.println("length "+vars.size());
			//sol.getSolutions().get(i).getVars().set(0, 0.3);
			//ArrayList<Double> solu=solutions.getSolutions().get(i).getVars();
			///solu.set(0, 0.33);
			//solu.set(0, 6.3);
			//System.out.println(solu.toString());
			
			
			
			//solutions.getSol(i).s
			solutions.getSolutions().get(i).getVars().set(j, tabSolutions[i][j]);
			}
			
		}
	}
	
	public double[] getFitness(){
		return fitness;
	}
	
	public double[][] getPopulation(){
		return population;
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
