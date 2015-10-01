package presenter;

import model.Model;
import view.View;

public interface Command {

	public void doCommand(String args, View v, Model m);
}
