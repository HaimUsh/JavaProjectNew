package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

public class ServerDisplay extends BasicWindow {
	Image image;
	
		private String[] ITEMS = { "A", "B", "C", "D" };

	public ServerDisplay(String title, int width, int height) {
		super(title, width, height);
		try {
			image  =  new Image(null,new FileInputStream("resources/server.jpg"));
	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(3,false));
		shell.setBackgroundImage(image);
		
		 Label label = new Label(shell,SWT.CENTER);
		  label.setText("connect to:");
		  label.setLayoutData(new GridData(SWT.FILL, SWT.FILL,false,false,1,1));

		  Combo combo1 = new Combo(shell, SWT.DROP_DOWN);
		  combo1.setItems(ITEMS);
		  combo1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		  
		  Button button = new Button(shell,SWT.BUTTON2);
		  button.setText("connect");
		  button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,1));
		 
	}

	public static void main(String[] args) {
		ServerDisplay sd=new ServerDisplay("ServerSide", 1024, 720);
		sd.run();
	}
}
