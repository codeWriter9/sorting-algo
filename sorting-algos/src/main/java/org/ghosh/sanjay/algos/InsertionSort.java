package org.ghosh.sanjay.algos;

import java.util.List;
import java.util.Arrays;

import static org.ghosh.sanjay.algos.Utils.isLessThan;

/**
 *
 * Insertion Sort
 *
 * The elements are inserted into the right places of an increasing 
 * window which will ultimately encompass the entire array.
 *
 **/
public class InsertionSort<T extends Comparable<T>> implements Sort<T>
{
	private T [] array;
	
	public InsertionSort(T [] array) {
		this.array = array;
	}
	
	@Override
	public Sort<T> sort()
	{
		for(int outer = 1; outer < array.length; outer++) {
			T key = array[outer]; // the Key which will be checked
			int inner = outer - 1; // the index which is the limit of sorted part
			while(inner >=0 && isLessThan(key, array[inner])) {				
				array[ inner + 1] = array [ inner ] ; // slide elements one back. Remember we have saved the key
				inner = inner - 1; // loop till inner is zero
			}
			array[ inner + 1 ] = key; // inner + 1 because inner would -1 to get out. 			
		}
		return this;
	}
	
	@Override
	public List<T> sorted()
	{
		return Arrays.asList(array);
	}	

	@Override
	public T [] sortedArray()
	{
		return array;
	}
}