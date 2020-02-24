package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.SortingAlgo.MERGE_SORT;
import static org.ghosh.sanjay.algos.SortingFactories.sort;
import static org.ghosh.sanjay.algos.XCollections.isNonDecreasing;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit test for simple App.
 */
@ExtendWith(SpringExtension.class)
public class MergeSortTest implements SortTest {

	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 */
	@Test
	public void testMergeSortForIntegers() {
		assertTrue(asList(sortedIntegers).equals(sort(MERGE_SORT, unsortedIntegers).sort().sorted()));
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void testMergeSortForString() {
		assertTrue(asList(sortedStrings).equals(sort(MERGE_SORT, unsortedStrings).sort().sorted()));
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void testMergeSortIntegerBulk() {
		assertTrue(isNonDecreasing(sort(MERGE_SORT, bulkLoadIntegers()).sort().sorted()));
	}
}