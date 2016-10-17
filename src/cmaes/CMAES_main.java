package cmaes;

/**
 * This class runs a single-objective CMA-ES algorithm.
 */
public class CMAES_main {

  public static void main(String [] args) throws Exception {

    int numberOfVariables = 20;
    int populationSize = 50;
    int maxEvaluations = 1000000;

    Problem problem   ;         // The problem to solve
    Algorithm algorithm ;         // The algorithm to use

    problem = new Sphere("Real", numberOfVariables) ;
    //problem = new Easom("Real") ;
    //problem = new Griewank("Real", populationSize) ;

    algorithm = new CMAES(problem) ;
    
    /* Algorithm parameters*/
    algorithm.setInputParameter("populationSize", populationSize);
    algorithm.setInputParameter("maxEvaluations", maxEvaluations);
 
    /* Execute the Algorithm */
    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    long estimatedTime = System.currentTimeMillis() - initTime;
    System.out.println("Total execution time: " + estimatedTime);

    /* Log messages */
    System.out.println("Objectives values have been written to file FUN");
    population.printObjectivesToFile("FUN");
    System.out.println("Variables values have been written to file VAR");
    population.printVariablesToFile("VAR");

  } //main

} // CMAES_main

