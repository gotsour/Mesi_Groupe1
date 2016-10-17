package cs.algorithm;

public class Constraint {
    int varIndex;
    double min, max;
    
    public Constraint(int varIndex, double min, double max) {
        this.varIndex = varIndex;
        this.min = min;
        this.max = max;
    }
    
    public int getVarIndex() {
        return this.varIndex;
    }
    public double getMin() {
        return this.min;
    }
    public double getMax() {
        return this.max;
    }
}
