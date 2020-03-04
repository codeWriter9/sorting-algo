package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MethodEnumeratorTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testMethod() {
		try {
			MethodEnumerator itr = new MethodEnumerator("org.ghosh.sanjay.algos.MethodEnumerator");
			while (itr.hasNext()) {
				LOGGER.debug(itr.next().toString());
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
