package imss;

import java.util.ArrayList;

import Probleme.Rosenbrock;
import cmaes2.fitness.IObjectiveFunction;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;
import cs.problem.RosenbrockMinProb;

public class main {
	public static void main(String[] args) {
		
		int nombreVariable=15;
		int taillePopulation=100;
		int nombreOptimisation=1000;
		OptimizationProblem problemeCS=new RosenbrockMinProb(nombreVariable);
		IObjectiveFunction problemeCMAES=new Rosenbrock();
		IMSS test= new IMSS(nombreVariable,taillePopulation,nombreOptimisation,problemeCMAES,problemeCS);
		test.initPopulation(-100, 100);
		test.solve();
		
		
	      
		
		//affichange des solutions
		for(int i=0;i<test.getPopulation().length;i++){
			for(int j=0;j<test.getPopulation()[i].length;j++){
				System.out.print(" "+test.getPopulation()[i][j]);
			}
			System.out.println("fitness : "+test.getFitness()[i]);
		}
	
	}

}
