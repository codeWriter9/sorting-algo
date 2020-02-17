package org.ghosh.sanjay.algos;

import static org.ghosh.sanjay.algos.XCollections.collectToString;
import static org.ghosh.sanjay.algos.XCollections.sumOfIntegers;
import static org.ghosh.sanjay.algos.XCollections.sumOfLongs;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UtilTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public UtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(UtilTest.class);
	}

	/**
	 * 
	 * Test the Utils to collection String
	 * 
	 */
	public void testUtilsCollectionToStringNullable() {
		assertEquals(true, "0,1,2,3,4,5,6,7,8,9"
				.equals(collectToString(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }), null)));
	}

	/**
	 * 
	 * Test the utils to collection string where delimiter is not null
	 * 
	 */
	public void testUtilsCollectionToStringNotNull() {
		assertEquals(true, "0|1|2|3|4|5|6|7|8|9"
				.equals(collectToString(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }), "|")));
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void testUtilsSummation() {
		assertEquals(true, sumOfIntegers(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })).equals(45));
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void testUtilsSummation2() {
		assertEquals(true,
				sumOfLongs(Arrays.asList(new Long[] { 0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L })).equals(45L));
	}
}