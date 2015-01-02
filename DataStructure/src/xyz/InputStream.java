package xyz;

import java.io.BufferedReader;
import java.io.IOException;

public class InputStream {
	
	public static void main(String args[]) throws MyException
	{
		new InputStreamProcessingTemplate() {
			
			@Override
			public void doProcess(BufferedReader input) throws IOException {
				// TODO Auto-generated method stub
				
			}

		}.process("");
		
	}

}
