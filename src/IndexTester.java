import java.io.File;
import java.io.IOException;

public class IndexTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Index it = new Index (); 
		it.initialize();
		it.add("test.txt");
		it.add("slunch.txt");
		
		it.remove("test.txt");
//		it.remove("snorgle.txt");
	it.remove("slunch.txt");
		
		
		
	}

}
