package xyz;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class Directory{
	
	class File{
		String fileName;
		public File(String fileName){
			this.fileName = fileName;
		}
	}
	
	int dirID;
	String dirName;
	ArrayList<Directory> dl;
	ArrayList<File> fl;
	
	public Directory(int dirID, String dirName){
		this.dirID = dirID;
		this.dirName = dirName;
		this.dl = new ArrayList<Directory>();
		this.fl = new ArrayList<File>();
	}
	
	void addDirectory(Directory d) {
		
		if(d == null)
			try {
				throw new FileNotFoundException("Null pointer exception.");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
		this.dl.add(d);
	}
	
	void addFile(File f){
		this.fl.add(f);
	}		
}


public class DirectoryStructure {

	public static void main(String args[])
	{
		
		Directory d00 = new Directory(00, "dr00");
		
		Directory d10 = new Directory(10, "dr10");
		Directory d11 = new Directory(10, "dr11");
		Directory d20 = new Directory(10, "dr20");
		
		d00.addDirectory(d10);
		d00.addDirectory(d11);
		
		d10.addDirectory(d20);

	}
}



