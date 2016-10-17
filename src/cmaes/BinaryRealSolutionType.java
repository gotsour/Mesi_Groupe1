package cmaes;

public class BinaryRealSolutionType extends SolutionType {

	/**
	 * Constructor
	 * @param problem
	 * @throws ClassNotFoundException 
	 */
	public BinaryRealSolutionType(Problem problem) {
		super(problem) ;
	} // Constructor
	
	/**
	 * Creates the variables of the solution
	 * @param decisionVariables
	 */
	public Variable[] createVariables() {
		Variable [] variables = new Variable[problem_.getNumberOfVariables()];
	  
    for (int var = 0; var < problem_.getNumberOfVariables(); var++) {
      if (problem_.getPrecision() == null) {
        int [] precision = new int[problem_.getNumberOfVariables()] ;
        for (int i = 0; i < problem_.getNumberOfVariables(); i++)
          precision[i] = BinaryReal.DEFAULT_PRECISION ;
        problem_.setPrecision(precision) ;
      } // if
      variables[var] = new BinaryReal(problem_.getPrecision(var),
                                      problem_.getLowerLimit(var),
                                      problem_.getUpperLimit(var));   
    } // for 
    return variables ;    
	} // createVariables
} // BinaryRealSolutionType
