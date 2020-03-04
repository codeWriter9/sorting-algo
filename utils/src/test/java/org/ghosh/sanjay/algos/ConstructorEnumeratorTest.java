package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ConstructorEnumeratorTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());
	
	
	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testConstructor() {
		try {
			ConstructorEnumerator itr = new ConstructorEnumerator("org.ghosh.sanjay.algos.ConstructorEnumerator");
			while(itr.hasNext()) {
				LOGGER.error(itr.next().toString());
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}

}