package application;

public class CodeGenerator {
	private static int lastCode;
	private int myCode=lastCode++;
	
	public int getMyCode() {
		return myCode;
	}
	public void setMyCode(int myCode) {
		this.myCode = myCode;
	}
}
