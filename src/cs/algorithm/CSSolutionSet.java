package cs.algorithm;


import java.util.ArrayList;
import java.util.Random;

import imss.ConvertArrays;


public class CSSolutionSet extends SolutionSet {
    
    private ArrayList<CSSolution> solutions;
    private Random rand;
    private int numNests;
    
    public CSSolutionSet(int numNests, int numVars) {
        this.numNests = numNests;
        super.N_SOL = numNests;
        this.rand = new Random();
        this.solutions = new ArrayList<CSSolution>(numNests);
        System.out.println("creation liste num var :"+numVars);
        for (int i = 0; i < numNests; i++) {
            this.solutions.add(i, new CSSolution(numVars));
            
        }
        
        super.solutions = solutions;
    }
   
    public void initializeWithRandomSols(OptimizationProblem optProb) {
        for (CSSolution sol : this.solutions) {
            sol.setAsRandSol(optProb);
            sol.evalFitness(optProb);
        }
    }
    
    public int getNumSols() {
        return numNests;
    }
    
    public void replace(int j, CSSolution sol) {
        this.solutions.set(j, sol);
    }
    
    public CSSolution getRandSol() {
        return this.solutions.get(rand.nextInt(this.getNumSols()));
    }
    
    public void abandonWorstSols(OptimizationProblem optProb, double abandonmentRatio) {
    	this.sortByFitness(optProb);
        int numToAbandon = (int) (abandonmentRatio * this.numNests);
        int numToKeep = this.numNests - numToAbandon;
        for (int i = numToKeep; i < numNests; i++) {
            solutions.get(i).setAsRandSol(optProb); //modifier appeler la fonction 5 pas rapeller
            solutions.get(i).evalFitness(optProb);
        }
    }
    
    public ArrayList<CSSolution> getSolutions(){
    	return solutions;
    }
    public int getNumNests(){
    	return numNests;
    }
    public void setSolutions(ArrayList<CSSolution> data){
    	this.solutions=data;
    }
    
    //TODO
    public void addDataToCSSolutionSet(double[][] data){
		//CSSolutionSet set=new CSSolutionSet(oldSet.getNumNests(), oldSet.)
		if(solutions==null) solutions=new ArrayList<>();
		for(int i=0;i<data.length;i++){
			
			ArrayList<Double> line=ConvertArrays.convertToOneDimensionArrayList(data[i]);
			CSSolution sol=new CSSolution(line);
			solutions.add(sol);
		}
		
		
	}
}
