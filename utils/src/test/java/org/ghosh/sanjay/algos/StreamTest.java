package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.ghosh.sanjay.algos.Streams.integers;
import static org.ghosh.sanjay.algos.Utils.evenCheckerInteger;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class StreamTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 */
	@Test
	public void testNaturalIntStream() {
		assertEquals(false, integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).allMatch(evenCheckerInteger()));
	}

	/**
	 * 
	 */
	@Test
	public void testEvenIntStream() {
		assertEquals(true, integers(2, 4, 6, 8, 10).allMatch(evenCheckerInteger()));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testOddIntStream() {
		assertEquals(false, integers(1, 3, 5, 7, 9, 11, 13).allMatch(evenCheckerInteger()));
	}
}