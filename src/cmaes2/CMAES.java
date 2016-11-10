package cmaes2;

import cmaes2.fitness.IObjectiveFunction;
import tools.QLearning;
import Probleme.*;
import cmaes2.CMAEvolutionStrategy;




public class CMAES {
	CMAEvolutionStrategy cma;
	double[] fitness;
	IObjectiveFunction fitfun;
	double[][] newPopulation;
	QLearning ql;
	
	//init
	public CMAES(int taillePop,int nombreVariable, IObjectiveFunction calculfitness, QLearning ql){
		fitfun=calculfitness;
		
		cma = new CMAEvolutionStrategy();
		cma.readProperties(); // read options, see file CMAEvolutionStrategy.properties
		cma.setDimension(nombreVariable); // overwrite some loaded properties => nombre de variables
		cma.setInitialX(0.05); // in each dimension, also setTypicalX can be used
		cma.setInitialStandardDeviation(0.2); // also a mandatory setting 
		cma.options.stopFitness = 1e-14;       // optional setting
		// initialize cma and get fitness array to fill in later
		fitness = cma.init();  // new double[cma.parameters.getPopulationSize()];
	
		// initial output to files
		cma.writeToDefaultFilesHeaders(0); // 0 == overwrites old files
		
		this.ql = ql;
	}
	
	
	
	public double[][] solve(double[][] population,double[][] Q ){
		
			newPopulation = cma.samplePopulation2(population); // get a new population of solutions from the old population
			for(int i = 0; i < newPopulation.length; ++i) {    // pour chaque element de la nouvelle population	
		    	                            
				while (!fitfun.isFeasible(newPopulation[i]))     //   verification des borne de la population 
					newPopulation[i] = cma.resampleSingle(i);    //   initialX is feasible and initialStandardDeviations are  
		                                               //   sufficiently small to prevent quasi-infinite looping here
		        // remplissage QTable
				Q=ql.UpdateQTable(Q,1, fitness[i], fitfun.valueOf(newPopulation[i]),i);
				// compute fitness/objective value	
				fitness[i] = fitfun.valueOf(newPopulation[i]); // modifie le fitness de chaque solution de la population
				
			}
			cma.updateDistribution(fitness);         // pass fitness array to update search distribution
			// output to files and console 
			cma.writeToDefaultFiles();
			int outmod = 150;
			if (cma.getCountIter() % (15*outmod) == 1)
				cma.printlnAnnotation(); // might write file as well
			if (cma.getCountIter() % outmod == 1)
				cma.println(); 
			// retourne QTable modifié
			return Q;
	}
	
	public void fin(){
		// evaluate mean value as it is the best estimator for the optimum
				cma.setFitnessOfMeanX(fitfun.valueOf(cma.getMeanX())); // updates the best ever solution 
				// final output
				cma.writeToDefaultFiles(1);
				cma.println();
				cma.println("Terminated due to");
				for (String s : cma.stopConditions.getMessages())
					cma.println("  " + s);
				cma.println("best function value " + cma.getBestFunctionValue() 
						+ " at evaluation " + cma.getBestEvaluationNumber());
					
	}



	public double[][] getPopulation() {
		return cma.population;
	}

	public double[][] getNewPopulation() {
		return newPopulation;
	}
	
	
	
}
	



