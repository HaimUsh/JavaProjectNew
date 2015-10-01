package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import model.Model;
import view.View;

public class Presenter implements Observer {

	Model model;
	View ui;
	HashMap<String, Command> commandList;


	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof Model)
		{
			String data = ((String) arg);
			switch (data)
			{
			case "dir":
			{
				ui.displayDirectory((String[]) model.getCommandData().get("dir"));
			} 	break;
			case "generated":
			{
				ui.display("Maze "+model.getCommandData().get("generated")+" is ready" );
			}	break;
			case "crossed":
			{
				ui.displayCrossSection((int[][]) model.getCommandData().get("crossed"));
			}	break;
			case "calcedMazeSize":
			{
				ui.displaySize((String) model.getCommandData().get("maze"), (double) model.getCommandData().get("calcedMazeSize"));
			}	break;
			case "loaded":
			{
			}	break;
			case "saved":
			{
			}	break;
			case "solved":
			{
				ui.display("Solution for maze: "+model.getCommandData().get("solved")+" is ready");
			}	break;
			case "notify":
			{
				ui.display((String) model.getCommandData().get("notify"));
			}	break;
			case "quit":
			{
				ui.display((String) model.getCommandData().get("quit"));
			}	break;
			case "saveZip":
			{
				ui.display((String) model.getCommandData().get("saveZip"));
			}	break;
			case "loadZip":
			{
				ui.display((String) model.getCommandData().get("loadZip"));
			}	break;
				

			default:
				ui.display("Model is going crazy (presenter.update.default)");
				break;
			}
		}
		else
		{
			if (o instanceof View)
			{
				String[] myData = ((String[]) arg);
				String data=myData[0];
				Command cData = commandList.get(myData[0]);
				cData.doCommand(data,ui,model);
			}
			else
			{
				System.out.println("FATAL ERROR:  UPDATE FROM SPACE (Presenter.update)");
				return;
			}
		}
	}

	public Presenter(Model m, View v) {
		this.ui=v;
		this.model=m;
		this.commandList= new HashMap<String, Command>();
		commandList.put("dir", new DirCommand());
		commandList.put("generate",new GenerateCommand());
		commandList.put("displayMaze",new presenter.DisplayMazeCommand());
		commandList.put("displayCrossSection",new presenter.DisplayCrossSectionCommand());
		commandList.put("save",new SaveCommand());
		commandList.put("load",new LoadCommand());
		commandList.put("mazeSize",new presenter.MazeSizeCommand());
		commandList.put("fileSize",new presenter.FileSizeCommand());
		commandList.put("solve",new SolveCommand());
		commandList.put("displaySolution",new presenter.DisplaySolutionCommand());
		commandList.put("menu",new presenter.CommandList());
	}
	
	
	
	public void display(String s){
		ui.display(s);
	}
	
	public HashMap<String, Command> getCommandList(){
		return this.commandList;
	}
	
	public void invokeCommand(String command) {
		String[] sp = command.split(" ");

		String commandName = sp[0];
		
		String args = null;
		if (sp.length > 1){
			args = new String();
			for (int i = 1; i < sp.length; i++)
				if(i==1)
					args = sp[i];
				else
					args += " " + sp[i];
		}
	
		Command cmd = selectCommand(commandName);
		if (cmd != null)
			cmd.doCommand(args,ui, model);
		else
			ui.display("Command not found");
			
	}
	

	public presenter.Command selectCommand(String commandName){
			
		return commandList.get(commandName);
	}
}