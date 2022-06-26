package a03;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

/**
 * Term creates comparable objects that have both 
 * a query and a relative weight.
 * 
 * @author insanorcross, manyanair
 *@version Summer 2022
 */
public class Term implements Comparable<Term>
{
	private String query;
	private double weight;
	
	/**
	 * Initilize a new term with the given query and weight.
	 * 
	 * @param query 
	 * @param weight
	 */
	public Term(String query, double weight)
	{
		// Handle invalid inputs
		if (query == null)
			throw new NullPointerException("Query is null");
		if (weight < 0)
			throw new IllegalArgumentException("Weight is negative");
		
		// Initialize fields
		this.query = query;
		this.weight = weight;
	}
	
	/**
	 * Sorts the Terms in reverse order by weight
	 * 
	 * @return sorted array
	 */
	
	public static Comparator<Term> byReverseWeightOrder()
	{
		return new ByReverseWeightOrder();
	}

	/**
	 * Compares the weights of two Terms.
	 */
	public static class ByReverseWeightOrder implements Comparator<Term>
	{
		@Override
		public int compare(Term a, Term b)
		{
			return (int) (b.weight - a.weight);
		}
	}
	
	/**
	 * Compare the terms in lexicographic order 
	 * but using only the first r characters of each query.
	 * 
	 * @param r 
	 * @return
	 */
	public static Comparator<Term> byPrefixOrder(int r)
	{
		if (r < 0)
			throw new IllegalArgumentException();
		
		return new ByPrefixOrder(r);
	}
	
	public static class ByPrefixOrder implements Comparator<Term>
	{
		private final int r;
		
		public ByPrefixOrder(int r)
		{	
			this.r = r;
		}

		@Override
		public int compare(Term a, Term b) 
		{
			
			return a.query.substring(0, r).compareTo(b.query.substring(0, r));
		}
	}

	/**
	 * Used to sort the array in lexicographical order by query
	 */
	public int compareTo(Term that)
	{
		return this.query.compareTo(that.query);
	}
	
	/**
	 * Returns the weight and query as a string
	 * 
	 * @return weight query
	 */
	public String toString()
	{
		return (this.weight + "\t" + this.query);
	}
	

	public static void main(String[] args)
	{
		// Test compareTo
		Term[] terms = {new Term("bcde", 3), new Term("abcef", 1), new Term("abc", 2), new Term ("zzz", 5)};
		Arrays.sort(terms);
		for(Term t: terms)
			StdOut.println(t.toString());
		StdOut.println();
		
		// Test byReverseWeightOrder
		Arrays.sort(terms, Term.byReverseWeightOrder());
		for(Term t: terms)
			StdOut.println(t.toString());
		StdOut.println();
		
		// Test byPrefixOrder
		Arrays.sort(terms, Term.byPrefixOrder(2));
		for(Term t: terms)
			StdOut.println(t.toString());
		StdOut.println();		
	}

}
