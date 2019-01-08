
public class KMPSearch {

	/*
	 * Bus Service Questions:
	 *
	 * 1. How many total vehicles is there information on?
	 * 1. 987 instances
	 *
	 * 2. Does the file contain information about the vehicle number 16555?
	 * 2. Yes it does
	 *
	 * 3. Locate the first record about a bus heading to HAMPTON PARK
	 * 3. At 19774
	 *
	 * 4. Does the file contain information about the vehicle number 9043409?
	 * 4. No, it does not exist
	 */

	/*
	 * The method checks whether a pattern pat occurs at least once in String
	 * txt.
	 *
	 */
	public static boolean contains(String txt, String pat) {
		if (pat.length() == 0 || txt.length() == 0)
		{
			return false;
		}
		
		int target = 0;
		int search = 0;
		
		while (search < txt.length())
		{
			if (txt.charAt(search) == pat.charAt(target))
			{
				target++;
				search++;
				if (target == pat.length())
				{
					return true;
				}
			}
			else
			{
				search++;
				target = 0;
			}
		}
		return false;
	}

	/*
	 * The method returns the index of the first ocurrence of a pattern pat in
	 * String txt. It should return -1 if the pat is not present
	 */
	public static int searchFirst(String txt, String pat) {
		if (pat.length() == 0 || txt.length() == 0)
		{
			return -1;
		}
		
		int target = 0;
		int search = 0;
		
		while (search < txt.length())
		{
			if (txt.charAt(search) == pat.charAt(target))
			{
				target++;
				search++;
				if (target == pat.length())
				{
					return search-pat.length();
				}
			}
			else
			{
				search++;
				target = 0;
			}
		}
		return -1;
	}

	/*
	 * The method returns the number of non-overlapping occurences of a pattern
	 * pat in String txt.
	 */
	public static int searchAll(String txt, String pat) {
		if (pat.length() == 0 || txt.length() == 0)
		{
			return 0;
		}
		
		int count = 0;
		
		int target = 0;
		int search = 0;
		
		while (search < txt.length())
		{
			if (txt.charAt(search) == pat.charAt(target))
			{
				target++;
				search++;
				if (target == pat.length())
				{
					count++;
					target = 0;
				}
			}
			else
			{
				search++;
				target = 0;
			}
		}
		return count;
	}
}
