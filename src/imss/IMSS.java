package imss;
import cmaes.PseudoRandom;
import cs.algorithm.CSSolutionSet;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;
import cmaes2.*;
import cmaes2.fitness.IObjectiveFunction;
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
	
	public IMSS() {
		nombreVariable=3; //� ajouter dans le probleme
		taillePopulation = 100;
		nombreOptimisation = 1500;
		QTable=new double[taillePopulation][2];
		SEcmaes=0;
		SEcs=0;
		probleme=new Rosenbrock();
		cmaes=new CMAES(taillePopulation,nombreVariable,probleme);
    }
	
	public void solve() {
		for (int t = 0; t < nombreOptimisation; t++) {
			//if(SEcmaes>SEcs){
				SEcmaes=0;
				QTable=cmaes.solve(population, QTable);
				//recupere la nouvelle population g�n�r�e
				population=cmaes.getNewPopulation();
			/*}
			else{
			SEcs=0;
			//appel de l'algo CS
				
			}*/
		}
		cmaes.fin();
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
