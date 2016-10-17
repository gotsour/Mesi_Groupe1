package cmaes;

/**
 * Class representing a Sphere problem.
 */
public class Sphere extends Problem {
  /** 
   * Constructor
   * Creates a default instance of the Sphere problem
   * @param numberOfVariables Number of variables of the problem 
   * @param solutionType The solution type must "Real" or "BinaryReal". 
   */
  public Sphere(String solutionType, Integer numberOfVariables) {
    numberOfVariables_   = numberOfVariables ;
    numberOfObjectives_  = 1;
    numberOfConstraints_ = 0;
    problemName_         = "Sphere";
        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables_; var++){
      lowerLimit_[var] = -5.12;
      upperLimit_[var] = 5.12;
    } // for
        
    if (solutionType.compareTo("BinaryReal") == 0)
    	solutionType_ = new BinaryRealSolutionType(this) ;
    else if (solutionType.compareTo("Real") == 0)
    	solutionType_ = new RealSolutionType(this) ;
    else {
    	System.out.println("Error: solution type " + solutionType + " invalid") ;
    	System.exit(-1) ;
    }  

  } // Sphere
    
  /** 
  * Evaluates a solution 
  * @param solution The solution to evaluate
 * @throws Exception 
  */        
  public void evaluate(Solution solution) {
    Variable[] decisionVariables  = solution.getDecisionVariables();

    double sum = 0.0;
    for (int var = 0; var < numberOfVariables_; var++) {
      try {
		sum += StrictMath.pow(decisionVariables[var].getValue(), 2.0);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}      
    }        
    solution.setObjective(0, sum);
  } // evaluate
} // Sphere

