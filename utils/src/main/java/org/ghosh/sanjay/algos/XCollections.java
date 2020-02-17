package org.ghosh.sanjay.algos;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * 
 * Standard Utils that Java should have provided but anyways
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class XCollections {	
	
	
	/**
	 * 
	 * Returns a Map of type K and Value v
	 * 
	 * @return
	 */
	public static <K, V> Supplier<Map<K, V>> hashMap() {
		return HashMap::new;
	}
	
	
	
	/**
	 * 
	 * Returns a Supplier of a new linked hash set  
	 * 
	 * @return
	 */
	public static <T> Supplier<Set<T>> linkedHashSet() {
		return LinkedHashSet::new;
	}
	
	/**
	 * 
	 * Returns a Supplier of a new hash set
	 * 
	 * 
	 * @return
	 */
	public static <T> Supplier<Set<T>> hashSet() {
		return HashSet::new;
	}
	
	/**
	 * 
	 * Returns a Supplier of a new array list
	 * 
	 * @return Supplier<List<T>>
	 */
	public static <T> Supplier<List<T>> arrayList() {
		return ArrayList::new;
	}

	/**
	 * 
	 * Returns a Supplier of a new linked list
	 * 
	 * @return Supplier<List<T>>
	 */
	public static <T> Supplier<List<T>> linkedList() {
		return LinkedList::new;
	}
	
	/**
	 * 
	 * Returns a Supplier of a new Random
	 * 
	 * @return
	 */
	public static Supplier<Random> random() {
		return java.util.Random::new;
	}

	/**
	 * 
	 * Returns a Supplier of a new Random
	 * 
	 * @return
	 */
	public static IntSupplier random(final Integer upperBound) {
		return new IntSupplier() {
			
			@Override
			public int getAsInt() {
				return new java.util.Random().nextInt(upperBound);
			}
		};
	}
	
	/**
	 * 
	 * 
	 * @param start
	 * @param end
	 * @param step
	 * @return List<Integer>
	 */
	public static List<Integer> range(Integer start, Integer end, Integer step) {
		List<Integer> integers = new ArrayList<Integer>();
		for(int loop=start;loop<end;loop = loop + step) {
			integers.add(Integer.valueOf(loop));
		}
		return integers;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param mapSupplier
	 * @param function
	 * @param keyList
	 * @return
	 */
	public static <K, V> Map<K, V> initialize(Supplier<Map<K, V>> mapSupplier, Function<K, V> function, List<K> keyList) {
		Map<K, V> map = mapSupplier.get();
		for(K k : keyList) map.put(k, function.apply(k));
		return map;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param listSupplier
	 * @param initializer
	 * @param size
	 * @return List<Integer>
	 */
	public static List<Integer> initializeList(Supplier<? extends List<Integer>> listSupplier, IntSupplier initializer, int size) {
		List<Integer> listOfIntegers = listSupplier.get();
		for(int loop=0;loop<size;loop++) listOfIntegers.add(initializer.getAsInt());
		return listOfIntegers;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param setSupplier
	 * @param initializer
	 * @param size
	 * @return IntSupplier
	 */
	public static Set<Integer> initializeSet(Supplier<? extends Set<Integer>> setSupplier, IntSupplier initializer, int size) {
		Set<Integer> setOfIntegers = setSupplier.get();
		for(int loop=0;loop<size;loop++) setOfIntegers.add(initializer.getAsInt());
		return setOfIntegers;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param upperBound
	 * @return IntSupplier
	 */
	public static IntSupplier randomIntegers(final Integer upperBound) {
		return new IntSupplier() {
			@Override
			public int getAsInt() {
				return random().get().nextInt(upperBound);
			}
		};
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param senitel
	 * @return IntSupplier
	 */
	public static IntSupplier senitel(final Integer senitel) {
		return new IntSupplier() {
			@Override
			public int getAsInt() {
				return senitel;
			}
		};
	}
	
	
	
	/**
	 * 
	 * 
	 * @return IntConsumer
	 */
	public static IntConsumer intConsumer() {
		return new IntConsumer() {
			
			@Override
			public void accept(int value) {

				
			}
		};
	}
	
	
	/**
	 * 
	 * 
	 * @return BiConsumer<K, V>
	 */
	public static <K, V> BiConsumer<K, V> mapPrintConsumer() {
		return new BiConsumer<K, V>() {
			@Override
			public void accept(K k, V v) {				
				System.out.println(" map [" + k + "] = " + v);
			}
		};
	}
	
	/**
	 * 
	 * Collect the string representation of the collection provided with the
	 * delimiter. If the delimiter is null comma would be used.
	 * 
	 * This method will break if the collection is null.
	 * 
	 * 
	 * @return String
	 */
	public static <T> String collectToString(Collection<T> things, String delimiter) {
		return things.stream().map(Object::toString).collect(
				joining(ofNullable(delimiter).isPresent() ? of(delimiter).get() : ","));
	}
	
	
}