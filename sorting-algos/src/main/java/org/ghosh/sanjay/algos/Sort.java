package org.ghosh.sanjay.algos;

import java.util.List;

public interface Sort<T extends Comparable<T>> 
{
	/**
	 *
	 *
	 *
	 *
	 **/
	public Sort<T> sort();
	
	/**
	 *
	 *
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