package imss;
import cmaes.PseudoRandom;
import cs.algorithm.CSSolutionSet;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;
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
	protected final int CS=0;
	protected final int CMAES=1;
	protected int SEcmaes;
	protected int SEcs;
	protected CMAES cmaes;
	protected IObjectiveFunction probleme;
	QLearning ql;
	protected final float gamma = 0.7f;
	protected final float ro = 0.5f;
	
	public IMSS() {
		nombreVariable=3; //à ajouter dans le probleme
		taillePopulation = 100;
		nombreOptimisation = 1500;
		QTable=new double[taillePopulation][2];
		SEcmaes=0;
		SEcs=0;
		probleme=new Rosenbrock();
		ql=new QLearning(gamma,ro);
		cmaes=new CMAES(taillePopulation,nombreVariable,probleme,ql);
    }
	
	public void initPopulation(double borneMin, double borneMax) {
		population = new double[taillePopulation][nombreVariable];
		for ( int i = 0 ; i < taillePopulation ; i++) {
			for (int j = 0 ; j < nombreVariable ; j++) {
				Random r = new Random();
				double randomValue = borneMin + (borneMax - borneMin) * r.nextDouble();
				population[i][j] = randomValue;
			}
		}
	}
	
	public void solve() {
		for (int t = 0; t < nombreOptimisation; t++) {
			//if(SEcmaes>SEcs){
				SEcmaes=0;
				QTable=cmaes.solve(population, QTable);
				//recupere la nouvelle population générée
				population=cmaes.getNewPopulation();
			/*}
			else{
			SEcs=0;
			//appel de l'algo CS
				
			}*/
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
}
