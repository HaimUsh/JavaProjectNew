/*
 * 
 */
package demo;

import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import search.AStar;
import search.BFS;
import search.Searchable;
import search.SearchableMaze;
import search.Searcher;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3DGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class Demo.
 */
public class Demo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		run();

	}

	/**
	 * Run.
	 */
	public static void run()
	{
		Maze3dGenerator mg = new MyMaze3DGenerator();
		Maze3d myMaze = mg.generate(10, 10, 10);
		System.out.print("Start Position : ");
		System.out.println(myMaze.getStartPosition());
		System.out.print("Finish Position : ");
		System.out.println(myMaze.getGoalPosition());
		int bound = myMaze.getLevels();
		System.out.print("Printing the maze by ");
		System.out.print((bound+1));
		System.out.println(" Y levels: ");
		for (int i = 0; i <= bound; i++)
		{
			try
			{
				int[][] maze2dZ = myMaze.getCrossSectionByZ(i);
				myMaze.print2dMaze(maze2dZ);
			} 
			catch (Exception e) 
			{
				System.out.println("Fatal Error with maze printaion");
			}
		}
		Searchable sm = new SearchableMaze(myMaze);
		Searcher sBFS = new BFS();
		sBFS.search(sm);
		Searcher sManhatan = new AStar(new MazeManhattanDistance());
		sManhatan.search(sm);
		Searcher sEuc = new AStar(new MazeEuclideanDistance());
		sEuc.search(sm);

		System.out.print("BFS algorithm has developed: ");
		System.out.print(sBFS.getNumberOfNodesEvaluated());
		System.out.println(" states.");
		System.out.print("AStar Manhatten algorithm has developed: ");
		System.out.print(sManhatan.getNumberOfNodesEvaluated());
		System.out.println(" states.");
		System.out.print("ASter Euclidean algorithm has developed: ");
		System.out.print(sEuc.getNumberOfNodesEvaluated());
		System.out.println(" states.");
		System.out.println("check1");
	}
}
