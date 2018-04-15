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
}