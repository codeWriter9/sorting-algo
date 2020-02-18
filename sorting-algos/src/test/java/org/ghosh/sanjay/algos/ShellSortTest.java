package org.ghosh.sanjay.algos;

import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.SortingAlgo.SHELL_SORT;
import static org.ghosh.sanjay.algos.SortingFactories.sort;
import static org.ghosh.sanjay.algos.XCollections.isNonDecreasing;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * Unit test for simple App.
 * 
 */
public class ShellSortTest extends TestCase implements SortTest {
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ShellSortTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ShellSortTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testShellSortForIntegers() {
		assertTrue(asList(sortedIntegers).equals(sort(SHELL_SORT, unsortedIntegers).sort().sorted()));
	}

	public void testShellSortForString() {
		assertTrue(Arrays.asList(sortedStrings).equals(sort(SHELL_SORT, unsortedStrings).sort().sorted()));
	}

	public void testShellSortIntegerBulk() {
		List<Integer> integers = bulkLoad();
		assertTrue(isNonDecreasing(new ShellSort<Integer>(integers.toArray(new Integer[0])).sort().sorted()));
	}
}