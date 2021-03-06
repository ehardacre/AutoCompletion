package autocomplete;

import java.util.List;
/** Interface for objects that can find all items matching a prefix
 * @author: Kim Bruce
 * @version:  8/12/2017
 **/

public interface AutocompleteInterface {

	/**
	 * @param prefix
	 *            string to be matched
	 * @return List of all matching terms in descending order by weight
	 */
	List<Term> allMatches(String prefix);
}
