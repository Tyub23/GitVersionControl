import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob {
	private String blobHash = "";
	
	public Blob (String fileName) throws IOException {
		
		
		Path path = Path.of(fileName);
		String ogContents = Files.readString(path);
		blobHash = generateHash(ogContents);
		
		
		  String newFileDirectory = "./objects/" + blobHash; 
		  Path p = Paths.get(newFileDirectory);
	        try {
	            Files.writeString(p, ogContents, StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		
	}
	
	
	public static String generateHash (String input) {
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
	
	public String getBlobHash () {
		return blobHash;
	}
	
	
	
}
