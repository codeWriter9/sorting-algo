package org.ghosh.sanjay.algos;

import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.Utils.sumOfIntegersDefault;
import static org.ghosh.sanjay.algos.Utils.sumOfNumbers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SummationTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public SummationTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SummationTest.class);
	}

	/**
	 * 
	 * 
	 */
	public void testSummation1() {
		assertEquals(Integer.valueOf(55), sumOfIntegersDefault(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
	}

	/**
	 * 
	 * 
	 */
	public void testSummation2() {
		assertEquals(Integer.valueOf(55), sumOfNumbers(11));
	}	
}