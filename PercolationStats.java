import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats{
	private double [] observations;
	private double averageP;
	private double stdP;
	private int n;
	private int trials;

	public PercolationStats(int n , int trials){
		observations = new double[trials];
		this.n = n;
		this.trials = trials;

		

		for(int i = 0 ; i < trials ; i++){
			Percolation test  = new Percolation(n);
			while(!test.percolates()){
			
			//System.out.println();
			//i++;
			int row = StdRandom.uniform(1,n+1);
			int col = StdRandom.uniform(1,n+1);
		
			test.open(row,col);
			}
			double p  = (double) test.numberOfOpenSites()/(n*n);
			observations[i] = p;
		}
		averageP = StdStats.mean(observations);
		stdP = StdStats.stddev(observations);
	}

	public double mean(){
		return averageP;
	}

	public double stddev(){
		return stdP;
	}

	public double confidenceLo(){
		return averageP - 1.96/Math.sqrt(trials);
	}

	public double confidenceHi(){
		return averageP + 1.96/Math.sqrt(trials);
	}

	public static void main(String [] args){
		int n  =Integer.parseInt(args[0]);
		int t  = Integer.parseInt(args[1]);

		
		PercolationStats test = new PercolationStats(n,t);
		System.out.println("mean\t"+test.mean());
		System.out.println("stdev\t"+test.stddev());
		System.out.println("95% confidence interval\t" +"["+test.confidenceLo()  + " " +test.confidenceHi() + " ]");
	}

}