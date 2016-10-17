package cs.problem;

import java.util.ArrayList;

import cs.algorithm.Constraint;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;


/**
 * Optimizes for the *minimum* of the bivariate Michaelwicz function,
 * letting m = 10 and constraining x and y between 0 and 5, inclusive.
 */
public class MichaelwiczMinProb extends OptimizationProblem {
	
    private final double M = 10.;
    
    public MichaelwiczMinProb() {
        this.constraints.add(new Constraint(0, 0,5));
        this.constraints.add(new Constraint(1, 0,5));
    }
    
    @Override
    public int getNumVar() {
        return 2;
    }

    /**
     * Returns the evaluation of the Michaelwicz function for a given solution.
     */
    public double eval(Solution s) {
        ArrayList<Double> vars = s.getVars();
        double x = vars.get(0);
        double y = vars.get(1);
        
        return
            -Math.sin(x) * Math.pow(Math.sin(  Math.pow(x, 2) / Math.PI), 2*M) -
             Math.sin(y) * Math.pow(Math.sin(2*Math.pow(y, 2) / Math.PI), 2*M);
    }
    
     /**
     * Returns the negative of the Michaelwicz function in order to
     * minimize the function.
     */
    @Override
    public double fitness(Solution s) {
        // Negates the evaluation in order to optimize for the *minimum*
        return -this.eval(s);
    }

    /** Returns true if x is between low and high, inclusively. */
    private boolean isInRange(double x, double low, double high) {
        return x >= low && x <= high;
    }
    
    /** Returns true if x and y are between 0 and 5. */
    @Override
    public boolean withinCustomConstraints(Solution s) {
        ArrayList<Double> vars = s.getVars();
        return this.isInRange(vars.get(0), 0., 5.) &&
               this.isInRange(vars.get(1), 0., 5.);
    }
    
    @Override
    public String solToString(Solution s) {
        ArrayList<Double> vars = s.getVars();
	    return String.format("X = %f, and Y = %f\n" +
	    		"F(x,y) = %f", vars.get(0), vars.get(1), s.getFitness());
    }
    
    @Override
    public String solToTable(Solution s) {
    	ArrayList<Double> vars = s.getVars();
    	
		String html = "<table>";
		html += String.format("<tr><td>x</td><td>%.4f</td></tr>", vars.get(0));
		html += String.format("<tr><td>y</td><td>%.4f</td></tr>", vars.get(1));
		html += String.format("<tr><td>Minimum f(x):</td><td>%.2f</td></tr></table>",-s.getFitness());
		
		return html;
    }

}
