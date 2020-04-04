package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.ghosh.sanjay.algos.Data.integer;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
@ExtendWith(SpringExtension.class)
public class DataTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testDataEqual() {
		assertTrue(integer(10).isEqual(integer(10)));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testDataGreater() {
		assertTrue(integer(1000).isGreater(integer(10)));
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void testDataSmaller() {
		assertTrue(integer(10).isSmaller(integer(1000)));
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Test()
	public void testIllegalException() {
		assertThrows(IllegalArgumentException.class, () -> new Data(null));
	}

}