package org.ghosh.sanjay.algos;

import java.util.List;

import static org.ghosh.sanjay.algos.Utils.isLessThan;

/**
 *
 * This should be moved away in a core Test Utils maven project
 *
 *
 **/
public class TestUtils {
	
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