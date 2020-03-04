package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.Utils.sumOfNumbers;
import static org.ghosh.sanjay.algos.XCollections.sumOfIntegersDefault;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SummationTest {
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 */
	@Test
	public void testSummation1() {
		assertEquals(Integer.valueOf(55), sumOfIntegersDefault(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void testSummation2() {
		assertEquals(Integer.valueOf(55), sumOfNumbers(11));
	}
}