package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable {
	
	Display display;
	Shell shell;
	
 	public BasicWindow(String title, int width,int height) {
 		this.display=new Display();
 		this.shell  = new Shell(display);
 		this.shell.setSize(width,height);
 		this.shell.setText(title);
	}
 	
 	abstract void initWidgets();

	@Override
	public void run() {
		initWidgets();
		shell.open();
		
		 while(!shell.isDisposed()){ 

		    if(!display.readAndDispatch()){ 
		       display.sleep(); 			
		    }

		 } 

		 display.dispose(); 
	}
	
}
