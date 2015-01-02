package xyz;

import java.io.IOException;

public class MyException extends IOException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* class definition of constructors goes here */

	public MyException (String errorMessage) {
		super (errorMessage);
		//System.out.println(errorMessage);
	}
	
	public MyException(IOException e2, String msg){
		super();
		System.out.println(e2.getMessage() + " "+msg);
	}
	
	public MyException(IOException e1, IOException e2, String msg){
		super();
		System.out.println(e1.getMessage() + " "+ e2.getMessage() + " "+msg);
	}
}