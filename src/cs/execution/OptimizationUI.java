package cs.execution;

import cs.algorithm.Solution;
import cs.exception.InputException;

public abstract class OptimizationUI {
    
    public OptimizationUI() {
    }
    
    public abstract String getVariableInput(String varName);
    public abstract String getFile(String request);
    public abstract int getOptionChoice(String prompt, String[] options);
    
    public abstract void printSolution(Solution solution);
    public abstract void display(String output);
    
    public Double getDoubleInput(String varName) throws InputException {
        String s = getVariableInput(varName);
        try {
            return Double.parseDouble(s);
        }
        catch(NumberFormatException e) {
            throw new InputException(varName, "is not a double");
        }
    }
    
    public Integer getIntegerInput(String varName) throws InputException {
        String s = getVariableInput(varName);
        try {
            return Integer.parseInt(s);
        }
        catch(NumberFormatException e) {
            throw new InputException(varName, "is not an integer");
        }
    }

}
