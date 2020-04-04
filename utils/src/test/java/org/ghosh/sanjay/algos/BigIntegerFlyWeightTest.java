package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;
import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigInteger;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BigIntegerFlyWeightTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	@Test
	public void testBigInteger1() {
		Instant start = now();
		for (Integer loop = 0; loop < 10000; loop++) {
			new BigInteger(loop + "");
		}
		Instant end = now();
		LOGGER.info(" Object creation takes " + start.until(end, MILLIS) + " ms ");
	}

	@Test
	public void testBigInteger2() {
		Instant start = now();
		for (Integer loop = 0; loop < 10000; loop++) {
			BigInteger.valueOf(loop);
		}
		Instant end = now();
		LOGGER.info(" Fly Weight Pattern " + start.until(end, MILLIS) + " ms ");
	}
}