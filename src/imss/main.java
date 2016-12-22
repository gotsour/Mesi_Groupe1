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
		
		int nombreVariable = 0;
		int taillePopulation = 0;
		int nombreOptimisation = 0;
		int borneSup = 0;
		int borneInf = 0;
		OptimizationProblem problemeCS=new RosenbrockMinProb();
		IObjectiveFunction problemeCMAES=new Rosenbrock();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("répondre aux questions suivantes pour choisir le problème et les differents paramètres si vous tapez 0 la valeur sera celle par défaut");
		System.out.println("Veuillez saisir le nombre de variables (defaut : 15): ");
		
		nombreVariable=sc.nextInt();
		if(nombreVariable == 0){
			nombreVariable =15;
		}

		System.out.println("Veuillez saisir la taille de la population (defaut : 50): ");
		taillePopulation = sc.nextInt();
		if( taillePopulation == 0){
			taillePopulation = 50;
		}

		System.out.println("Veuillez saisir le nombre d'optimisations (defaut : 100000): ");
		nombreOptimisation = sc.nextInt();
		if(nombreOptimisation == 0){
			nombreOptimisation = 100000;
		}		
		
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
		
		System.out.println("borne suppérieur du rang (defaut : 100):");
		borneSup = sc.nextInt();
		if(borneSup == 0){
			borneSup = 100;
		}
		
		
		System.out.println("borne inférieur du rang (defaut : -100):");
		borneInf = sc.nextInt();
		if(borneInf == 0){
			borneInf = -100;
		}
			
		
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
