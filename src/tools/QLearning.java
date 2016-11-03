package tools;

public class QLearning {

	protected float phi;
	protected float ro;
	
	public QLearning(float phi, float ro) {
		this.phi = phi;
		this.ro = ro;
	}
	
	public double[][] UpdateQTable(double[][] Q, int solver, float ft, float ftplus, int i) {
		float rtplus = Math.abs(ftplus - ft);
		ftplus = rtplus;
				
		if ( ftplus > ft ) {
			Q[i][solver] = Q[i][solver] + ro * (rtplus + (phi*ftplus) - Q[i][solver]);
		} else {
			Q[i][solver] = Q[i][solver] + ro * (-rtplus - (phi*ftplus) - Q[i][solver]);
		}
		return Q;
	}
	
}
