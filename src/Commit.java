import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.Date; 

public class Commit {
	private String summary; 
	private String author; 
	private String date; 
	private String pTree; 
	private Commit parent;
	private Commit child; 
	//i formally apologize for the code you're about to see
	
	public Commit (String summary, String author, String pTree, Commit parent) {
	
		this.summary  = summary; 
		this.author = author; 
		if (pTree != null) {
			this.pTree = pTree;
		}
		if (parent != null) {
			this.parent = parent;
			parent.setChild(this);
		}
		if (child != null) {
			child.setParent(this);
		}
		date = getDate();
		//child.setParent(this);
	//	parent.setChild(this);
		
		
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
		File f = new File("./objects/" + hashedContents);
		FileWriter fw = new FileWriter(f); 
		
		fw.write(pTree + "\n" + parentPointer + "\n" + childPointer + "\n" + author + "\n" + date + "\n" + summary);
		fw.close();
	}
}
