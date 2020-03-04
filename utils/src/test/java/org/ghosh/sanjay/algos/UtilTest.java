package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.ghosh.sanjay.algos.XCollections.collectToString;
import static org.ghosh.sanjay.algos.XCollections.sumOfIntegers;
import static org.ghosh.sanjay.algos.XCollections.sumOfLongs;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UtilTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * Test the Utils to collection String
	 * 
	 */
	@Test
	public void testUtilsCollectionToStringNullable() {
		assertEquals(true, "0,1,2,3,4,5,6,7,8,9"
				.equals(collectToString(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }), null)));
	}

	/**
	 * 
	 * Test the utils to collection string where delimiter is not null
	 * 
	 */
	@Test
	public void testUtilsCollectionToStringNotNull() {
		assertEquals(true, "0|1|2|3|4|5|6|7|8|9"
				.equals(collectToString(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }), "|")));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testUtilsSummation() {
		assertEquals(true, sumOfIntegers(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })).equals(45));
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testUtilsSummation2() {
		assertEquals(true,
				sumOfLongs(Arrays.asList(new Long[] { 0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L })).equals(45L));
	}
}