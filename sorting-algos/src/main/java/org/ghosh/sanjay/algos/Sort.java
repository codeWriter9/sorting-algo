package org.ghosh.sanjay.algos;

import java.util.List;

/**
 * 
 * Behaviour for Sorting Algorithm
 * 
 * 
 * @author Sanjay Ghosh
 *
 * @param <T>
 */
public interface Sort<T extends Comparable<T>> 
{
	/**
	 *
	 * Returns the Algorithm Implementation
	 *
	 *
	 **/
	public Sort<T> sort();
	
	/**
	 *
	 * List based data structure of the data which has been sorted
	 *
	 *
	 **/
	public List<T> sorted();
	
	/**
	 *
	 * 
	 *
	 *
	 *
	 **/
	public T [] sortedArray();
		
}