package org.ghosh.sanjay.algos;

import java.util.List;

public class Utils {

	public static <T extends Comparable<T>> boolean isLessThan(T first ,T second) 	{
		return first.compareTo(second) < 0;	
	}
	
	
	public static <T extends Comparable<T>> boolean isNonDecreasing(List<T> list) {
		if(list != null || list.isEmpty() == false) {
			for(int index=0;index<list.size() - 1;index++) {
				if(isLessThan(list.get(index + 1), list.get(index))) return false;
			}	
			return true;
		}
		else return true;
	}
	
	public static <T> void swap(T [] array, int index, int anotherIndex) {
		if(array != null || array.length > max(index, anotherIndex) ) {
			T sub = array[ index ];
			array[ index ] = array[ anotherIndex ];
			array[ anotherIndex ] = sub;
		}
	}

	public static int max(int first, int second) {
		return first >= second ? first : second;
	}
}