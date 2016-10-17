package cmaes;

import java.io.Serializable;

/**
 * This abstract class is the base for defining new types of variables.
 * Many methods of <code>Variable</code> (<code>getValue</code>,
 * <code>setValue</code>,<code>
 * getLowerLimit</code>,<code>setLowerLimit</code>,<code>getUpperLimit</code>,
 * <code>setUpperLimit</code>)
 * are not applicable to all the subclasses of <code>Variable</code>.
 * For this reason, they are defined by default as giving a fatal error.
 */
public abstract class Variable implements Serializable {
	
	private static final long serialVersionUID = 1L;

  //private VariableType_ type_;

  /** 
   * Creates an exact copy of a <code>Variable</code> object.
   * @return the copy of the object.
   */
  public abstract Variable deepCopy();

  /**
   * Gets the double value representating the variable. 
   * It is used in subclasses of <code>Variable</code> (i.e. <code>Real</code> 
   * and <code>Int</code>).
   * As not all objects belonging to a subclass of <code>Variable</code> have a 
   * double value, a call to this method it is considered a fatal error by 
   * default, and the program is terminated. Those classes requiring this method 
   * must redefine it.
 * @throws Exception 
   */
  public double getValue() throws Exception {
    Class<String> cls = java.lang.String.class;
    String name = cls.getName(); 
    Configuration.logger_.severe("Class " + name + " does not implement " +
        "method getValue");
    throw new Exception("Exception in " + name + ".getValue()") ;
  } // getValue
  
  /**
  * Sets a double value to a variable in subclasses of <code>Variable</code>. 
  * As not all objects belonging to a subclass of <code>Variable</code> have a 
  * double value, a call to this method it is considered a fatal error by 
  * default, and the program is terminated. Those classes requiring this method 
  * must redefine it.
 * @throws Exception 
  */
  public void setValue(double value) throws Exception {
    Class<String> cls = java.lang.String.class;
    String name = cls.getName(); 
    Configuration.logger_.severe("Class " + name + " does not implement " +
        "method setValue");
    throw new Exception("Exception in " + name + ".setValue()") ;
  } // setValue

  /**
   * Gets the lower bound value of a variable. As not all
   * objects belonging to a subclass of <code>Variable</code> have a lower bound,
   * a call to this method is considered a fatal error by default,
   * and the program is terminated.
   * Those classes requiring this method must redefine it.
 * @throws Exception 
   */
  public double getLowerBound() throws Exception { 
    Class<String> cls = java.lang.String.class;
    String name = cls.getName(); 
    Configuration.logger_.severe("Class " + name + 
                       " does not implement method getLowerBound()");
    throw new Exception("Exception in " + name + ".getLowerBound()") ;
  } // getLowerBound
  
  /**
   * Gets the upper bound value of a variable. As not all
   * objects belonging to a subclass of <code>Variable</code> have an upper 
   * bound, a call to this method is considered a fatal error by default, and the 
   * program is terminated. Those classes requiring this method must redefine it.
 * @throws Exception 
   */
  public double getUpperBound() throws Exception {
    Class<String> cls = java.lang.String.class;
    String name = cls.getName(); 
    Configuration.logger_.severe("Class " + name + 
                       " does not implement method getUpperBound()");
    throw new Exception("Exception in " + name + ".getUpperBound()") ;
  } // getUpperBound
  
  /**
   * Sets the lower bound for a variable. As not all objects belonging to a
   * subclass of <code>Variable</code> have a lower bound, a call to this method 
   * is considered a fatal error by default and the program is terminated.
   * Those classes requiring this method must to redefine it.
 * @throws Exception 
   */
  public void setLowerBound(double lowerBound) throws Exception {
    Class<String> cls = java.lang.String.class;
    String name = cls.getName(); 
    Configuration.logger_.severe("Class " + name + 
                       " does not implement method setLowerBound()");
    throw new Exception("Exception in " + name + ".setLowerBound()") ;
  } // setLowerBound
  
  /**
   * Sets the upper bound for a variable. As not all objects belonging to a 
   * subclass of <code>Variable</code> have an upper bound, a call to this method
   * is considered a fatal error by default, and the program is terminated. 
   * Those classes requiring this method must redefine it.
 * @throws Exception 
   */
  public void setUpperBound(double upperBound) throws Exception {
    Class<String> cls = java.lang.String.class;
    String name = cls.getName(); 
    Configuration.logger_.severe("Class " + name + 
                       " does not implement method setUpperBound()");
    throw new Exception("Exception in " + name + ".setUpperBound()") ;
  } // setUpperBound

  /**
   * Sets the type of the variable. The types are defined in class Problem.
   */
  /*
  public void setVariableType(VariableType_ variableType) {
    type_ = variableType ;
  } // setVariableType
*/
  /**
   * Gets the type of the variable. The types are defined in class Problem.
   * @return The type of the variable
   */
  
  public Class<? extends Variable> getVariableType() {
    return this.getClass() ;
  } // getVariableType
} // Variable
