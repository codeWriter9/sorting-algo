package org.ghosh.sanjay.algos;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.ghosh.sanjay.algos.Utils.mirror;
import static org.ghosh.sanjay.algos.Utils.printIntConsumer;
import static org.ghosh.sanjay.algos.Utils.sleepSafely;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class ConcurrentHashMapTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ConcurrentHashMapTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ConcurrentHashMapTest.class);
	}

	/**
	 * 
	 * 
	 */
	public void testMap() {
		Assert.assertEquals(true, true);
	}

	/**
	 * 
	 * 
	 * @param concurrentMap
	 * @param consumer
	 * @return
	 */
	private Runnable consumer(final ConcurrentMap<Integer, Integer> concurrentMap,
			final BiConsumer<Integer, Integer> consumer) {
		return new Runnable() {
			@Override
			public void run() {
				concurrentMap.forEach(consumer);
			}
		};
	}

	/**
	 * 
	 * 
	 * @param concurrentMap
	 * @param remappingFunction
	 * @param supplier
	 * @return
	 */
	private Runnable remover(final ConcurrentMap<Integer, Integer> concurrentMap,
			final BiFunction<Integer, Integer, Integer> remappingFunction, final IntSupplier supplier) {
		return new Runnable() {

			@Override
			public void run() {
				concurrentMap.compute(supplier.getAsInt(), remappingFunction);
			}
		};
	}

	/**
	 * 
	 * 
	 * @param concurrentMap
	 * @param supplier
	 * @return
	 */
	private Runnable supplier(final ConcurrentMap<Integer, Integer> concurrentMap, final IntSupplier supplier) {
		return new Runnable() {
			@Override
			public void run() {
				concurrentMap.putIfAbsent(supplier.getAsInt(), supplier.getAsInt());
			}
		};
	}

	/**
	 * 
	 * 
	 * 
	 * @param queue
	 * @param consumer
	 * @return
	 */
	private Runnable consumer(final BlockingQueue<Integer> queue, final IntConsumer consumer) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					consumer.accept(queue.take());
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
		};
	}

	/**
	 * 
	 * 
	 * @param queue
	 * @param supplier
	 * @return
	 */
	private Runnable supplier(final BlockingQueue<Integer> queue, final IntSupplier supplier) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					queue.put(supplier.getAsInt());
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
		};
	}

	/**
	 * 
	 * 
	 * 
	 * @param str
	 * @return
	 */
	private Map<String, Integer> mapToLength(String... str) {
		return Arrays.asList(str).stream().collect(Collectors.toMap(Function.identity(), String::length));
	}

	/**
	 * 
	 * 
	 * 
	 * @param lock
	 * @param frequency
	 * @param data
	 * @return
	 */
	private Runnable runnable(Lock lock, ConcurrentMap<Integer, Integer> frequency, Map<String, Integer> data) {
		return () -> {
			synchronized (lock) {
				try {
					lock.lock();
					for (String str : data.keySet())
						if (frequency.containsKey(data.get(str))) {
							frequency.computeIfPresent(data.get(str), (Integer k, Integer v) -> (v + 1));
						} else {
							frequency.computeIfAbsent(data.get(str), k -> 1);
						}
				} catch (Exception e) {
					System.err.println(e.getCause());
				} finally {
					lock.unlock();
				}
			}
		};
	}

	/**
	 * Checks the frequency of lengths of words
	 * 
	 */
	public void testConcurrentHashMap() {
		ExecutorService service = newCachedThreadPool();
		ConcurrentMap<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();
		Lock lock = new ReentrantLock();

		for (int loop = 0; loop < 10; loop++) {
			service.execute(runnable(lock, concurrentMap, mapToLength("Alpha", "Beta", "Gamma", "Delta", "Phi", "Si")));
		}
		sleepSafely(1000);
		concurrentMap.forEach((k, v) -> System.out.println(" Key [" + k + "] = Value [" + v + "]"));

		shutdownWithGrace(service);
	}

	/**
	 * 
	 * Tests out the Array Blocking Queue by creating runnables which takes
	 * suppliers and consumers Just to demonstrate consumers are created first and
	 * then the suppliers are created.
	 * 
	 */
	public void testArrayBlockingQueue() {
		// create an executor service to get a cached Thread Pool
		ExecutorService service = newCachedThreadPool();
		// get a block queue in our case this is just a an array blocking queue
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(16);
		// Create Consumers
		for (int loop = 0; loop < 10; loop++) {
			service.execute(consumer(blockingQueue, printIntConsumer()));
		}
		// Create Producers
		for (int loop = 0; loop < 10; loop++) {
			service.execute(supplier(blockingQueue, mirror(loop)));
		}
		shutdownWithGrace(service);
	}

	/**
	 * 
	 * (1) Create 10 Supplier (2) Create 2 Consumer (3) Create 10 Removers (4)
	 * Create 10 Consumers
	 * 
	 */
	public void testConcurrentHashMapBasic() {
		ExecutorService service = newCachedThreadPool();
		ConcurrentMap<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();

		for (int loop = 0; loop < 10; loop++) {
			service.execute(supplier(concurrentMap, Utils.mirror(loop)));
		}

		for (int loop = 0; loop < 2; loop++)
			service.execute(consumer(concurrentMap, (t, u) -> System.out.println(t + ":" + u)));

		for (int loop = 0; loop < 10; loop++) {
			service.execute(remover(concurrentMap, (t, u) -> t % 2 == 0 ? null : u, Utils.mirror(loop)));
		}

		for (int loop = 0; loop < 2; loop++)
			service.execute(consumer(concurrentMap, (t, u) -> System.out.println(t + ":" + u)));

		shutdownWithGrace(service);
	}

	/**
	 * 
	 * Boiler plate code to shutdown with grace Ideally this should be in some
	 * abstract class
	 * 
	 */
	private void shutdownWithGrace(final ExecutorService service) {
		try {
			service.shutdown();// try and shutdown the service
			while (!service.awaitTermination(1000, MILLISECONDS)) // while the service has not been shutdown,
																	// or timeout has not occurred or
																	// Interrupted Exception has not been thrown
				service.shutdownNow();// force the service to shutdown now
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}