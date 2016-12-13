package imss;

import java.util.ArrayList;

import cs.algorithm.Solution;

public class main {
	public static void main(String[] args) {
		IMSS test= new IMSS();
		test.initPopulation(-200, 200);
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
