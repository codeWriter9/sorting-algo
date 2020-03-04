package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static org.ghosh.sanjay.algos.Utils.exceptionConsumer;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VolatilityTest  {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	private volatile Integer VLOOP = 5;	

	
	public Runnable changeListener() {
		return () -> {
			
			for (int loop = 0; loop < 100; loop++) {
				LOGGER.debug(" [" + Thread.currentThread().getName() + "] loop " + loop);
				if (loop > VLOOP)
					break;
			}
		};
	}

	public Runnable changeMaker() {
		return () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			for (int loop = 0; loop < 5; loop++) {
				LOGGER.debug(" [" + Thread.currentThread().getName() + "] " +" incrementing VLOOP ");
				VLOOP = 10 + loop;
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
	 * 
	 * 
	 */
	@Test
	public void testVolatile() {
		// create an executor service to get a cached Thread Pool
		ExecutorService service = newCachedThreadPool();

		// Create Change Listener
		for (int loop = 0; loop < 10; loop++) {
			new Thread(changeListener()).start();
		}
		// Create Change Maker
		for (int loop = 0; loop < 2; loop++) {
			new Thread(changeMaker()).start();
		}
		shutdownWithGrace(service);
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testNonVolatile() {
		assertEquals(true, true);
	}
}