package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static org.ghosh.sanjay.algos.Utils.exceptionConsumer;
import static org.ghosh.sanjay.algos.XCollections.intConsumer;
import static org.ghosh.sanjay.algos.XCollections.random;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
public class NewThreadsTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 * 
	 * @param queue
	 * @param intConsumer
	 * @return
	 */
	public Runnable runnable(BlockingQueue<Integer> queue, IntConsumer intConsumer) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					intConsumer.accept(queue.take());
				} catch (InterruptedException e) {
					consume(exceptionConsumer(), e);
				}
			}
		};
	}

	/**
	 * 
	 * 
	 * 
	 * @param queue
	 * @param intSupplier
	 * @return
	 */
	public Runnable runnable(BlockingQueue<Integer> queue, IntSupplier intSupplier) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					queue.put(intSupplier.getAsInt());
				} catch (InterruptedException e) {
					consume(exceptionConsumer(), e);
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
	private void shutdownWithGrace(final ExecutorService service) {
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

	/**
	 * 
	 * Tests out the Array Blocking Queue by creating runnables which takes
	 * suppliers and consumers Just to demonstrate consumers are created first and
	 * then the suppliers are created.
	 * 
	 */
	@Test
	public void testNewThreads() {
		// create an executor service to get a cached Thread Pool
		ExecutorService service = newCachedThreadPool();
		// get a block queue in our case this is just a an array blocking queue
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(16);
		// Create Consumers
		for (int loop = 0; loop < 10; loop++) {
			new Thread(runnable(blockingQueue, intConsumer())).start();
		}
		// Create Producers
		for (int loop = 0; loop < 10; loop++) {
			new Thread(runnable(blockingQueue, random(100))).start();
		}
		shutdownWithGrace(service);
	}
}