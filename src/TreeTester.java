import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeTester {
	static ArrayList <String> treeList = new ArrayList <String>(); 
	//String add; 
	//String add2; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String add = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f";
		String add2 = "blob : 01d82591292494afd1602d175e165f94992f6f5f";
		treeList.add(add);
		treeList.add(add2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File f = new File ("399c852940ac76e1f375fcfcedc36234ccda3f6a.txt");
		f.delete();
	}
 
	@Test
	void testFileCreation() throws NoSuchAlgorithmException, IOException {
		

		Tree t = new Tree (treeList);
		File f = new File ("./objects/399c852940ac76e1f375fcfcedc36234ccda3f6a.txt");
		assertTrue(f.exists());
		
		//String content = "";
		Scanner s = new Scanner (f); 
		assertEquals("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f", s.nextLine());
		assertEquals("blob : 01d82591292494afd1602d175e165f94992f6f5f", s.nextLine());
		s.close();
		
		 
		
	}

}
