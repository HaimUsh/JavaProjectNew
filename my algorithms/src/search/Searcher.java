package search;
import solution.Solution;


// TODO: Auto-generated Javadoc
/**
 * The Interface Searcher.
 */
public interface Searcher
{
	
	/**
	 * Search.
	 *
	 * @param s the s
	 * @return the solution
	 */
	public Solution search(Searchable s);
	
	/**
	 * Gets the number of nodes evaluated.
	 *
	 * @return the number of nodes evaluated
	 */
	public int getNumberOfNodesEvaluated();
}
