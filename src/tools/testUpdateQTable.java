package tools;

import java.util.Random;

public class testUpdateQTable {
	
public static void main(String[] args) {
	
		QLearning ql = new QLearning(10, 10);
		
		int size = 5;
		double[][] Q = new double[size][2];
		
		// 0 -> CS | 1 -> CMAES
		int CS = 0;
		int CMAES = 1;
		
		Random rand = new Random();
		int max = 100;
		int min = 0;
		
		for (int i = 0 ; i < size ; i++) {
			int randomCS = rand.nextInt((max - min) + 1) + min;
			int randomCMAES = rand.nextInt((max - min) + 1) + min;
			Q[i][CS] = randomCS;
			Q[i][CMAES] = randomCMAES;
		}
		
		/*for (int i = 0 ; i < size ; i++) {
			System.out.println("Q["+i+"][CS] = "+Q[i][CS]);
			System.out.println("Q["+i+"][CMAES] = "+Q[i][CMAES]);
		}*/
				
		double[][] newCSQ = new double[size][2];
		double[][] newCMAESQ = new double[size][2];
		newCSQ = ql.UpdateQTable(Q, CS, 15, 10, 2);
		newCMAESQ = ql.UpdateQTable(Q, CMAES, 15, 10, 2);
		
		/*for (int i = 0 ; i < size ; i++) {
			System.out.println("newQ["+i+"][CS] = "+newQ[i][CS]);
			System.out.println("newQ["+i+"][CMAES] = "+newQ[i][CMAES]);
		}*/
		
		// Exemple à l'indice 2 des tableaux
		System.out.println("Q["+2+"][CS] = "+Q[2][CS]);
		System.out.println("Q["+2+"][CMAES] = "+Q[2][CMAES]);
		System.out.println("newCSQ["+2+"][CS] = "+newCSQ[2][CS]);
		System.out.println("newCMAESQ["+2+"][CMAES] = "+newCMAESQ[2][CMAES]);
	}
	
}
