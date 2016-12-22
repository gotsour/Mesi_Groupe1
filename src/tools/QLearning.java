package tools;

public class QLearning {

	protected float gamma;
	protected float ro;
	
	public QLearning(float gamma, float ro) {
		this.gamma = gamma;
		this.ro = ro;
	}
	
	public double[][] UpdateQTable(double[][] Q, int solver, double ft, double ftplus, int i) {
		double rtplus = Math.abs(ftplus - ft);
		//ftplus = rtplus;
				
		if ( ftplus < ft ) {
			Q[i][solver] = Q[i][solver] + ro * (rtplus + (gamma*rtplus) - Q[i][solver]);
			
		} else {
			Q[i][solver] = Q[i][solver] + ro * (-rtplus - (gamma*rtplus) - Q[i][solver]);
			
		}
		return Q;
	}
	
}
