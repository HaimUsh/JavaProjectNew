package presenter;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer {

	Model model;
	View ui;
	
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	public Presenter(Model m, View v) {
		this.ui=v;
		this.model=m;
	}
	
}
