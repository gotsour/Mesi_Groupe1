package imss;

import java.util.Scanner;

import Probleme.Rastrigin;
import Probleme.Rosenbrock;
import Probleme.Sphere;
import cmaes2.fitness.IObjectiveFunction;
import cs.algorithm.OptimizationProblem;
import cs.problem.RastriginMinProb;
import cs.problem.RosenbrockMinProb;
import cs.problem.SphereProb;

public class main {
	public static void main(String[] args) {
		
		
		OptimizationProblem problemeCS=new RosenbrockMinProb();
		IObjectiveFunction problemeCMAES=new Rosenbrock();
		
		Scanner sc = new Scanner(System.in);
	
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le nombre de variables : ");
		int nombreVariable = sc.nextInt();

		System.out.println("Veuillez saisir la taille de la population : ");
		int taillePopulation = sc.nextInt();

		System.out.println("Veuillez saisir le nombre d'optimisations : ");
		int nombreOptimisation = sc.nextInt();
		
		System.out.println("Veuillez saisir un probleme : \n 1 - RosenBrock \n 2 - Rastrigin \n 3 - Sphere");
		int str = sc.nextInt();
		
		switch(str){
			case 1 :{
				problemeCS=new RosenbrockMinProb(nombreVariable);
				problemeCMAES=new Rosenbrock();
			}
			case 2 :{
				problemeCS=new RastriginMinProb(nombreVariable);
				 problemeCMAES=new Rastrigin();
			}
			case 3 :{
				problemeCS=new SphereProb(nombreVariable);
				 problemeCMAES=new Sphere();
			}
		}
		
		System.out.println("borne suppérieur du rang");
		int borneSup = sc.nextInt();
		
		System.out.println("borne inférieur du rang");
		int borneInf = sc.nextInt();
			
		
		IMSS test= new IMSS(nombreVariable,taillePopulation,nombreOptimisation,problemeCMAES,problemeCS);
		test.initPopulation(borneInf, borneSup);
		test.solve();
		
		
	     
		//affichange des solutions
		for(int i=0;i<test.getPopulation().length;i++){
			System.out.print(" Population : ");
			for(int j=0;j<test.getPopulation()[i].length;j++){
				System.out.print(" "+test.getPopulation()[i][j]);
			}
			System.out.println(" fitness : "+test.getFitness()[i]);
		}
	
	}

}
