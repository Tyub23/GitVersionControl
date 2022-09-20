import java.io.IOException;

public class CommitTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Commit p = new Commit("parent commit", "Karl Marx", null, null);
		Blob b = new Blob ("slunch.txt");
		Commit c = new Commit ("This commit is swell!", "Karl Marx", "./objects/da56bf2e70b180f3cc80786b8195a108794b79c2", p);
		c.writesFile();
		System.out.println(c.getpTree());
		System.out.println(c.getDate());
	}

}
