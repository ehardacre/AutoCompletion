package autocomplete;


import java.util.Comparator;

public class Term implements Comparable<Term> {

	
	private String query = ""; //The search term
	private long weight = 0; //The frequency of this term

	/**
	 * Initializes a term with the given query string and weight.
	 * 
	 * @param query
	 *            word to be stored
	 * @param weight
	 *            associated frequency
	 */
	public Term(String query, long weight) {
		this.query  = query;
		this.weight = weight;
	}

	/**
	 * @return comparator ordering elts by descending weight
	 */
	public static Comparator<Term> byReverseWeightOrder() {
		return (Term termOne, Term termTwo)->
		-Long.valueOf(termOne.getVal()).compareTo(Long.valueOf(termTwo.getVal()));
	}

	/**
	 * @param r
	 *            Number of initial characters to use in comparing words
	 * @return comparator using lexicographic order, but using only the first r
	 *         letters of each word
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		//return a lambda comparator that compares two terms
		return (Term termOne, Term termTwo)->{
			int tempr = r;
			if (r > termOne.getKey().length()) tempr = termOne.getKey().length();
			if (r > termTwo.getKey().length()) tempr = termTwo.getKey().length();
			return termOne.getKey().substring(0, tempr).compareTo(termTwo.getKey().substring(0,tempr));
		};
	}

	/**
	 * @param that
	 *            Term to be compared
	 * @return -1, 0, or 1 depending on whether the word for that is smaller,
	 *         the same or larger than for the receiver
	 */
	public int compareTo(Term that) {
		return Integer.valueOf(this.getKey().length()).compareTo(Integer.valueOf(that.getKey().length()));
	}

	/**
	 * @return a string representation of this term in the following format: the
	 *         weight, followed by 2 tabs, followed by the word.
	 **/
	public String toString() {
		return weight + "		" + query;
	}
	
	/**
	 * @return the search query associated with this term
	 */
	public String getKey() {
		return query;
	}
	
	/**
	 * @return the weight of this search term
	 */
	public long getVal(){
		return weight;
	}

}
