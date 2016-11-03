package tools;

public class QLearning {

	public static final int phi = 10;
	public static final int ro = 10;
	
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
