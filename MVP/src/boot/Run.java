package boot;

import presenter.Presenter;
import view.MyBasicWindow;
import view.MyView;
import model.MyModel;

public class Run {

	public static void main(String[] args) {
		
		MyModel model= new MyModel();
		MyView ui= new MyView();
		Presenter p= new Presenter(model, ui);
		model.addObserver(p);
		ui.addObserver(p);
		ui.start();
		MyBasicWindow bw = new MyBasicWindow();
		

	}

}
