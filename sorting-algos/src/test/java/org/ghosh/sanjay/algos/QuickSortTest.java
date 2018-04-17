package org.ghosh.sanjay.algos;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.ghosh.sanjay.algos.Utils.isNonDecreasing;
import static org.ghosh.sanjay.algos.Utils.isLessThan;



/**
 * Unit test for simple App.
 */
public class QuickSortTest 
    extends TestCase {
		
		/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public QuickSortTest( String testName )
    {
        super( testName );
    }
	
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( QuickSortTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testQuickSortForIntegers()
    {		    
        assertTrue( Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}).equals(
		new QuickSort<Integer>(new Integer[] {2, 3, 4, 1, 9, 8, 7, 6, 5}).sort()
		.sorted()));
    }
	
	public void testQuickSortForString()
	{		
		assertTrue( Arrays.asList(new String[]{  "Eight", "Five", "Four", "Nine" ,"One", "Seven", "Six", "Three", "Two"}).equals(
		new QuickSort<String>(new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"}).sort()
		.sorted()));		
	}
	
	
	public void testQuickSortIntegerBulk()
	{
		List<Integer> integers = new ArrayList<Integer>();
		for(int count=2001;count<14091;count++) integers.add(new Integer(count));
		for(int count=101;count<1001;count++) integers.add(new Integer(count));		
		assertTrue(isNonDecreasing(new QuickSort<Integer>(integers.toArray(new Integer[0])).sort().sorted()));		
	}
	
}