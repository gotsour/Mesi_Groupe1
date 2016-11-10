package imss;

import java.util.ArrayList;

import cmaes.SolutionSet;
import cs.algorithm.CSSolution;
import cs.algorithm.CSSolutionSet;
import cs.algorithm.CuckooSearchOpt;
import cs.algorithm.OptimizationProblem;
import cs.algorithm.Solution;
import cs.problem.RosenbrockMinProb;


public class ConvertArrays {
	
	public static ArrayList<Double> convertToOneDimensionArrayList(double[] data){
		ArrayList<Double> list=new ArrayList<>();
		
		for(int i=0;i<data.length;i++){
			list.add(data[i]);
		}
		
		return list;
	}
	
	public static double[] convertToOneDimensionArray(ArrayList<Double> data){
		int l=data.size();
		double[] list=new double[l];
		
		for(int i=0; i<l;i++){
			list[i] = data.get(i);
		}
		
		return list;
	}

	public static double[][] getCSSOlutionsVars(OptimizationProblem problem, CuckooSearchOpt algo){
		
		CSSolutionSet set=algo.getSolutions(problem);
		int length=set.getNumSols();
		double[][] list=new double[length][];
		for(int i=0;i<length;i++){
			ArrayList<CSSolution> l= set.getSolutions();
			ArrayList<Double> line=l.get(i).getVars();
			list[i]= convertToOneDimensionArray(line);
			
		}
		return list;
	}
	
	
	
	
	
	
	
public static void main(String[] args) {
		
		/*CuckooSearchOpt opt=new CuckooSearchOpt();
		RosenbrockMinProb prob=new RosenbrockMinProb();
		opt.solve(prob);
		CSSolutionSet sols=opt.getSolutions(prob);
		for(int i=0;i< sols.getNumSols();i++){
			System.out.println(sols.getSol(i).toString());
		}
		
		System.out.println("\n\n\n\n");
		
		double[][] solutions=getCSSOlutionsVars(prob, opt );
		for(int i=0;i<solutions.length;i++){
			for(int j=0;j<solutions[i].length;j++){
				System.out.print(""+ solutions[i][j]+ "\t");
			}
			System.out.println();
		}
	*/
	//TODO
	CSSolutionSet set=new CSSolutionSet(3, 2);
	double[][] data={{0,5},{1,5},{2,5}};
	set.addDataToCSSolutionSet(data);
	System.out.println(set.getNumNests());
	for(int i=0;i<set.getNumNests();i++){
		System.out.println(set.getSol(i).toString());
	}
	}
	
}
