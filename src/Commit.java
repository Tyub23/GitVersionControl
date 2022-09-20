import java.io.File;
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
	//i formally apologize for the code you're about to see
	
	public Commit (String summary, String author, String pTree, Commit parent) {
		pTree = null;
		this.summary  = summary; 
		this.author = author; 
		this.pTree= pTree;
		date = getDate();
		
		parent = null; 
		
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
	
	public void writesFile() {
		File f = new File("./objects/");
		
		
	}
}
