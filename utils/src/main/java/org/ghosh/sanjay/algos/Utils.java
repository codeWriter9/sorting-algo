package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.stream.IntStream.range;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongPredicate;
import java.util.stream.IntStream;

import org.slf4j.Logger;

/**
 * 
 * Utility Methods which do not fit anywhere else
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class Utils {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * Checks if the first argument is less than the second
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T extends Comparable<T>> boolean isLessThan(T first, T second) {
		return first.compareTo(second) < 0;
	}

	/**
	 * 
	 * Checks if the first argument is greater than the second
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T extends Comparable<T>> boolean isGreaterThan(T first, T second) {
		return first.compareTo(second) > 0;
	}

	/**
	 * 
	 * Swaps the values at the specified index
	 * 
	 * @param array
	 * @param index
	 * @param anotherIndex
	 */
	public static <T> void swap(T[] array, int index, int anotherIndex) {
		if (array != null || array.length > max(index, anotherIndex)) {
			T sub = array[index];
			array[index] = array[anotherIndex];
			array[anotherIndex] = sub;
		}
	}

	/**
	 * 
	 * Returns the max of two int
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static int max(int first, int second) {
		return first >= second ? first : second;
	}

	/**
	 * 
	 * Checks whether the index is in range. This will be preceded by a null check.
	 * 
	 * @param array
	 * @param index
	 * @return
	 */
	public static <T> boolean inRange(T[] array, int index) {
		return array != null && index > -1 && index < array.length ? true : false;
	}

	/**
	 * 
	 * 
	 * 
	 * @param f
	 * @param maxSize
	 * @return
	 */
	public static Long sumOfCollectionOfNumbers(IntUnaryOperator f, Integer maxSize) {
		return IntStream.iterate(0, f).limit(maxSize).asLongStream().sum();
	}

	/**
	 * 
	 * 
	 * 
	 * @param maxSize
	 * @return
	 */
	public static Long sumOfSquares(Integer maxSize) {
		return sumOfCollectionOfNumbers(i -> i * i, maxSize);
	}

	/**
	 * 
	 * 
	 * 
	 * @param maxSize
	 * @return
	 */
	public static Long sumOfCubes(Integer maxSize) {
		return sumOfCollectionOfNumbers(i -> i * i * i, maxSize);
	}

	/**
	 * 
	 * 
	 * 
	 * @param start
	 * @param end
	 * @return Integer
	 */
	public static Integer sumOfNumbers(int start, int end) {
		return range(start, end).sum();
	}

	/**
	 * 
	 * 
	 * 
	 * @param end
	 * @return Integer
	 */
	public static Integer sumOfNumbers(int end) {
		return range(0, end).sum();
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static IntUnaryOperator squareOperator() {
		return i -> i * i;
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static IntUnaryOperator cubeOperator() {
		return i -> i * i * i;
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static IntPredicate evenCheckerInteger() {
		return i -> i % 2 == 0;
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static LongPredicate evenCheckerLong() {
		return i -> i % 2 == 0;
	}

	/**
	 * 
	 * 
	 * 
	 * @return Consumer<Exception>
	 */
	public static Consumer<Exception> exceptionConsumer() {
		return new Consumer<Exception>() {

			@Override
			public void accept(Exception t) {
				System.err.println(t);

			}
		};
	}

	/**
	 * 
	 * 
	 * @return Consumer<String>
	 */
	public static <K, V> BiConsumer<K, V> mapPrintConsumer() {
		return new BiConsumer<K, V>() {
			public void accept(K k, V v) {
				System.out.println(" map [" + k + "] = " + v);
			};
		};
	}

	/**
	 * 
	 * 
	 * @return Consumer<String>
	 */
	public static Consumer<String> printConsumer() {
		return System.out::println;
	}

	/**
	 * 
	 * 
	 * @return IntConsumer
	 */
	public static IntConsumer printIntConsumer() {
		return (value) -> LOGGER.info("" + value);			
	}

	/**
	 * 
	 * 
	 * @param value
	 * @return IntSupplier
	 */
	public static IntSupplier mirror(final Integer value) {
		return () -> value;
	}

	/**
	 * 
	 * 
	 * @param millis
	 */
	public static void sleepSafely(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		}
	}
}