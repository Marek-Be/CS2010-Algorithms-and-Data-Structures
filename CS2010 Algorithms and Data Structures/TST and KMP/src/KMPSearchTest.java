import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class KMPSearchTest {

	@Test
	public void testEmpty() {
		assertEquals("Empty text or pattern is invalid", -1, KMPSearch.searchFirst("", ""));
		assertEquals("Empty text or pattern is invalid", 0, KMPSearch.searchAll("", ""));
		assertFalse("Empty text or pattern is invalid", KMPSearch.contains("", ""));
	}

	@Test
	public void testContains() {
		assertEquals("Empty text or pattern is invalid", true, KMPSearch.contains("MrChild", "ild"));
		assertEquals("Empty text or pattern is invalid", false, KMPSearch.contains("MrChild", "Cild"));
	}

	@Test
	public void testSearchFirst() {
		assertEquals("Empty text or pattern is invalid", 4, KMPSearch.searchFirst("MrChild", "ild"));
		assertEquals("Empty text or pattern is invalid", 7, KMPSearch.searchFirst("abcdefghijklmnopqrs", "hij"));
		assertEquals("Empty text or pattern is invalid", -1, KMPSearch.searchFirst("MrChild", "Cild"));
	}

	@Test
	public void testSearchAll() {
		assertEquals("Empty text or pattern is invalid", 3,
				KMPSearch.searchAll("omegalulomegalulomegalulomegal", "omegalul"));
		assertEquals("Empty text or pattern is invalid", 0, KMPSearch.searchAll("MrChild", "Cild"));
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		File file = new File("BUSES_SERVICE_0.json");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		String str = new String(data, "UTF-8");
		System.out.println("Amount of VehicleNo:" + KMPSearch.searchAll(str, "VehicleNo"));
		System.out.println("Does info on 16555 exist:" + KMPSearch.contains(str, "16555"));
		System.out.println("First index on HAMPTON PARK:" + KMPSearch.searchFirst(str, "HAMPTON PARK"));
		System.out.println("Does info on 9043409 exist:" + KMPSearch.contains(str, "9043409"));
	}
}


/*
1. How many total vehicles is there information on in the BUSES_SERVICE_0.json file, i.e., how
many occurrences of the pattern “VehicleNo” is there in the file?
1. 987 instances



2. Does the file contain information about the vehicle number 16555, i.e., is the pattern “16555”
present in the file?
2. Yes it does exist


3. Locate the first record about a bus heading to HAMPTON PARK, i.e., return the index of the
first occurrence of the pattern “HAMPTON PARK” in the file?
3. At 19774



4. Does the file contain information about the vehicle number 9043409?
4. No it does not exist







*/