package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.ghosh.sanjay.algos.XCollections.arrayList;
import static org.ghosh.sanjay.algos.XCollections.hashSet;
import static org.ghosh.sanjay.algos.XCollections.initializeList;
import static org.ghosh.sanjay.algos.XCollections.initializeSet;
import static org.ghosh.sanjay.algos.XCollections.linkedHashSet;
import static org.ghosh.sanjay.algos.XCollections.linkedList;
import static org.ghosh.sanjay.algos.XCollections.randomIntegers;
import static org.ghosh.sanjay.algos.XCollections.senitel;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class XCollectionsTest {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * Test the creation of a new Array List through lambda
	 * 
	 */
	@Test
	public void testXCollectionsNewList() {
		assertEquals(true, arrayList().get() != null && arrayList().get().isEmpty());
	}

	/**
	 * 
	 * 
	 * Test the creation of a new Linked List through lambda
	 * 
	 */
	@Test
	public void testXCollectionsNewList2() {
		assertEquals(true, linkedList().get() != null && linkedList().get().isEmpty());
	}

	/**
	 * 
	 * Create a new array list of a defined size and initialize it to a random value
	 * 
	 */
	@Test
	public void testIntegerList() {
		for (Integer integer : initializeList(arrayList(), randomIntegers(1), 10)) {
			assertEquals(true, integer == 1 || integer == 0);
		}
	}

	/**
	 * 
	 * Create a new array list of a defined size and initialize it to a passed value
	 * 
	 * 
	 */
	@Test
	public void testIntegerList3() {
		for (Integer integer : initializeList(arrayList(), senitel(0), 10)) {
			assertEquals(true, integer == 0);
		}
	}

	/**
	 * 
	 * Create a new linked list of a defined size and initialize it to a passed
	 * value
	 * 
	 */
	@Test
	public void testIntegerList4() {
		for (Integer integer : initializeList(linkedList(), senitel(0), 10)) {
			assertEquals(true, integer == 0);
		}
	}

	/**
	 * 
	 * Test the creation of a new Hash Set through lambda
	 * 
	 */
	@Test
	public void testXCollectionsNewSet() {
		assertEquals(true, hashSet().get() != null && hashSet().get().isEmpty());
	}

	/**
	 * 
	 * 
	 * Test the creation of a new Linked Hash Set through lambda
	 * 
	 */
	@Test
	public void testXCollectionsNewSet2() {
		assertEquals(true, linkedHashSet().get() != null && linkedHashSet().get().isEmpty());
	}

	/**
	 * 
	 * Create a new array list of a defined size and initialize it to a random value
	 * 
	 */
	@Test
	public void testIntegerSet() {
		for (Integer integer : initializeSet(hashSet(), randomIntegers(1), 10)) {
			assertEquals(true, integer == 1 || integer == 0);
		}
	}

	/**
	 * 
	 * Create a new array list of a defined size and initialize it to a passed value
	 * 
	 * 
	 */
	@Test
	public void testIntegerSet3() {
		for (Integer integer : initializeSet(hashSet(), senitel(0), 10)) {
			assertEquals(true, integer == 0);
		}
	}

	/**
	 * 
	 * Create a new linked list of a defined size and initialize it to a passed
	 * value
	 * 
	 */
	@Test
	public void testIntegerSet4() {
		for (Integer integer : initializeSet(linkedHashSet(), senitel(0), 10)) {
			assertEquals(true, integer == 0);
		}
	}
}