package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.Utils.exceptionConsumer;
import static org.ghosh.sanjay.algos.Utils.isLessThan;
import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.slf4j.Logger;

public class BlockingPrimeNumberService implements PrimeService {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	private static final BigInteger TWO = ONE.add(ONE);
	private static final BigInteger THREE = TWO.add(ONE);
	private static final BigInteger FIVE = TWO.add(THREE);
	private static final BigInteger SEVEN = TWO.add(FIVE);
	private static final BigInteger ELEVEN = SEVEN.add(THREE).add(ONE);

	private BigInteger upperBound;
	private ExecutorService service;

	private BlockingQueue<BigInteger> primes;

	{
		primes = new LinkedBlockingQueue<>();
		primes.addAll(asList(TWO, THREE, FIVE, SEVEN, ELEVEN));
	}

	/**
	 * 
	 * 
	 * 
	 * @param upperBound
	 */
	public BlockingPrimeNumberService(BigInteger upperBound) {
		this.upperBound = upperBound;
		this.service = Executors.newCachedThreadPool();
	}

	/**
	 * 1
	 * 
	 * 
	 * 
	 */
	public Set<BigInteger> primes() {
		if (!(isLessThan(upperBound, ELEVEN.add(ONE)) || ELEVEN.add(ONE).equals(upperBound))) {
			// Create runnables that can run parallely
			// Check if they are prime then add to the queue else pass over
			BigInteger current = ELEVEN.add(ONE);
			while (isLessThan(current, upperBound)) {
				service.execute(runnable(primes, current, (current.add(ELEVEN).subtract(ONE))));
				current = current.add(ELEVEN);
			}
			shutdownWithGrace();
		}
		return new HashSet<BigInteger>(primes);
	}

	/**
	 * 
	 * Runnable which checks the primes within the concurrent hash map
	 * 
	 * @param primes
	 * @param start
	 * @param finish
	 * @return
	 */
	private Runnable runnable(final BlockingQueue<BigInteger> primes, BigInteger start, BigInteger finish) {
		return new Runnable() {
			@Override
			public void run() {
				for (BigInteger loop = start; isLessThan(loop, finish); loop = loop.add(ONE)) {
					boolean isPrime = true;
					for (BigInteger currentPrime : primes) {
						if (loop.mod(currentPrime).equals(ZERO))
							isPrime = false;
					}
					if (isPrime)
						try {
							primes.put(loop);
						} catch (InterruptedException e) {
							consume(exceptionConsumer(), e);
						}
				}

			}
		};
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
			while (!service.awaitTermination(1000, TimeUnit.MILLISECONDS))
				service.shutdownNow();
		} catch (InterruptedException e) {
			consume(exceptionConsumer(), e);
		}
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