import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Index {
	private HashMap <String, String> list = new HashMap <String, String>();
	//initialize a project which creates an empty file named index and a direcotry named objects
	public Index () {
		
	}
	
	public void initialize () {
		File objectsDirectory = new File ("./objects"); 
		objectsDirectory.mkdirs();
	//	new File ("./objects").mkdirs(); 
		File created = new File("index");
		
	}
	
	public void add (String fileName) throws IOException {
		Blob b = new Blob(fileName);
		list.put(fileName, b.getBlobHash());
		FileWriter fw = new FileWriter("index");
		fw.write(fileName + " : " + b.getBlobHash());
		fw.close();
	}
	
	
}
