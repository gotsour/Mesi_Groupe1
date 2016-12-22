package cs.algorithm;


import java.util.ArrayList;
import java.util.Random;

//import Algorithme.CSSolution;
//import Algorithme.OptimizationProblem;
import DistLib.uniform;
import DistLib.weibull;
import tools.QLearning;


public class CSSolution extends Solution {

    public CSSolution(ArrayList<Double> vars) {
        super(vars);
    }
    public CSSolution(int numVars) {
        super(numVars);
        this.vars=new ArrayList<Double>(numVars);
        for(int i=0;i<numVars;i++){
    		vars.add(0.0);
    	}
    }
    public double getIndice(int i){
    	return this.vars.get(i);
    }

    
    public CSSolution randomWalk (OptimizationProblem prob, String distribution) {
    	int n = prob.getNumVar();
    	ArrayList<Integer> varIndices = new ArrayList<Integer>(n);
    	for (int i = 0; i < n; i++) {
    		varIndices.add(i, i);
    	}
    	
    	ArrayList<Double> vars = this.getVars();
    	CSSolution newSol = new CSSolution(this.numVars);
    	newSol.initializeWithNull();
    	ArrayList<Double> newVars = newSol.getVars();
    	for (int i = 0; i < n; i++) {
    		/* Chooses a random variable index from the indices
    		 * of the remaining/unwalked variables. */
    		int index = rand.nextInt(varIndices.size());
    		// Finds the variable value that this index corresponds to.
    		int varIndex = varIndices.get(index);
    		double curVar = vars.get(varIndex);
    		
    		// use correct distribution to generate random double [0,1)
    		double r;
            if (distribution == "levy") {
            	r = LevyDistribution.sample(1.5);
            } else {
                r = rand.nextDouble();
            }
         
    		double a0=0.01;
            double distance = curVar-vars.get(i);
            double a=a0*distance;
           
    		double newVar = a0+a*r;
    		newVars.set(varIndex, newVar);
    		
    	}
    	
    	return newSol;
    }
    
    public double heavisideFunction(double x){
    	if(x<0){
    		return 0;
    	}
    	else if(x>0){
    		return 1;
    	}
    	else{
    		return 0.5f;
    	}
    		
    }


    
    public CSSolution randomWalk5 (OptimizationProblem prob, double pa) {
        //this = solution i créee
    	int n = prob.getNumVar();
    	// creates a neighborhood of size 1 times the scaling factor
    	double distanceSquared = Math.pow(rand.nextDouble() * prob.getScalingFactor(),2);
    	// creates an ArrayList from 0 to n-1 (for indexing purposes only)
    	ArrayList<Integer> varIndices = new ArrayList<Integer>(n);
    	for (int i = 0; i < n; i++) {
    		varIndices.add(i, i);
    	}
    	
    	ArrayList<Double> vars = this.getVars();
    	CSSolution newSol = new CSSolution(this.numVars);
    	newSol.initializeWithNull();
    	ArrayList<Double> newVars = newSol.getVars();
    	for (int i = 0; i < n; i++) {
    		
    		int index1=rand.nextInt(varIndices.size());
    		double curVar1 = vars.get(varIndices.get(index1));
    		//retire pour pas retomber dessus
    		varIndices.remove(index1);
    		
    		int index2=rand.nextInt(varIndices.size());
    		double curVar2 = vars.get(varIndices.get(index2));
    		//rajoute pour le prochain tour
    		varIndices.add(index1);
    		
    		double r=rand.nextGaussian();
    		double e=rand.nextGaussian();
            
    		double newVar = vars.get(i) + r*heavisideFunction(pa-e)*(curVar1-curVar2);
    		newVars.set(i, newVar);
    		
    	}
    	
    	return newSol;
    }
	public int getOldIndice() {
		return super.getOldIndice();
		
	}
	public void setOldIndice(int val) {
		 super.setOldIndice(val);
		
	}

}
