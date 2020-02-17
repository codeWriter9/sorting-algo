package org.ghosh.sanjay.algos;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.function.Function.identity;
import static org.ghosh.sanjay.algos.Utils.mapPrintConsumer;
import static org.ghosh.sanjay.algos.XCollections.range;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Function;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ExecutionServiceTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ExecutionServiceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ExecutionServiceTest.class);
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void testRunnables() {
		ExecutorService service = newCachedThreadPool();
		for (Runnable command : runnables(10)) {
			service.execute(command);
		}
		try {
			service.shutdown();
			if (service.awaitTermination(1000, SECONDS)) {
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * (1) Creates a cached Pool Constructs a Concurrent Hash Map with capacity 16
	 * (2) Runs a for loop with 10 iterations each iteration submitting a runnable
	 * to executor service to execute. (3) Runs a for loop with 10 iterations each
	 * iteration submitting a mapping functions to executor service to submit if
	 * mapping not present.
	 * 
	 */
	public void testMapRunnables() {
		ExecutorService service = newCachedThreadPool();// will create a Thread only when needed and will re-use
														// existing
		Map<Integer, Integer> concurrentMap = new ConcurrentHashMap<>(16);
		for (Runnable command : runnables(10, concurrentMap)) {
			service.execute(command);
		}
		for (Integer i : range(1, 20, 2)) {
			service.execute(runnable(concurrentMap, i, identity()));
		}
		service.execute(runnable(mapPrintConsumer(), concurrentMap));
		try {
			service.shutdown();
			if (service.awaitTermination(1000, SECONDS)) {
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param size
	 * @return
	 */
	private Collection<Runnable> runnables(int size) {
		Collection<Runnable> runnables = new LinkedList<>();
		for (int loop = 0; loop < size; loop++)
			runnables.add(runnable(loop));
		return runnables;
	}

	/**
	 * 
	 * 
	 * 
	 * @param size
	 * @param map
	 * @return
	 */
	private Collection<Runnable> runnables(int size, Map<Integer, Integer> map) {
		Collection<Runnable> runnables = new LinkedList<>();
		for (int loop = 0; loop < size; loop++)
			runnables.add(runnable(map, loop));
		return runnables;
	}

	/**
	 * 
	 * 
	 * 
	 * @param runner
	 * @return
	 */
	private Runnable runnable(final Integer runner) {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("Running : " + runner);
			}
		};
	}

	/**
	 * 
	 * Returns a runnable which can put an key in a map
	 * 
	 * @param map
	 * @param integer
	 * @return
	 */
	private Runnable runnable(final Map<Integer, Integer> map, final Integer integer) {
		return new Runnable() {
			@Override
			public void run() {
				map.put(integer, integer);
			}
		};
	}

	/**
	 * 
	 * Returns a runnable which can use the provided Function to map a Key to a
	 * Value if the provided map does not have the key pair binding in place
	 * 
	 * @param map
	 * @param integer
	 * @return
	 */
	private Runnable runnable(final Map<Integer, Integer> map, final Integer integer,
			final Function<Integer, Integer> mapper) {
		return new Runnable() {
			@Override
			public void run() {
				map.computeIfAbsent(integer, mapper);
			}
		};
	}

	/**
	 * 
	 * Returns a runnable which can use the provided BiConsumer to read the provided
	 * map
	 * 
	 * @param mapConsumer
	 * @param map
	 * @return
	 */
	private Runnable runnable(final BiConsumer<Integer, Integer> mapConsumer, final Map<Integer, Integer> map) {
		return new Runnable() {
			@Override
			public void run() {
				map.forEach(mapConsumer);
			}
		};
	}
}