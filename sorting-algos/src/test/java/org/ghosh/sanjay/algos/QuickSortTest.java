package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.SortingAlgo.QUICK_SORT;
import static org.ghosh.sanjay.algos.SortingFactories.sort;
import static org.ghosh.sanjay.algos.XCollections.isNonDecreasing;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;



/**
 * Unit test for simple App.
 */
@ExtendWith(SpringExtension.class)
public class QuickSortTest implements SortTest {

	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testQuickSortForIntegers() {
		assertTrue(asList(sortedIntegers).equals(sort(QUICK_SORT, unsortedIntegers).sort().sorted()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testQuickSortForString() {
		assertTrue(asList(sortedStrings).equals(sort(QUICK_SORT, unsortedStrings).sort().sorted()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testQuickSortIntegerBulk() {
		List<Integer> integers = new ArrayList<Integer>();
		for (int count = 2001; count < 14091; count++)
			integers.add(new Integer(count));
		for (int count = 101; count < 1001; count++)
			integers.add(new Integer(count));
		assertTrue(isNonDecreasing(sort(QUICK_SORT, integers.toArray(new Integer[0])).sort().sorted()));
	}
}