package boot;

import model.MyModel;
import presenter.Presenter;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		
		MyModel model= new MyModel();
		MyView ui= new MyView();
		Presenter p= new Presenter(model, ui);
		model.addObserver(p);
		ui.addObserver(p);
		ui.start();
		

	}

}
