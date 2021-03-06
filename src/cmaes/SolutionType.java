package cmaes;

public abstract class SolutionType {	
	
	public Problem problem_ ; /** Problem to be solved */
	
  /**
   * Constructor
   * @param problem The problem to solve
   */
  public SolutionType(Problem problem) {
  	problem_ = problem ;
  } // Constructor
    
  /**
   * Abstract method to create the variables of the solution
   * @param decisionVariables
   */
	public abstract Variable[] createVariables() ;
	
	/**
	 * Copies the decision variables
	 * @param decisionVariables
	 * @return An array of variables
	 */
	public Variable[] copyVariables(Variable[] vars) {
		Variable[] variables ;
		
		variables = new Variable[vars.length];
		for (int var = 0; var < vars.length; var++) {
			variables[var] = vars[var].deepCopy();
		} // for
		
		return variables ;
	} // copyVariables
	  
} // SolutionType
