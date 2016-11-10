package tools;

public class QLearning {

	protected float phi;
	protected float ro;
	
	public QLearning(float phi, float ro) {
		this.phi = phi;
		this.ro = ro;
	}
	
	public double[][] UpdateQTable(double[][] Q, int solver, double ft, double ftplus, int i) {
		double rtplus = Math.abs(ftplus - ft);
		//ftplus = rtplus;
				
		if ( ftplus > ft ) {
			Q[i][solver] = Q[i][solver] + ro * (rtplus + (phi*rtplus) - Q[i][solver]);
		} else {
			Q[i][solver] = Q[i][solver] + ro * (-rtplus - (phi*rtplus) - Q[i][solver]);
		}
		return Q;
	}
	
}
