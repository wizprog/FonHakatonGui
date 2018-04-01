package serverSide;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;


public class WorkingThread extends Thread {

	Socket socket;
	HashMap<String, int[]> hashmap;

	public WorkingThread(Socket socket, HashMap<String, int[]> hashmap) {
		this.socket = socket;
		this.hashmap = hashmap;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket;
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
			String bs = (String) in.readObject();
			System.out.println(bs + " OVO JE BS");
			int array[] = hashmap.get(bs);
			out.writeObject(array[0]);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
