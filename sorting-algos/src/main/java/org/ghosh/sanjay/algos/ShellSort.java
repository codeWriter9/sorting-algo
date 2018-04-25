package org.ghosh.sanjay.algos;

import java.util.List;
import java.util.Arrays;

import static org.ghosh.sanjay.algos.Utils.isLessThan;

/**
 *
 * Shell Sort
 *
 * The elements are inserted into the right places of an increasing 
 * window which will ultimately encompass the entire array. Here the gaps 
  * start as very long and ultimately collapse.
 *
 **/
public class ShellSort<T extends Comparable<T>> implements Sort<T> {
	
	private T [] array;
	
	public ShellSort(T [] array) {
		this.array = array;
	}
	
	/**
	 *
	 * Re-use Insertion sort
	 *
	 **/	
	private void insertionSort(int gap) {		
		for(int outer = gap; (outer - 1 + gap) < array.length; outer = outer + gap) {
			T key = array[outer]; // the Key which will be checked
			int inner = outer - gap; // the index which is the limit of sorted part
			while(inner >= 0 && isLessThan(key, array[inner])) {							    
				array[ inner + gap] = array [ inner ] ; // slide elements gap back. Remember we have saved the key
				inner = inner - gap; // loop till inner is zero
			}
			array[ inner + gap ] = key; // inner + gap because inner would - gap to get out. 			
		}		
	}
	
	/**
	 *
	 * 2, 3, 4, 1, 9, 8, 7, 6, 5
	 * 2a[i], 3[Key], 4, 1, 9, 8, 7, 6, 5
	 * 2, 3a[i], 4[Key], 1, 9, 8, 7, 6, 5   
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 **/
	@Override
	public Sort<T> sort()
	{		
		for(int gap = array.length / 3 ; gap > 0 ; gap = gap / 3) {
			insertionSort(gap);	
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