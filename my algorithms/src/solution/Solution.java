package solution;

import java.util.ArrayList;
import java.util.Collections;
// TODO: Auto-generated Javadoc

/**
 * The Class Solution.
 */
public class Solution
{

	/** The solution list. */
	private ArrayList<String> solutionList;

	/**
	 * Instantiates a new solution.
	 */
	public Solution()
	{
		super();
		this.solutionList = new ArrayList<String>();
	}
	
	/**
	 * Gets the solution list.
	 *
	 * @return the solution list
	 */
	public ArrayList<String> getSolutionList()
	{
		return solutionList;
	}
	
	/**
	 * Sets the solution list.
	 *
	 * @param solutionList the new solution list
	 */
	public void setSolutionList(ArrayList<String> solutionList)
	{
		this.solutionList = new ArrayList<String>(solutionList);
	}

	/**
	 * Sortback.
	 */
	public void sortback()
	{
		Collections.reverse(solutionList);

	}
	
	/**
	 * Prints the.
	 */
	public void print()
	{
		if (solutionList.size() == 0)
		{
			System.out.println("Start point = Finish point, no rout calculated");
		}
		else
		{
			int i = 0;
			int k = 0;
			if (!solutionList.isEmpty()) {

				for (String string : solutionList) {
					if	(k == 9){
						System.out.println();
						k = 0;
					}
					if(i==solutionList.size()-1){
						System.out.print(string);
						continue;
					}
					System.out.print(string + " -> ");
					i++;
					k++;
				}
			}
			System.out.println("\nNodes to solution: " + (i+1));
		}
	}
}


