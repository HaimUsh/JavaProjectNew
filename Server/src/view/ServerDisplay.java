package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;

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
		shell.setLayout(new GridLayout(2,false));
		shell.setBackgroundImage(image);
		

		  Combo combo = new Combo(shell, SWT.DROP_DOWN);
		  combo.setItems(ITEMS);
		  combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
		  combo.setText("connect to:");
		
	}

	public static void main(String[] args) {
		ServerDisplay sd=new ServerDisplay("ServerSide", 1024, 720);
		sd.run();
	}
}
