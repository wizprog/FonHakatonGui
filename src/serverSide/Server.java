package serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.plaf.SliderUI;


import sqlConnection.Database;

public class Server {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		Pointer pointer=new Pointer(false);
		HashMap<String, int[]> hashmap = new HashMap<String, int[]>();
		int array[]=new int[3];
		int oldArray[] = new int[3];
	//	for (int i=0; i < oldArray.length;i++) oldArray[i] = 0;
		new Timer(pointer,hashmap,array,oldArray).start();
		try (ServerSocket serverSocket = new ServerSocket(port);) {
			System.out.println("Server running on port " + port + "...");
			while (true) {
				while(pointer.isGoodToGo()==false){
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Server running on port " + port + "...");
				Socket socket = serverSocket.accept();
				System.out.println("IP Address: " + socket.getInetAddress());
				new WorkingThread(socket, hashmap,array,oldArray).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
