package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.SortingAlgo.SELECTION_SORT;
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
public class SelectionSortTest implements SortTest {

	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testInsertionSortForIntegers() {
		assertTrue(asList(sortedIntegers).equals(sort(SELECTION_SORT, unsortedIntegers).sort().sorted()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testSelectionSortForString() {
		assertTrue(asList(sortedStrings).equals(sort(SELECTION_SORT, unsortedStrings).sort().sorted()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testSelectionSortIntegerBulk() {
		assertTrue(isNonDecreasing(sort(SELECTION_SORT, bulkLoadIntegers()).sort().sorted()));
	}
}