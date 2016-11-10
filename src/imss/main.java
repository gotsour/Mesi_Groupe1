package imss;

public class main {
	public static void main(String[] args) {
		IMSS test= new IMSS();
		test.solve();
		
		//affichange des solutions
		for(int i=0;i<test.getPopulation().length;i++){
			for(int j=0;j<test.getPopulation()[i].length;j++){
				System.out.print(" "+test.getPopulation()[i][j]);
			}
			System.out.println(" ");
		}
	
	}

}
