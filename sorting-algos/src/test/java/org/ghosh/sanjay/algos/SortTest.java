package org.ghosh.sanjay.algos;

import java.util.ArrayList;
import java.util.List;

public interface SortTest {

	String[] unsortedStrings = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
	String[] sortedStrings = { "Eight", "Five", "Four", "Nine", "One", "Seven", "Six", "Three", "Two" };
	Integer[] unsortedIntegers = { 2, 3, 4, 1, 9, 8, 7, 6, 5 };
	Integer[] sortedIntegers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	default Integer [] bulkLoadIntegers() {
		List<Integer> integers = new ArrayList<Integer>();
		for (int count = 101; count < 1001; count++)
			integers.add(new Integer(count));
		for (int count = 2001; count < 14091; count++)
			integers.add(new Integer(count));
		return integers.toArray(new Integer[0]);
	}

}
