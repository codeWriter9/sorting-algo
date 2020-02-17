package org.ghosh.sanjay.algos;

import static org.ghosh.sanjay.algos.XCollections.isNonDecreasing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ShellSortTest extends TestCase {
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
		assertTrue(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 })
				.equals(new ShellSort<Integer>(new Integer[] { 2, 3, 4, 1, 9, 8, 7, 6, 5 }).sort().sorted()));
	}

	public void testShellSortForString() {
		assertTrue(
				Arrays.asList(new String[] { "Eight", "Five", "Four", "Nine", "One", "Seven", "Six", "Three", "Two" })
						.equals(new ShellSort<String>(
								new String[] { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" })
										.sort().sorted()));
	}

	public void testShellSortIntegerBulk() {
		List<Integer> integers = new ArrayList<Integer>();
		for (int count = 101; count < 1001; count++)
			integers.add(new Integer(count));
		for (int count = 2001; count < 14091; count++)
			integers.add(new Integer(count));
		assertTrue(isNonDecreasing(new ShellSort<Integer>(integers.toArray(new Integer[0])).sort().sorted()));
	}
}