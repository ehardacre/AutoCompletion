package autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Autocomplete implements AutocompleteInterface 
{
	private BinarySearchForAll binSearch; //binary searcher
	private List<Term> totalTerms; // all of the terms
	
	/**
	 * COnstructor for autocomplete
	 * @param aList - the complete list of terms
	 */
	public Autocomplete(List<Term> aList){
		BinarySearchForAll binSearch = new BinarySearchForAll();
		totalTerms = (ArrayList<Term>) aList;
	}
	
	
	/**
	 * @param prefix - the prefix we are searching for
	 * @returns the list of all of the matching terms from the total list
	 * in reverse weight order
	 */
	@Override
	public List<Term> allMatches(String prefix) {
		Collections.sort(totalTerms, Term.byPrefixOrder(prefix.length()));
		int firstOcc = BinarySearchForAll.firstIndexOf(totalTerms, new Term(prefix,0), Term.byPrefixOrder(prefix.length()));
		int lastOcc = BinarySearchForAll.lastIndexOf(totalTerms, new Term(prefix,0), Term.byPrefixOrder(prefix.length()));
		if(firstOcc == -1|| lastOcc == -1){
			return new ArrayList<Term>();
		}
		totalTerms = totalTerms.subList(firstOcc, lastOcc);
		Collections.sort(totalTerms, Term.byReverseWeightOrder());
		return totalTerms;
	}
	
}
