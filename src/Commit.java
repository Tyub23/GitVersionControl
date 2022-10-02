import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date; 

public class Commit {
	private String summary; 
	private String author; 
	private String date; 
	private String pTree; 
	private File index;
	private File head;
	private ArrayList<String> list;
	private Commit parent;
	private Commit child; 
	private Tree tree;
	//i hope it works
	
	public Commit (String summary, String author, Commit parent) throws NoSuchAlgorithmException, IOException {
	
		this.summary  = summary; 
		this.author = author; 
		
		
		if (parent != null) {
			this.parent = parent;
			parent.setChild(this);
		}
		if (child != null) {
			child.setParent(this);
		}
		date = getDate();	
		
	}
	public void init() throws IOException, NoSuchAlgorithmException
	{
		list=new ArrayList<String>();
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
			pTree=pSha;
			list.add("tree : "+pSha);
		}
		
		tree=new Tree(list);
		index=new File("index");
		index.delete();
		index.createNewFile();
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
	
	
	public String getDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		String dt = dateFormat.format(cal.getTime());
		return dt; // should I use the member variable here?
	

		
		
	}
	public boolean delete (String fileName) throws IOException
	{
		File file=new File (fileName);
		if (!file.exists())
			return false;
		file.delete();
		FileWriter fw=new FileWriter(index);
		fw.write("*deleted* "+fileName+"\n");
		fw.close();
		return true;
	}
	public boolean edit (String fileName) throws IOException //assumes edit has been made externally
	{
		File file=new File (fileName);
		if (!file.exists())
			return false;
		FileWriter fw=new FileWriter(index);
		fw.write("*edited* "+fileName+"\n");
		fw.close();
		return true;
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
			parentPointer = getpTree();
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
		
		fw.write(tree.getSha() + "\n" + parentPointer + "\n" + childPointer + "\n" + author + "\n" + date + "\n" + summary);
		fw.close();
	}
}
