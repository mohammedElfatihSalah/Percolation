import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{

	private int n;
	private boolean [][] sites;
	private int numberOfOpenSites;
	private WeightedQuickUnionUF uf;

	private int[] sizes;

	public Percolation(int n){
		
		if (n <= 0){
			throw new IllegalArgumentException("n must be greater than zero");
		}
		this.n = n;

		//t1
		uf = new WeightedQuickUnionUF(n*n+ 2);

		//added additional two rows 
		//one at the top,
		// and the other at the bottom, it contains sites that are open.
		sites  = new boolean[n+1][n+1];

	}


	//indexes assume 1 to n
	// make site at (row,col) open
	public void open(int row, int col){

		if ((row <= 0 || row > n)||(col <= 0 || col > n)){
			throw new IllegalArgumentException("must not get out of boundary");
		}

		if(!sites[row][col]){
			sites[row][col] = true;
			numberOfOpenSites++;
			updateId(row, col);
			if(row == 1){
				int id = (row-1) * n + col-1;
				uf.union(id,n*n);
			}else if(row == n){
				int id = (row-1) * n + col-1;
				uf.union(id,n*n+1);
			}

		}
	}

	private void updateId(int row, int col){
		boolean left = true;
		boolean right = true;
		boolean up = true;
		boolean down = true; 
		if(row == 1){
			up = false;
		}
		if(col == 1){
			left = false;
		}
		if(row == n){
			down = false;
		}
		if(col == n){
			right = false;
		}

		if(up){
			if(isOpen(row-1,col)){
				union(row,col,row-1,col);

			}
		}

		if(down){
			if(isOpen(row+1,col)){
				union(row,col,row+1,col);
			}
		}

		if(right){
			if(isOpen(row,col+1)){
				union(row,col,row,col+1);
			}
		}

		if(left){
			if(isOpen(row,col-1)){
				union(row,col,row,col-1);
			}
		}
			
	}

	// check id site at (row,col) open
	public boolean isOpen(int row, int col){
		if ((row <= 0 || row > n)||(col <= 0 || col > n)){
			throw new IllegalArgumentException("error");
		}

		return sites[row][col];

	}

	public boolean isFull(int row, int col){

		if ((row <= 0 || row > n)||(col <= 0 || col > n)){
			throw new IllegalArgumentException("error");
		}
		int id1 = n*(row-1) + col-1;
		
		return uf.connected(id1 , n*n) ;
	}

	public boolean percolates(){
		return uf.connected(n*n, n*n+1);
	}



	private boolean connected(int i1, int j1, int i2, int j2){
		int id1 = n*(i1-1) + j1-1;
		int id2 = n*(i2-1) + j2-1;
		return uf.connected(id1 , id2);
	}

	private void union(int i1,int j1, int i2, int j2){
		int id1 = n*(i1-1) + j1-1;
		int id2 = n*(i2-1) + j2-1;
		uf.union(id1,id2);
	}

	/*public void print(){
		for(int i = 0 ; i < n+2 ; i++ ){
			for(int j = 0 ; j < n; j++ ){
			System.out.print(sites[i][j]+" ");
		}
		System.out.println();
	}

	}*/

	/*public void print1(){
		for(int i = 0; i < (n+2)*n ; i++){
			System.out.print(id[i] + " ");
		}
	}*/

	public int numberOfOpenSites(){
		return numberOfOpenSites ;
	}


	public static void main(String [] args){
		System.out.println("hello");
		int n = 200;
		Percolation test = new Percolation(n);
		int i = 0;
		while(!test.percolates()){
			
			//System.out.println();
			//i++;
			int row = StdRandom.uniform(1,n+1);
			int col = StdRandom.uniform(1,n+1);
			System.out.println(row + " " + col);
			test.open(row,col);
		}
		double p  = (double) test.numberOfOpenSites()/(n*n);
		System.out.println(p);
	}

}
