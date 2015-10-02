package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import presenter.Presenter;
import controller.Command;


public class CLI {
	
	BufferedReader in;
	
	PrintWriter out;
	
	HashMap<String,Command> commandList;

	public CLI (BufferedReader i, PrintWriter o, HashMap<String,Command> c)
	{
		this.in = i;
		this.out = o;
		this.commandList = c;
	}

	public void start(Presenter p)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{

				System.out.print("Enter command (for cmd list, type \"menu\"): ");
				try {
					String line = in.readLine();

					while (!line.equals("exit")) {

						p.invokeCommand(line);
						System.out.print("Enter command: ");
						line = in.readLine();
					}

					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						System.out.println("bye bye");
						in.close();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
			}).start();
	}
	
}
