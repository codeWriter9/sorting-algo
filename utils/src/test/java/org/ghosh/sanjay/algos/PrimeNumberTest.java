package org.ghosh.sanjay.algos;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PrimeNumberTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public PrimeNumberTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(PrimeNumberTest.class);
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void testPrime() {
		PrimeService service = new PrimeNumberService(new BigInteger("23"));
		System.out.println(service.primes());
	}
	
	/**
	 * 
	 */
	public void testPrime2() {
		PrimeService service = new PrimeNumberService(new BigInteger("11"));
		System.out.println(service.primes());
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public void testConcurrentPrime() {
		PrimeService service = new ConcurrentPrimeNumberService(new BigInteger("23"));
		System.out.println(service.primes());
	}
	
	/**
	 * 
	 */
	public void testConcurrentPrime2() {
		PrimeService service = new ConcurrentPrimeNumberService(new BigInteger("11"));
		System.out.println(service.primes());
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public void testBlockingPrime() {
		PrimeService service = new BlockingPrimeNumberService(new BigInteger("23"));
		System.out.println(service.primes());
	}
	
	/**
	 * 
	 */
	public void testBlockingPrime2() {
		PrimeService service = new BlockingPrimeNumberService(new BigInteger("11"));
		System.out.println(service.primes());
	}
}