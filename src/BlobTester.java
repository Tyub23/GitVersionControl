
import java.io.IOException;


public class BlobTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String dog = "some content";
		String hashed;
		hashed = Blob.generateHash(dog);
		System.out.println(hashed);
		Blob blob = new Blob ("test.txt");
		//System.out.println(b.getBlobHash());
		
		
	//	Blob blob = new Blob(".\
	}

}
