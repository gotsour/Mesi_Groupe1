package cmaes;

/**
 * Class representing a solution type composed of real variables
 */
public class RealSolutionType extends SolutionType {

	/**
	 * Constructor
	 * @param problem
	 * @throws ClassNotFoundException 
	 */
	public RealSolutionType(Problem problem) {
		super(problem) ;
	} // Constructor

	/**
	 * Creates the variables of the solution
	 * @param decisionVariables
	 */
	public Variable[] createVariables() {
		Variable[] variables = new Variable[problem_.getNumberOfVariables()];

		for (int var = 0; var < problem_.getNumberOfVariables(); var++)
			variables[var] = new Real(problem_.getLowerLimit(var),
					problem_.getUpperLimit(var)); 

		return variables ;
	} // createVariables
} // RealSolutionType

