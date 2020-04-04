package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TreeNodeTest {

	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testTreeNodeException() {
		assertThrows(IllegalArgumentException.class, () -> new TreeNode<Integer>(null));
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testNodeConstructor1() {
		assertTrue(new TreeNode<Integer>(Data.integer(4), TreeNode.integer(3), TreeNode.integer(5)).value() == 4);
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testNodeConstructor2() {
		assertTrue(new TreeNode<Integer>(Data.integer(4)).value() == 4);
	}

}