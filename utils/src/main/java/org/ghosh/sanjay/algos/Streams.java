package org.ghosh.sanjay.algos;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Streams {
	
	/**
	 * 
	 * 
	 * @param integers
	 * @return
	 */
	public static IntStream integers(Integer ...integers) {
		return Arrays.asList(integers).stream().mapToInt(Integer::intValue);
	} 
	
	/**
	 * 
	 * 
	 * 
	 * @param doubles
	 * @return
	 */
	public static DoubleStream doubles(Double ...doubles) {
		return Arrays.asList(doubles).stream().mapToDouble(Double::doubleValue);
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param longs
	 * @return
	 */
	public static LongStream longs(Long ...longs) {
		return Arrays.asList(longs).stream().mapToLong(Long::longValue);
	}

}