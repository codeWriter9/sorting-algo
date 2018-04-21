package org.ghosh.sanjay.algos;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static org.ghosh.sanjay.algos.Utils.inRange;
import static org.ghosh.sanjay.algos.Utils.isLessThan;
import static org.ghosh.sanjay.algos.Utils.isGreaterThan;
import static org.ghosh.sanjay.algos.Utils.swap;




public class MergeSort<T extends Comparable<T>> implements Sort<T> {	
	
	private T [] array;
	
	public MergeSort(T [] array) {
		this.array = array;
	}
	
	private int middle(int start, int end) {
		return end > start ? (end - start) / 2 : (start - end) / 2; 
	}
	
	private <T> void print(List<T> list) {
		System.out.println(list);
	}
	
	private <T> void print(T [] array) {
		print(Arrays.asList(array));
	}
	
	private void merge(int start, int pivot, int end) {		
		int leftSize = pivot - start +  1;
		int rightSize = end - pivot;		
		
		// Create two buffer arrays
		List<T> left = new ArrayList<T>(leftSize);
		List<T> right = new ArrayList<T>(rightSize);
		
				
		// copy left
		for(int i =0;i< ( pivot - start + 1) ; i ++ ) {			
			left.add(array[ start + i ]);
		}
		
		
		// copy right
		for(int i =0;i< ( end - pivot) ; i ++ ) {			
			right.add(array[ pivot + 1 + i ]);			
		}
		
		
		int mainIndex = start;
		int leftIndex = 0;
		int rightIndex = 0;
		
		while(leftIndex < leftSize && rightIndex < rightSize) {
			if(isLessThan(left.get(leftIndex), right.get(rightIndex))) {
				array[mainIndex] = left.get(leftIndex);
				leftIndex++;
			}
			else if(isGreaterThan(left.get(leftIndex), right.get(rightIndex))) {
				array[mainIndex] = right.get(rightIndex);
				rightIndex++;
			}
			else {
				array[mainIndex] = right.get(rightIndex);
				leftIndex++;
			}
			mainIndex++;
		}
		
		
		// Copy left Overs in case right is breached
		while(leftIndex < leftSize) {
			array[mainIndex] = left.get(leftIndex);
			mainIndex++;
			leftIndex++;
		}
		
		// Copy right Overs in case left is breached
		while(rightIndex < rightSize) {
			array[mainIndex] = right.get(rightIndex);
			mainIndex++;
			rightIndex++;
		}		
	}
	
	/**
	 * 0 8
	 * 0 3       |  4 9                    
	 * 0 0       |  1 3
	 *      0 0 returns
	 * 1 1       |  2 3
	 *      1 1 returns
	 *      2 3 returns
	 * 4 5       | 6 9
	 *      4 5 returns
	 * 6 6       | 7 9
	 *      6 6 returns
	 * 7 7       | 8 9 
	 *      7 7 returns
	 *      8 9 returns
	 **/
	private void call(int start, int end) {		
		if(end > start) {
			int pivot = ( end + start ) / 2;
			call(start, pivot);
			call(pivot + 1, end);
			merge(start, pivot, end);
		}		
	}
	
	@Override
	public Sort<T> sort() {		
		call(0, array.length - 1);	
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