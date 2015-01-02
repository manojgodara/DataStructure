package xyz;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class InputStreamProcessingTemplate
{

	public static int getStream()
	{
		return 0;
	}
	
	public void process(String fileName) throws MyException 
	{
		InputStream input = null;
		DataInputStream dis = null;
		BufferedReader br = null;
		IOException processException = null;
		
		try{
			input = new FileInputStream(fileName);
			dis = new DataInputStream(input);
			br = new BufferedReader(new InputStreamReader(dis));
			doProcess(br);
			
		}catch(IOException e) {
			processException = e;
		}finally {
			if(input != null)
			{
				try {
					br.close();
				} catch (IOException e) {
					if(processException != null)
					{
						throw new MyException(processException, e,"Error message..." +fileName);
					}else{
						throw new MyException(e, "Error closing InputStream for file " +fileName);
					}
				}
			}
			if(processException != null)
			{
				throw new MyException(processException, "Error closing InputStream for file " +fileName);
			}
		}
	}
	
	public abstract void doProcess(BufferedReader input) throws IOException;
}
