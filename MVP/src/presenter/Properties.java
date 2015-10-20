package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;

	private int numOfThreads;
	private String generateAlgo;
	private String solveAlgo;
	
	public Properties() {
		this.numOfThreads=10;
		this.generateAlgo="my";
		this.solveAlgo= "bfs";
		
	}
	
	public Properties(int num, String gAlgo, String sAlgo){
		this.numOfThreads=num;
		this.generateAlgo=gAlgo;
		this.solveAlgo=sAlgo;
	}
	
	public Properties(String num, String gen, String solve)
	{
		this.numOfThreads = Integer.parseInt(num);
		this.generateAlgo = new String(gen);
		this.solveAlgo = new String(solve);
	}
	

	public int getNumOfThreads() {
		return numOfThreads;
	}

	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

	public String getGenerateAlgo() {
		return generateAlgo;
	}

	public void setGenerateAlgo(String generateAlgo) {
		this.generateAlgo = generateAlgo;
	}

	public String getSolveAlgo() {
		return solveAlgo;
	}

	public void setSolveAlgo(String solveAlgo) {
		this.solveAlgo = solveAlgo;
	}
	
	
}
