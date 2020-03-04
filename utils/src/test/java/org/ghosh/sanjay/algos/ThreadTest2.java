package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.ghosh.sanjay.algos.XCollections.fill;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ThreadTest2 {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	private enum Color {
		RED, GREEN, BLUE
	}

	private static class Status {
		Color color;

		Status(Color color) {
			this.color = color;
		}

		synchronized void set(Color color) {
			this.color = color;
		}

		synchronized boolean isNot(Color color) {
			return this.color != color;
		}

	}

	private static class ColorPrinter {
		Color color;

		ColorPrinter(Color color) {
			this.color = color;
		}

		public Color nextColor() {
			if (Color.RED.equals(color))
				return Color.GREEN;
			else if (Color.GREEN.equals(color))
				return Color.BLUE;
			else if (Color.BLUE.equals(color))
				return Color.RED;
			return null;
		}

		public Color color() {
			return this.color;
		}
	}

	/**
	 * 
	 * Returns a list of Colors of the specified Size
	 * 
	 * @param color
	 * @param maxSize
	 * @return
	 */
	private static List<ColorPrinter> color(ColorPrinter colorPrinter, int maxSize) {
		return fill(new Supplier<ColorPrinter>() {
			@Override
			public ColorPrinter get() {
				return colorPrinter;
			}
		}, maxSize);
	}

	public Runnable runnable(ColorPrinter colorPrinter, Status status) {
		return new Runnable() {
			@Override
			public void run() {
				synchronized (status) {
					try {
						while (status.isNot(colorPrinter.color())) {
							status.wait();
						}
						LOGGER.debug(colorPrinter.color().toString());
						status.set(colorPrinter.nextColor());
						status.notifyAll();
					} catch (InterruptedException e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		};
	}

	@Test
	public void testThread2() {
		List<ColorPrinter> list = new ArrayList<ColorPrinter>();
		list.addAll(color(new ColorPrinter(Color.RED), 10));
		list.addAll(color(new ColorPrinter(Color.GREEN), 10));
		list.addAll(color(new ColorPrinter(Color.BLUE), 10));

		Status status = new Status(Color.RED);
		Thread threads[] = new Thread[30];
		int index = 0;
		for (ColorPrinter printer : list) {
			threads[index++] = new Thread(runnable(printer, status));
		}

		for (int i = 0; i < 10 * 3; i++) {
			threads[i].start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < 10 * 3; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}