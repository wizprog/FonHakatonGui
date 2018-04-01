package serverSide;

import java.util.HashMap;

import sqlConnection.Database;

public class Timer extends Thread{
	
	Pointer pointer;
	Database database;
	HashMap<String, int[]> hashmap;
	int array[]=null;
	int oldArray[] = null;
	
	
	public Timer(Pointer pointer,HashMap<String, int[]> hashmap,int array[], int oldArray[]) {
		this.pointer=pointer;
		this.hashmap=hashmap;
		this.array=array;
		this.oldArray = oldArray;
	}

	public void run() {
		while(true){
			pointer.setGoodToGo(false);
			database=new Database();
			updateDB(this.hashmap);
			database.closeConnection();
			pointer.setGoodToGo(true);
			try {
				sleep(120000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateDB(HashMap<String, int[]> hashmap) {
		database.getLastHour(hashmap,array, oldArray);
		
	}
}
