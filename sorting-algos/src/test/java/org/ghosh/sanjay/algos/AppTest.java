package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit test for simple App.
 */
@ExtendWith(SpringExtension.class)
public class AppTest

{

	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp() {
		LOGGER.info(" running App Test ");
		assertTrue(true);
	}
}
