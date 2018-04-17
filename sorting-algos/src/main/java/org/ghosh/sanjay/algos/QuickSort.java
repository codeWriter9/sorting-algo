package org.ghosh.sanjay.algos;

import java.util.List;
import java.util.Arrays;

import static org.ghosh.sanjay.algos.Utils.inRange;
import static org.ghosh.sanjay.algos.Utils.isLessThan;
import static org.ghosh.sanjay.algos.Utils.isGreaterThan;
import static org.ghosh.sanjay.algos.Utils.swap;




public class QuickSort<T extends Comparable<T>> implements Sort<T> {
	
	private T [] array;
	
	public QuickSort(T [] array) {
		this.array = array;
	}
	
	private int partition(int low, int high) {
		if(isLessThan(new Integer(low), new Integer(high))) {
			T pivot = array[high];
			while(true) {
				int i = low;
				while(inRange(array, i) && isGreaterThan(pivot, array[i])) {
					i = i + 1;
				}
				int j = high;
				while(inRange(array, j) && isGreaterThan(array[j], pivot)) {
					j = j - 1;
				}
				if(i >= j) return j; // if low is greater than high
			
				swap(array, i, j);// else swap
			}
		}
		else return -1;
		
	}
	
	private void call(int low, int high) {
		int border = partition(low, high);
		if(border != -1) call(low, border - 1);
		if(border != -1) call(border, high);
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