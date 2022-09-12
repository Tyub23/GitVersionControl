import java.io.File;

public class Index {
	//initialize a project which creates an empty file named index and a direcotry named objects
	public Index () {
		new File ("/path/objects").mkdirs(); 
		try {
	         File file = new File("index");
	         file.createNewFile();	       
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
}
