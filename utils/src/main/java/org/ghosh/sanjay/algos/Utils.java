package org.ghosh.sanjay.algos;

import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class Utils {

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
	 * Checks whether or not a particular sequence of numbers is not decreasing
	 * 
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<T>> boolean isNonDecreasing(List<T> list) {
		if (list != null || list.isEmpty() == false) {
			for (int index = 0; index < list.size() - 1; index++) {
				if (isLessThan(list.get(index + 1), list.get(index)))
					return false;
			}
			return true;
		} else
			return true;
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
	 * Filtered summation of the Long Collection
	 * 
	 * @param numbers
	 * @return
	 */
	public static Long summationLong(Collection<Long> numbers, Predicate<Long> filter) {
		return numbers.stream().filter(filter).mapToLong(Long::longValue).sum();
	}

	/**
	 * 
	 * Filtered summation of the Integer Collection
	 * 
	 * @param numbers
	 * @return
	 */
	public static Integer summationInt(Collection<Integer> numbers, Predicate<Integer> filter) {
		return numbers.stream().filter(filter).mapToInt(Integer::intValue).sum();
	}

	/**
	 * 
	 * 
	 * 
	 * @param numbers
	 * @return
	 */
	public static Long sumOfLongs(Collection<Long> numbers) {
		return numbers.stream().reduce(0L, (partialSum, current) -> partialSum + current);
	}

	/**
	 * 
	 * 
	 * 
	 * @param numbers
	 * @return
	 */
	public static Integer sumOfIntegers(Collection<Integer> numbers) {
		return numbers.stream().reduce(0, (partialSum, current) -> partialSum + current);
	}

	/**
	 * 
	 * 
	 * 
	 * @param numbers
	 * @return
	 */
	public static Integer sumOfIntegersDefault(Collection<Integer> numbers) {
		return numbers.stream().collect(Collectors.summingInt(Integer::intValue));
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @param numbers
	 * @return
	 */
	public static Long sumOfLongsDefault(Collection<Long> numbers) {
		return numbers.stream().collect(Collectors.summingLong(Long::longValue));
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
	public static <K, V> Consumer<Map.Entry<K, V>> mapEntryPrintConsumer() {
		return System.out::println;
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
		return new IntConsumer() {

			@Override
			public void accept(int value) {
				System.out.println(value);
			}
		};
	}

	/**
	 * 
	 * 
	 * @param value
	 * @return IntSupplier
	 */
	public static IntSupplier mirror(final Integer value) {
		return new IntSupplier() {

			@Override
			public int getAsInt() {

				return value;
			}
		};
	}

	/**
	 * 
	 * 
	 * @param supplier
	 * @param maxSize
	 * @return List<T>
	 */
	public static <T> List<T> fill(Supplier<T> supplier, Integer maxSize) {
		List<T> filler = new ArrayList<>();
		for (int loop = 0; loop < maxSize; loop++) {
			filler.add(supplier.get());
		}
		return filler;
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
			System.err.println(e.getMessage());
		}
	}
}