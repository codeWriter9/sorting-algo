package org.ghosh.sanjay.algos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
	public static <T extends Comparable<T>> boolean isLessThan(T first ,T second) 	{
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
	public static <T extends Comparable<T>> boolean isGreaterThan(T first ,T second) 	{
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
		if(list != null || list.isEmpty() == false) {
			for(int index=0;index<list.size() - 1;index++) {
				if(isLessThan(list.get(index + 1), list.get(index))) return false;
			}	
			return true;
		}
		else return true;
	}
	
	/**
	 * 
	 * Swaps the values at the specified index
	 * 
	 * @param array
	 * @param index
	 * @param anotherIndex
	 */
	public static <T> void swap(T [] array, int index, int anotherIndex) {
		if(array != null || array.length > max(index, anotherIndex) ) {
			T sub = array[ index ];
			array[ index ] = array[ anotherIndex ];
			array[ anotherIndex ] = sub;
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
	public static <T> boolean inRange(T [] array, int index) {
		return array != null && index > -1 && index < array.length ? true : false;
	}
	
	/**
	 * 
	 * Collect the string representation of the collection provided with the delimiter.
	 * If the delimiter is null comma would be used.
	 * 
	 * This method will break if the collection is null.
	 * 
	 * 
	 * @return String
	 */
	public static <T> String collectToString(Collection<T> things, String delimiter) {
		return things.stream().map(Object::toString).collect(Collectors.joining(Optional.ofNullable(delimiter).isPresent() ? Optional.of(delimiter).get() : ","));
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
}