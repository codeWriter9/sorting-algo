package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.ghosh.sanjay.algos.Utils.isLessThan;
import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.slf4j.Logger;

/**
 * 
 * Concurrently Finds the prime
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class ConcurrentPrimeNumberService implements PrimeService {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	private static final BigInteger TWO = ONE.add(ONE);
	private static final BigInteger THREE = TWO.add(ONE);
	private static final BigInteger FIVE = TWO.add(THREE);
	private static final BigInteger SEVEN = TWO.add(FIVE);
	private static final BigInteger ELEVEN = SEVEN.add(THREE).add(ONE);

	private BigInteger upperBound;
	private ExecutorService service;

	private Map<BigInteger, BigInteger> primes;

	{
		primes = new ConcurrentHashMap<BigInteger, BigInteger>();
		for (BigInteger bigInteger : asList(TWO, THREE, FIVE, SEVEN, ELEVEN))
			primes.put(bigInteger, bigInteger);
	}

	/**
	 * 
	 * 
	 * 
	 * @param upperBound
	 */
	public ConcurrentPrimeNumberService(BigInteger upperBound) {
		this.upperBound = upperBound;
		this.service = Executors.newCachedThreadPool();
	}

	/**
	 * 
	 * 
	 * 
	 */
	public Set<BigInteger> primes() {
		if (!(isLessThan(upperBound, ELEVEN.add(ONE)) || ELEVEN.add(ONE).equals(upperBound))) {
			// 1. Divide the "distance" between current last prime and the upper bound into
			// equal buckets
			// 2. Create a runnable which can take care of each bucket
			// 3. Continuously update the Concurrent Hash Map with the primes
			BigInteger current = ELEVEN.add(ONE);
			while (isLessThan(current, upperBound)) {
				service.execute(runnable(primes, current, (current.add(ELEVEN).subtract(ONE))));
				current = current.add(ELEVEN);
			}
			shutdownWithGrace();
		}
		return primes.keySet();
	}

	/**
	 * 
	 * Boiler plate code to shutdown with grace Ideally this should be in some
	 * abstract class
	 * 
	 */
	private void shutdownWithGrace() {
		try {
			service.shutdown();
			while (!service.awaitTermination(1000, MILLISECONDS))
				service.shutdownNow();
		} catch (InterruptedException e) {
			// consume(exceptionConsumer(), e);
		}
	}

	/**
	 * 
	 * Runnable which checks the primes within the concurrent hash map
	 * 
	 * @param prime
	 * @param start
	 * @param finish
	 * @return
	 */
	private Runnable runnable(final Map<BigInteger, BigInteger> prime, BigInteger start, BigInteger finish) {
		return new Runnable() {
			@Override
			public void run() {
				for (BigInteger loop = start; isLessThan(loop, finish); loop = loop.add(ONE)) {
					boolean isPrime = true;
					for (BigInteger currentPrime : primes.keySet()) {
						if (loop.mod(currentPrime).equals(ZERO))
							isPrime = false;
					}
					if (isPrime)
						primes.put(loop, loop);
				}

			}
		};
	}

	/**
	 * 
	 * Consume the Exception using the custom consumer
	 * 
	 * @param consumer
	 * @param e
	 */
	private void consume(Consumer<Exception> consumer, Exception e) {
		consumer.accept(e);
	}
}