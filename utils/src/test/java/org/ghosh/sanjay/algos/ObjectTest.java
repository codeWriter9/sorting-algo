package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ObjectTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * Test the creation of a new Array List through lambda
	 * 
	 */
	@Test
	public void testObjectCloning() {
		try {
			this.clone();
		} catch (Exception e) {
			assertEquals(true, e instanceof CloneNotSupportedException);
		}
	}

	@Test
	public void testObjectEquals() {
		Object x = new Object();
		Object y = new Object();
		assertEquals(true, x.equals(x));
		assertEquals(true, y.equals(y));
		assertEquals(false, x.equals(y));

		Object p = new Object();
		Object q = p;
		assertEquals(true, p.equals(p));
		assertEquals(true, q.equals(q));
		assertEquals(true, p.equals(q));
	}
}