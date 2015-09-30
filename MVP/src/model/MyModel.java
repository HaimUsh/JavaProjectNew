package model;

import java.util.HashMap;
import java.util.Observable;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;

public class MyModel extends Observable implements Model {

	@Override
	public void getFilesInDirectory(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generate3dMaze(String name, int size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCrossSection(char xyz, int index, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveMaze(String mazeName, String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMaze(String mazeName, String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calcMazeSize(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calcFileSize(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void solve(String name, String algo) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Maze3d> getMazeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Maze3d, Solution> getSolutionList() {
		//test
		return null;
	}

}
