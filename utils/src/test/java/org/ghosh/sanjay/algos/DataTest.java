package org.ghosh.sanjay.algos;

import static org.ghosh.sanjay.algos.Data.integer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DataTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public DataTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DataTest.class);
	}

	public void testDataEqual() {
		assertTrue(integer(10).isEqual(integer(10)));
	}

	public void testDataGreater() {
		assertTrue(integer(1000).isGreater(integer(10)));
	}

	public void testDataSmaller() {
		assertTrue(integer(10).isSmaller(integer(1000)));
	}

}