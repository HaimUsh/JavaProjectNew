package model;

import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import search.AStar;
import search.BFS;
import search.CommonSearcher;
import search.SearchableMaze;
import solution.Solution;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3DGenerator;

public class MyModel extends Observable implements Model {

	private HashMap<String, Maze3d> mazeList;

	private HashMap<Maze3d, Solution> solutionList;

	private HashMap<String, Object> commandData;

	ExecutorService executer;


	public MyModel() {
		this.commandData=new HashMap<String, Object>();
		this.mazeList= new HashMap<String, Maze3d>();
		this.solutionList= new HashMap<Maze3d, Solution>();

		executer = Executors.newCachedThreadPool();
//		try 
//		{
//			loadFromZip();
//			changeAndNotify("loadZip", "Mazes has been loaded from file");
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
	}


	@Override
	public void getFilesInDirectory(String path) {
		File f = new File(path);
		File[] fList = f.listFiles();
		String[] fileNames = new String[1];
		if (fList == null) 
		{
			fileNames[0] = "No Files in these directory";
			changeAndNotify("dir", fileNames);
			return;
		}
		else
		{
			fileNames = new String[fList.length];
			for (int i = 0; i < fList.length; i++)
			{
				fileNames[i] = fList[i].getName();	
			}
			changeAndNotify("dir", fileNames);
		}

	}

	@Override
	public void generate3DMaze(String name, int size) {
		Future<Maze3d> myMaze = executer.submit(new Callable<Maze3d>()
				{
			@Override
			public Maze3d call() throws Exception
			{
				Maze3dGenerator mg = new MyMaze3DGenerator();
				Maze3d myMaze = mg.generate(size, size, size);
				return myMaze;
			}
				});
		try 
		{
			getMazeList().put(name, myMaze.get());
		}
		catch (InterruptedException | ExecutionException e) 
		{

			e.printStackTrace();
		}
		changeAndNotify("generated", name);
	}

	@Override
	public void getCrossSection(char xyz, int index, String name) {
		if(getMazeList().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeList().get(name));
			if (xyz == 'x' || xyz == 'X') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByX(index));
				return;
			}
			if (xyz == 'y' || xyz == 'Y') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByY(index));
				return;
			}
			if (xyz == 'z' || xyz == 'Z') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByZ(index));
				return;
			}
			else 
			{
				changeAndNotify("notify", "bad X/Y/Z cord");
			}
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.getcross)");
			return;
		}

	}

	@Override
	public void saveMaze(String mazeName, String fileName) {
		Maze3d myMaze = new Maze3d(getMazeList().get(mazeName));
		try 
		{
			OutputStream out=new MyCompressorOutputStream( new FileOutputStream(fileName));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		changeAndNotify("saved", mazeName);
	}

	@Override
	public void loadMaze(String mazeName, String fileName) {
		InputStream in;
		try 
		{
			in = new MyDecompressorInputStream( new FileInputStream(fileName));
			byte[] b = new byte[((MyDecompressorInputStream) in).getLength()];
			in.read(b);
			in.close();
			this.mazeList.put(mazeName, new Maze3d(b));
			changeAndNotify("loaded", mazeName);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public void MazeSize(String name) {
		double size =-5;
		if(getMazeList().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeList().get(name));
			size = myMaze.toByteArray().length;
			commandData.put("maze", name);
			changeAndNotify("calcedMazeSize", size);
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.calcmazesize)");
		}

	}

	@Override
	public void FileSize(String name) {
		File f = new File(name);
		if (f.length() == 0L)
		{
			this.saveMaze(name, "tempFileName");
			f = new File("tempFileName");
		}
		commandData.put("maze", name);
		changeAndNotify("calcedFileSize", f.length());


	}

	@Override
	public void solveMaze(String name, String algo) {
		if (getMazeList().containsKey(name))
		{
			Future<Solution> mySolution = executer.submit(new Callable<Solution>()
					{
				@Override
				public Solution call() throws Exception 
				{
					Maze3d myMaze = new Maze3d(getMazeList().get(name));
					SearchableMaze sMaze = new SearchableMaze(myMaze);
					CommonSearcher searcher;
					Solution sol = new Solution();


					if (algo.equalsIgnoreCase("astarman"))
					{
						searcher = new AStar(new MazeManhattanDistance());
						sol = searcher.search(sMaze);
					}
					if (algo.equalsIgnoreCase("astarair"))
					{
						searcher = new AStar(new MazeEuclideanDistance());
						sol = searcher.search(sMaze);
					}
					if (algo.equalsIgnoreCase("bfs"))
					{
						searcher = new BFS();
						sol = searcher.search(sMaze);
					}
					return sol;
				}
					});
			try
			{
				getSolutionList().put(getMazeList().get(name), mySolution.get());
			} 
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
			changeAndNotify("solved", name);
		}
		else 
		{
			changeAndNotify("notify", "Bad Maze Name (m.solve)");
		}

	}

	@Override
	public HashMap<String, Maze3d> getMazeList() {
		return this.mazeList;
	}

	@Override
	public HashMap<Maze3d, Solution> getSolutionList() {

		return this.solutionList;
	}

	@Override
	public HashMap<String, Object> getCommandData() {

		return this.commandData;
	}

	private void changeAndNotify(String command, Object obj)
	{
		if (obj != null) 
		{
			this.commandData.put(command, obj);
		}
		setChanged();
		notifyObservers(command);
	}

	@Override
	public void officialExit() 
	{
		executer.shutdown();
		try 
		{
			saveToZip();
			changeAndNotify("saveZip", "File has been saved");
			executer.awaitTermination(59, TimeUnit.SECONDS);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		executer.shutdownNow();
		changeAndNotify("quit", "Official Exit");
	}

	private void saveToZip()
	{
		try
		{
			ObjectOutputStream zipMaze = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("mazeSolutionCache.gzip")));
			zipMaze.writeObject(mazeList);
			zipMaze.writeObject(solutionList);
			zipMaze.flush();
			zipMaze.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void loadFromZip()
	{
		File myFile = new File("mazeSolutionCache.gzip");
		try
		{
			if(!myFile.createNewFile())
			{
				ObjectInputStream mazeZip = new ObjectInputStream(new GZIPInputStream(new FileInputStream(myFile)));

				this.mazeList = (HashMap<String, Maze3d>) mazeZip.readObject();
				this.solutionList = (HashMap<Maze3d, Solution>) mazeZip.readObject();

				mazeZip.close();
			} 
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}



}
