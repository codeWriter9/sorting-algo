package org.ghosh.sanjay.algos;

import static org.ghosh.sanjay.algos.XCollections.fill;
import static org.ghosh.sanjay.algos.XCollections.mapEntryPrintConsumer;
import static org.ghosh.sanjay.algos.XCollections.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ThreadTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ThreadTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ThreadTest.class);
	}

	/**
	 * 
	 * Returns a list of Colors of the specified Size
	 * 
	 * @param color
	 * @param maxSize
	 * @return
	 */
	private static List<Color> color(Color color, int maxSize) {
		return fill(new Supplier<Color>() {
			@Override
			public Color get() {
				return color;
			}
		}, maxSize);
	}

	private enum Status {
		ONE, TWO, THREE
	}

	private enum Color {
		RED, GREEN, BLUE
	};

	private static class ColorPrinter {
		private boolean done;
		private Color color;

		public ColorPrinter(Color color) {
			this.color = color;
			this.done = false;
		}

		public synchronized boolean isDone() {
			return done;
		}

		public synchronized String color() {
			done = true;
			return color.name().substring(0, 1);
		}

		@Override
		public String toString() {
			return color.name();
		}
	}

	private static class AdvancedColorPrinter {
		private boolean done;
		private Color color;
		private Status status;

		public AdvancedColorPrinter(Color color, Status status) {
			this.color = color;
			this.status = status;
			this.done = false;
		}

		public synchronized boolean isDone() {
			return done;
		}

		public synchronized boolean isAfter(AdvancedColorPrinter previous) {
			return (previous.status == Status.ONE && status == Status.TWO)
					|| (previous.status == Status.TWO && status == Status.THREE)
					|| (previous.status == Status.THREE && status == Status.ONE);
		}

		public synchronized String color() {
			done = true;
			return color.name().substring(0, 1);
		}

		public synchronized Status status() {
			return status;
		}

		public synchronized Status setStatusToOne() {
			status = Status.ONE;
			return status;
		}

		public synchronized Status setStatusToTWO() {
			status = Status.TWO;
			return status;
		}

		public synchronized Status setStatusToThree() {
			status = Status.THREE;
			return status;
		}

		@Override
		public String toString() {
			return color.name();
		}
	}

	private static class MyNumber {
		private Integer number;
		private boolean hasCompleted;

		public MyNumber(Integer number) {
			this.number = number;
			hasCompleted = false;
		}

		public synchronized boolean hasCompleted() {
			return hasCompleted;
		}

		public synchronized boolean isAfter(MyNumber previous) {
			return this.number == previous.number + 1;
		}

		public synchronized Integer number() {
			hasCompleted = true;
			return number;
		}

		@Override
		public String toString() {
			return number + "";
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @param printer
	 * @param lock
	 * @return
	 */
	public Runnable runnable(final AdvancedColorPrinter current, final Object lock,
			final AdvancedColorPrinter previous) {
		return new Runnable() {
			@Override
			public void run() {
				if (lock != null) {
					synchronized (lock) {
						if (previous != null) {
							while (!previous.isDone() && !current.isAfter(previous)) {
								try {
									lock.wait();
								} catch (InterruptedException e) {
								}
							}
						}
						System.out.print(current.color());
						lock.notifyAll();
					}
				}
			}
		};
	}

	/**
	 * 
	 * 
	 * 
	 * @param printer
	 * @param lock
	 * @return
	 */
	public Runnable runnable(final ColorPrinter current, final Object lock, final ColorPrinter previous) {
		return new Runnable() {
			@Override
			public void run() {
				if (lock != null) {
					synchronized (lock) {
						if (previous != null) {
							while (!previous.isDone()) {
								try {
									lock.wait();
								} catch (InterruptedException e) {
								}
							}
						}
						System.out.print(current.color());
						lock.notifyAll();
					}
				}
			}
		};
	}

	/**
	 * 
	 * 
	 * 
	 * @param number
	 * @param lock
	 * @return
	 */
	public Runnable runnable(final MyNumber number, Object lock, final MyNumber previous) {
		return new Runnable() {

			@Override
			public void run() {
				if (lock != null) {
					synchronized (lock) {
						if (previous != null) {
							while (!previous.hasCompleted() && !number.isAfter(previous)) {
								try {
									lock.wait();
								} catch (InterruptedException e) {
								}
							}
						}
						System.out.println(number.number());
						lock.notifyAll();
					}
				}
			}

		};
	}

	/**
	 * 
	 * Returns a runnable which print the provided Integer
	 * 
	 * @param x
	 * @return
	 */
	public Runnable runnable(final Integer x) {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println(" Running " + x);
			}
		};
	}

	/**
	 * 
	 * Returns a runnable which takes a lock and an Integer loop
	 * 
	 * 
	 * @param lock
	 * @param loop
	 * @return
	 */
	public Runnable runnable(final Object lock, final Integer loop) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(loop);
					Thread.sleep(100);
				} catch (Exception e) {
					System.err.println(e);// Standard utility method to consume error

				}
			}
		};
	}

	/**
	 * 
	 * Returns a runnable which takes lock 1 and lock 2 and an Integer. The runnable
	 * will wait for a notification on the lock 1. Once notified it will print the
	 * Integer and then will notify in the lock 2.
	 * 
	 * @param lock1
	 * @param lock2
	 * @param loop
	 * @return
	 */
	public Runnable runnable(final Object lock1, final Object lock2, final Integer loop) {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println(" Runnable " + loop);
				try {
					System.out.println(" [waiting for lock] Runnable " + loop + ":" + lock1);
					synchronized (lock1) {
						System.out.println(" [synchronized][waiting for lock] Runnable " + loop + ":" + lock1);
						lock1.wait();
					}

					System.out.println("[" + loop + "]");

					System.out.println(" [notifying] Runnable " + loop + ":" + lock2);
					synchronized (lock2) {
						System.out.println(" [synchronized][notifying] Runnable " + loop + ":" + lock2);
						lock2.notify();
					}
				} catch (Exception e) {
					System.err.println(e);// Standard utility method to consume error

				}
			}
		};
	}

	/**
	 * 
	 * Returns a runnable which takes a lock, a map and an Integer Supplier. This
	 * puts the Integer supplied in the Map both as a key and then as a value
	 * 
	 * @param lock
	 * @param map
	 * @param supplier
	 * @return
	 */
	public Runnable runnable(final Object lock, final Map<Integer, Integer> map, final IntSupplier supplier) {
		return new Runnable() {
			@Override
			public void run() {
				Integer supplied = supplier.getAsInt();
				map.put(supplied, supplied);
			}
		};
	}

	/**
	 * 
	 * Test unordered Wait and Notify
	 * 
	 */
	public void testUnOrderedWaitNotify() {
		Thread[] thread = new Thread[10];
		Object lock = new Object();

		for (int i = 1; i < 11; i++) {
			final int loop = i;
			thread[i - 1] = new Thread(runnable(lock, loop));
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}

		synchronized (lock) {
			lock.notify();
		}
	}

	/**
	 * 
	 * Test correct Wait and Notify
	 * 
	 */
	public void testCorrectWaitNotify() {
		Thread[] thread = new Thread[10];
		Object lock1 = new Object() {
			@Override
			public String toString() {
				return "lock1";
			}
		};
		Object lock2 = new Object() {
			@Override
			public String toString() {
				return "lock2";
			}
		};

		for (int i = 1; i < 11; i++) {
			final int loop = i;
			if (i % 2 == 1)
				thread[i - 1] = new Thread(runnable(lock1, lock2, loop));// so odd threads wait on lock 1 and notify
																			// lock 2
			else if (i % 2 == 0)
				thread[i - 1] = new Thread(runnable(lock2, lock1, loop));// and even threads wait on lock 2 and notify
																			// lock 1
		}

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);// So that all the threads to line up before the locks to get a monitor
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread[i].start();
		}

		// some one has to notify the lock1 for the first time
		synchronized (lock1) {
			lock1.notify();
		}

		for (int i = 0; i < 10; i++) {
			try {
				thread[i].join(1000, 100);// so that the main thread waits for all of the other threads to finish
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * Test Threads with a Runnable which also prints the loop
	 * 
	 * 
	 */
	public void testThreads() {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			final int loop = i;
			threads[i] = new Thread(runnable(loop));
		}

		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
	}

	/**
	 * 
	 * Constructs runnables which take a lock, a map and a random Integer supplier.
	 * It makes the main thread sleep while the forked out threads join the main
	 * thread.
	 * 
	 */
	public void testThreadsX() {
		Integer bound = 10;
		Thread[] threads = new Thread[bound];
		Object lock = new Object();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < bound; i++)
			threads[i] = new Thread(runnable(lock, map, random(bound)));

		for (int i = 0; i < bound; i++) {
			threads[i].start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < bound; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		map.entrySet().forEach(mapEntryPrintConsumer());
	}

	public void testThread() {
		Integer bound = 10;
		Thread[] threads = new Thread[bound];
		Object lock = new Object();

		ThreadTest.MyNumber number = null;
		ThreadTest.MyNumber previous = null;
		for (int i = 0; i < bound; i++) {
			number = new ThreadTest.MyNumber(i);
			if (i == 0)
				threads[i] = new Thread(runnable(number, lock, (MyNumber) null));
			else
				threads[i] = new Thread(runnable(number, lock, previous));
			previous = number;
		}

		for (int i = 0; i < bound; i++) {
			threads[i].start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < bound; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void testColoredThreads() {
		Integer bound = 10;
		Thread[] threads = new Thread[bound * 3];
		Object lock = new Object();

		ThreadTest.ColorPrinter current = null;
		ThreadTest.ColorPrinter previous = null;

		List<Color> colors = new ArrayList<Color>();
		colors.addAll(color(Color.GREEN, 10));
		colors.addAll(color(Color.BLUE, 10));
		colors.addAll(color(Color.RED, 10));

		for (int i = 0; i < bound * 3; i++) {
			current = new ThreadTest.ColorPrinter(colors.get(i));
			if (i == 0)
				threads[i] = new Thread(runnable(current, lock, (ColorPrinter) null));
			else
				threads[i] = new Thread(runnable(current, lock, previous));
			previous = current;
		}

		for (int i = 0; i < bound * 3; i++) {
			threads[i].start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < bound * 3; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void testAdvancedColoredThreads() {
		Integer bound = 10;
		Thread[] threads = new Thread[bound * 3];
		Object lock = new Object();

		ThreadTest.AdvancedColorPrinter current = null;
		ThreadTest.AdvancedColorPrinter previous = null;

		List<Color> colors = new ArrayList<Color>();
		colors.addAll(color(Color.RED, 10));
		colors.addAll(color(Color.GREEN, 10));
		colors.addAll(color(Color.BLUE, 10));

		for (int i = 0; i < bound * 3; i++) {
			current = new ThreadTest.AdvancedColorPrinter(colors.get(i),
					i % 3 == 0 ? Status.ONE : i % 3 == 1 ? Status.TWO : Status.THREE);
			if (i == 0)
				threads[i] = new Thread(runnable(current, lock, (AdvancedColorPrinter) null));
			else
				threads[i] = new Thread(runnable(current, lock, previous));
			previous = current;
		}

		for (int i = 0; i < bound * 3; i++) {
			threads[i].start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < bound * 3; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}