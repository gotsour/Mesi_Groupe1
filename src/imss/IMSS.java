package imss;
import cmaes.PseudoRandom;
import cs.algorithm.CSSolutionSet;
import cs.algorithm.CuckooSearchOpt;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;
import cs.problem.RosenbrockMinProb;
import tools.QLearning;
import cmaes2.*;
import cmaes2.fitness.IObjectiveFunction;

import java.util.Random;

import Probleme.*;

public class IMSS {
	private CSSolutionSet solutions;
    protected final int taillePopulation;
    protected final int nombreVariable;
	protected final int nombreOptimisation;			//number of generations	
	protected double QTable[][];
	protected double population[][];
	protected double fitness[];
	protected final int CS=0;
	protected final int CMAES=1;
	protected double SEcmaes;
	protected double SEcs;
	protected CMAES cmaes;
	protected CuckooSearchOpt cs;
	protected IObjectiveFunction probleme;
	QLearning ql;
	protected final float gamma = 0.7f;
	protected final float ro = 0.5f;
	
	public IMSS() {
		nombreVariable=3; //à ajouter dans le probleme
		taillePopulation = 10;
		nombreOptimisation = 1000;
		QTable=new double[taillePopulation][2];
		SEcmaes=0;
		SEcs=0;
		probleme=new Rosenbrock();
		ql=new QLearning(gamma,ro);
		cmaes=new CMAES(taillePopulation,nombreVariable,probleme,ql);
		RosenbrockMinProb problemeCS=new RosenbrockMinProb(nombreVariable);
		System.out.println("num var "+problemeCS.getNumVar());
		cs= new CuckooSearchOpt(taillePopulation,problemeCS,ql);
		
		population = new double[taillePopulation][nombreVariable];
		fitness= new double[taillePopulation];
    }
	
	public void initPopulation(double borneMin, double borneMax) {
		for ( int i = 0 ; i < taillePopulation ; i++) {
			for (int j = 0 ; j < nombreVariable ; j++) {
				Random r = new Random();
				double randomValue = borneMin + (borneMax - borneMin) * r.nextDouble();
				population[i][j] = randomValue;
			}
		}
		
		for ( int i = 0 ; i < taillePopulation ; i++) {
			fitness[i]=probleme.valueOf(population[i]);
		}
		
	}
	
	public void affichePop() {
		for(int i=0;i<population.length;i++){
			for(int j=0;j<population[i].length;j++){
				System.out.print(population[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	public void solve() {
		for (int t = 0; t < nombreOptimisation; t++) {
			if(SEcmaes>SEcs){
				affichePop();
				QTable=cmaes.solve(population,fitness, QTable);
				//recupere la nouvelle population générée
				population=cmaes.getNewPopulation();
				fitness=cmaes.getFitness();
				SEcmaes=QTableSUMforCMAES();
				System.out.println("+++++++++++++CMAES "+SEcmaes+" cs "+cs);
				affichePop();
			}
			else{
				/**voir pb CS */
				QTable=cs.solve(population,fitness, QTable);
				population=cs.getPopulation();
				fitness=cs.getFitness();
				SEcs=QTableSUMforCS();
				
				System.out.println("+++++++++++++CS "+SEcs +" cmaes "+SEcmaes);
				
				
			}
		}
		//cmaes.fin();
	}

	public double[][] getPopulation() {
		return population;
	}
	
	public double[][] getQTable() {
		return QTable;
	}

	public void setQTable(double[][] qTable) {
		QTable = qTable;
	}
	
	/**
	 * SUM QTable for CMAES Algorithm
	 * @return La somme des valeurs contenues dans QTable pour l'algo CMAES 
	 */
	public double QTableSUMforCMAES() {
		double sum = 0;
		for (int i = 0 ; i < taillePopulation ; i++) {
			sum += QTable[i][1];
		}
		return sum;
	}
	
	/**
	 * SUM QTable for CS Algorithm
	 * @return La somme des valeurs contenues dans QTable pour l'algo CS
	 */
	public double QTableSUMforCS() {
		double sum = 0;
		for (int i = 0 ; i < taillePopulation ; i++) {
			sum += QTable[i][0];
		}
		return sum;
	}
}
