package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

public class NoVisibility {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());
	
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while(!ready) {
				Thread.yield();
			LOGGER.debug(Integer.toString(number));
			}
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}

}
