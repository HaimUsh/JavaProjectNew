package controller;

import model.Model;
import view.View;



// TODO: Auto-generated Javadoc
/**
 * The Class CommandList.
 */
public class CommandList implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String command,View v,Model m) {
		v.display("\nList of commands:");
		v.display("  dir <path>");
		v.display("  generate 3d maze <name> <size>");
		v.display("  display <name>");
		v.display("  display cross section by <x/y/z> <index> for <name>");
		v.display("  save maze <name> <file name>");
		v.display("  load maze <file name> <file>");
		v.display("  maze size <name>");
		v.display("  file size <name>");
		v.display("  solve <name> <algorithm (BFS / ASTARAIR / ASTARMAN>");
		v.display("  display solution <name>");
		v.display("  menu");
		v.display("  exit");

	}
}
