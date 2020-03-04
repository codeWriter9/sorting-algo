package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PrimeNumberTest  {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());
	
	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testPrime() {
		PrimeService service = new PrimeNumberService(new BigInteger("23"));
		LOGGER.debug(service.primes().toString());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPrime2() {
		PrimeService service = new PrimeNumberService(new BigInteger("11"));
		LOGGER.debug(service.primes().toString());
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testConcurrentPrime() {
		PrimeService service = new ConcurrentPrimeNumberService(new BigInteger("23"));
		LOGGER.debug(service.primes().toString());
	}
	
	/**
	 * 
	 */
	@Test
	public void testConcurrentPrime2() {
		PrimeService service = new ConcurrentPrimeNumberService(new BigInteger("11"));
		LOGGER.debug(service.primes().toString());
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testBlockingPrime() {
		PrimeService service = new BlockingPrimeNumberService(new BigInteger("23"));
		LOGGER.debug(service.primes().toString());
	}
	
	/**
	 * 
	 */
	@Test
	public void testBlockingPrime2() {
		PrimeService service = new BlockingPrimeNumberService(new BigInteger("11"));
		LOGGER.debug(service.primes().toString());
	}
}