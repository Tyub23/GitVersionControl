import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		
		ind.add("placeholder.txt");
		ind.add("test.txt");
		Commit second=new Commit ("Second commit", "Jake Wiczyk", initial);
		

		ind.add("quick.txt");
		ind.add("great.txt");
		Commit third=new Commit ("Third commit", "Jake Wiczyk", second);
		


		ind.add("n.txt");
		ind.add("last.txt");
		Commit fourth=new Commit ("Fourth commit", "Jake Wiczyk", third);
		//BufferedReader head=new BufferedReader(new FileReader(new File("head")));
	//	String s=head.readLine();
		
		//while ()
		initial.writesFile();
		second.writesFile();
		third.writesFile();
		fourth.writesFile();




	}

}
