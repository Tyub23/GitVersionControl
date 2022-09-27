import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date; 

public class Commit {
	private String summary; 
	private String author; 
	private String date; 
	private String pTree; 
	private Commit parent;
	private Commit child; 
	private Tree tree;
	//i hope it works
	
	public Commit (String summary, String author, Commit parent) throws NoSuchAlgorithmException, IOException {
	
		this.summary  = summary; 
		this.author = author; 
		ArrayList<String> list=new ArrayList<String>();
		BufferedReader br=new BufferedReader(new FileReader("index"));
		String name;
		while (br.ready())
			{
			String s=br.readLine();
			int j=s.indexOf(' ');
			name=s.substring(0,j);
			String sha=s.substring (j+3);
			list.add("blob : "+sha+" "+name);
			}
		br.close();
		if (parent!= null)
		{
			String pSha=parent.getTree().getSha();
			list.add("tree : "+pSha);
		}
		
		tree=new Tree(list);
		File index=new File("index");
		index.delete();
		index.createNewFile();
		
		if (parent != null) {
			this.parent = parent;
			parent.setChild(this);
		}
		if (child != null) {
			child.setParent(this);
		}
		date = getDate();	
		
	}
	
	public void setParent(Commit p) {
		parent = p; 
	}
	
	public void setChild(Commit c) {
		child = c; 
	}
	
	public String getpTree() {
		return pTree; 
	}
	
	public String getDate() {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy"); 
		date = formatter.format(d); 
		
		return date; 
	}
	public Tree getTree()
	{
		return tree;
	}
	
	public String hashify (String input) {
		 try {
	           
	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	 
	            byte[] messageDigest = md.digest(input.getBytes());
	 
	            BigInteger no = new BigInteger(1, messageDigest);
	 
	            String hashtext = no.toString(16);
	 
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	 
	            return hashtext;
	        }
	 
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public void writesFile() throws IOException {
		String parentPointer; 
		String childPointer;
		if (parent == null || parent.getpTree() == null) {
			parentPointer = ""; 
		} else {
			parentPointer = parent.getpTree();
		}
		
		if (child == null || child.getpTree() == null) {
			childPointer = ""; 
		} else {
			childPointer = child.getpTree();
		}
		
		String hashedContents = hashify(summary + date + author + parentPointer); 
		//hashedContents = hashify(summary + date + author + parent.getpTree());
		File f = new File("objects/" + hashedContents);
		FileWriter fw = new FileWriter(f); 
		
		fw.write(tree + "\n" + parentPointer + "\n" + childPointer + "\n" + author + "\n" + date + "\n" + summary);
		fw.close();
	}
}
