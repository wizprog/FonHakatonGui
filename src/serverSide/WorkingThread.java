package serverSide;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;


public class WorkingThread extends Thread {

	Socket socket;
	HashMap<String, int[]> hashmap;
	int array[];
	int oldArray[];

	public WorkingThread(Socket socket, HashMap<String, int[]> hashmap,int array[], int oldArray[]) {
		this.socket = socket;
		this.hashmap = hashmap;
		this.array=array;
		this.oldArray = oldArray;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket;
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
			String bs = (String) in.readObject();
			System.out.println(bs + " OVO JE BS");
			int a[] = hashmap.get(bs);
			
			
			
			
			out.writeObject(a[0]);
			out.writeObject(a[1]);
			out.writeObject(a[2]);
			out.writeObject(array[0]-oldArray[0]);
			out.writeObject(array[1]-oldArray[1]);
			out.writeObject(array[2]-oldArray[2]);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
