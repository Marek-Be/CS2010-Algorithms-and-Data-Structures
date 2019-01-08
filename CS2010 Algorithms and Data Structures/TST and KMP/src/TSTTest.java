import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.hamcrest.core.Every;
import org.junit.Test;

public class TSTTest {

  @Test(expected = IllegalArgumentException.class)
  public void testEmpty(){
    TST<Long> trie = new TST<>();
    assertEquals("size of an empty trie should be 0",0, trie.size());
    assertNull("searching an empty trie should return false",trie.contains(""));
    assertNull("getting from an empty trie should return null",trie.get(""));
  }
  
  @Test
  public void getFromTree()
  {
	  TST<Integer> trie = new TST<>();
	  trie.put("A", 1);
	  trie.put("B", 2);
	  trie.put("A", 3);
	  assertEquals(true,trie.contains("B"));
	  assertEquals(false,trie.contains("D"));
	  assertEquals(true,trie.contains("A"));
  }
  
  @Test
  public void testKeysWithPrefix()
  {
	  TST<Integer> trie = new TST<>();
	  trie.put("Hi", 1);
	  trie.put("Hello", 2);
	  trie.put("Hello1", 1);
	  trie.put("Hey", 2);
	  trie.put("Hello2", 5);
	  trie.put("Heya", 9);
	  trie.put("HELLO2", 1);
	  
	  assertEquals(3,trie.keysWithPrefix("Hello").size());
	  assertEquals("HELLO2",trie.keysWithPrefix("HELLO").get(0));
  }
  
  @Test
  public void checkSize()
  {
	  TST<Integer> trie = new TST<>();
	  trie.put("A", 1);
	  trie.put("B", 2);
	  trie.put("A", 3);
	  assertEquals(2,trie.size());
	  trie.put("A", 3);
	  assertEquals(2,trie.size());
	  trie.put("C", 3);
	  assertEquals(3,trie.size());
  }
  
  public static void main(String[] args) throws InterruptedException, IOException {
		File file = new File("BUSES_SERVICE_0.json");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		String myStrings[];
		String mySubstrings[];
		String str = new String(data, "UTF-8");
		myStrings = str.split("Destination\":\"");
		fis.close();
		TST<Integer> trie = new TST<>();

		String arrayOfDest[] = new String[myStrings.length-1];
		
		for (int i = 1; i < myStrings.length; i++)
		{
			mySubstrings = myStrings[i].split(",\"");
			arrayOfDest[i-1] = mySubstrings[0];
		}
		
		for (int i = 0; i < arrayOfDest.length; i++)
		{
			if (trie.contains(arrayOfDest[i]))
			{
				trie.put(arrayOfDest[i],trie.get(arrayOfDest[i])+1);
			}
			else
				trie.put(arrayOfDest[i],1);
		}
		
		System.out.println("Unique destinations:" + trie.size());
		System.out.println("Is a bus going to SOUTHSIDE:" + trie.contains("SOUTHSIDE"));
		
		LinkedList<String> queue = new LinkedList<String>();
		
		queue.add("Lul");
		
		
		String arrayOfWords[];
		
		BufferedReader br = new BufferedReader(new FileReader("google-books-common-words.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    arrayOfWords = everything.split("\n");
		} finally {
		    br.close();
		}
		

		TST<Integer> mytrie = new TST<>();
		
		for (int i = 35; i < arrayOfWords.length; i++) 	//so im blaming java for having its ints be too small
		{
			mytrie.put(arrayOfWords[i].split("\\t")[0], Integer.valueOf(arrayOfWords[i].split("\\t")[1].trim()));
		}
		
		System.out.println("Amount of words in the file:" + (mytrie.size() + 35));
		System.out.println("Frequency of word ALGORITHM:" + (mytrie.get("ALGORITHM")));
		System.out.println("Contains EMOJI?:" + (mytrie.contains("EMOJI")));
		System.out.println("Contains BLAH?:" + (mytrie.contains("BLAH")));
		System.out.println("How many words are there that start with TEST??:" + mytrie.keysWithPrefix("TEST").size());
	}
}
