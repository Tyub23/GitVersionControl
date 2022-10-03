import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CommitTester {
	public static void main (String [] args) throws NoSuchAlgorithmException, IOException
	{
		Index ind=new Index ();
		ind.initialize();
		ind.add("snorgle.txt");
		ind.add("slunch.txt");

		Commit initial=new Commit ("First commit", "Jake Wiczyk", null);
		initial.writesFile();
		ind.add("placeholder.txt");
		ind.add("test.txt");
		Commit second=new Commit ("Second commit", "Jake Wiczyk", initial);
		second.writesFile();

		ind.add("quick.txt");
		ind.add("great.txt");
		Commit third=new Commit ("Third commit", "Jake Wiczyk", second);
		third.writesFile();


		ind.add("n.txt");
		ind.add("last.txt");
		Commit fourth=new Commit ("Fourth commit", "Jake Wiczyk", third);
	//fourth.delete("snorgle.txt");
		fourth.writesFile();




	}

}
