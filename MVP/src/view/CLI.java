//package view;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Observable;
//import java.util.Observer;

//
//public class CLI extends Observable {
//	
//	BufferedReader in;
//	
//	PrintWriter out;
//	
//	ArrayList<Observer> Observers;
//	
//	Observable me;
//
//	public CLI (BufferedReader i, PrintWriter o)
//	{
//		this.in = i;
//		this.out = o;
//		Observers = new ArrayList<Observer>();
//		me = this;
//	}
//
//	public void start()
//	{
//		new Thread(new Runnable()
//		{
//			@Override
//			public void run() 
//			{
//
//				System.out.print("Enter command (for cmd list, type \"menu\"): ");
//				try {
//					String line = in.readLine();
//
//					while (!line.equals("exit")) {
//						
//						notifyObservers(me, line);
//						System.out.print("Enter command: ");
//						line = in.readLine();
//					}
//
//					
//				} catch (IOException e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						System.out.println("bye bye");
//						in.close();
//						out.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//		}
//			}).start();
//	}
//	
//	public void addObserver(Observer o){
//		if(!Observers.contains(o))
//			Observers.add(o);
//	}
//	
//	public void notifyObservers(Observable cameFrom, Object arg){
//		for (Observer observer : Observers) {
//			observer.update(cameFrom, arg);
//		}
//	}
//}
