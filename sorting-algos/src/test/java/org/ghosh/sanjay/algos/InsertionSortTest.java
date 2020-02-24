package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.SortingAlgo.INSERTION_SORT;
import static org.ghosh.sanjay.algos.SortingFactories.sort;
import static org.ghosh.sanjay.algos.XCollections.isNonDecreasing;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 * Unit test for simple App.
 */
@ExtendWith(SpringExtension.class)
public class InsertionSortTest implements SortTest {

	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 */
	@Test
	public void testInsertionSortForIntegers() {
		LOGGER.debug(" insertion sort ");
		assertTrue(asList(sortedIntegers).equals(sort(INSERTION_SORT, unsortedIntegers).sort().sorted()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testInsertionSortForString() {
		LOGGER.debug(" insertion sort ");
		assertTrue(asList(sortedStrings).equals(sort(INSERTION_SORT, unsortedStrings).sort().sorted()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testInsertionSortIntegerBulk() {
		LOGGER.debug(" insertion sort ");
		assertTrue(isNonDecreasing(sort(INSERTION_SORT, bulkLoadIntegers()).sort().sorted()));
	}
}