package org.ghosh.sanjay.algos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConstructorEnumeratorTest extends TestCase {
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ConstructorEnumeratorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ConstructorEnumeratorTest.class);
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public void testMethod() {
		try {
			ConstructorEnumerator itr = new ConstructorEnumerator("org.ghosh.sanjay.algos.ConstructorEnumerator");
			while(itr.hasNext()) {
				System.out.println(itr.next().toString());
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}

}