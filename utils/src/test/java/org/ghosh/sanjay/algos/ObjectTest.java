package org.ghosh.sanjay.algos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ObjectTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ObjectTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ObjectTest.class);
	}

	/**
	 * 
	 * Test the creation of a new Array List through lambda
	 * 
	 */
	public void testObjectCloning() {
		try {
			this.clone();
		} catch (Exception e) {
			assertEquals(true, e instanceof CloneNotSupportedException);
		}
	}
	
	public void testObjectEquals() {
		Object x = new Object();
		Object y = new Object();
		assertEquals(true, x.equals(x));
		assertEquals(true, y.equals(y));
		assertEquals(false, x.equals(y));
		
		Object p = new Object();
		Object q = p;
		assertEquals(true, p.equals(p));
		assertEquals(true, q.equals(q));
		assertEquals(true, p.equals(q));
	}	
}