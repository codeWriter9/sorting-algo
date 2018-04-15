package org.ghosh.sanjay.algos;

import java.util.List;
import java.util.Arrays;

import static org.ghosh.sanjay.algos.Utils.isLessThan;
import static org.ghosh.sanjay.algos.Utils.swap;

/**
 *
 * Selection Sort
 *
 * The elements are selected from a window based on whether they are the minimum or maximum.
 * Once selected they are shifted into the front. For the next part of the array
 * The are continously selected untill window squeezes to zero 
 * 
 **/
public class SelectionSort<T extends Comparable<T>> implements Sort<T> 
{
	private T [] array;
	
	public SelectionSort(T [] array) {
		this.array = array;
	}
	
	@Override
	public Sort<T> sort() {
		for(int outer=0;outer<array.length;outer++) {
			int minIndex = outer;
			for(int inner=outer + 1;inner<array.length;inner++) {
				if(isLessThan(array[inner], array[minIndex])) {
					minIndex = inner;
				}
			}			
			swap(array, outer, minIndex);
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