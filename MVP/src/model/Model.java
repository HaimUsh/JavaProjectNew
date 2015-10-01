package model;

import java.util.HashMap;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;

public interface Model {


	public void getFilesInDirectory(String path);

	public void generate3dMaze(String name, int size);

	public void getCrossSection (char xyz, int index, String name);

	public void saveMaze (String mazeName, String fileName);

	public void loadMaze (String mazeName, String fileName);

	public void calcMazeSize(String name);

	public void calcFileSize(String name);

	public void solve(String name, String algo);

	public HashMap<String,Maze3d> getMazeList();

	public HashMap<Maze3d,Solution> getSolutionList();

	public HashMap<String, Object> getCommandData();

	public void officialExit();

	

	
	

}

