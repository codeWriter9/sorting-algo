package org.ghosh.sanjay.algos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MethodEnumeratorTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public MethodEnumeratorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MethodEnumeratorTest.class);
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void testMethod() {
		try {
			MethodEnumerator itr = new MethodEnumerator("org.ghosh.sanjay.algos.MethodEnumerator");
			while(itr.hasNext()) {
				System.out.println(itr.next().toString());
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}

}
