package org.ghosh.sanjay.algos;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



/**
 * Unit test for simple App.
 */
public class InsertionSortTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public InsertionSortTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( InsertionSortTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testInsertionSortForIntegers()
    {		
        assertTrue( Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}).equals(
		new InsertionSort<Integer>(new Integer[] {2, 3, 4, 1, 9, 8, 7, 6, 5}).sort()
		.sorted()));
    }
	
	public void testInsertionSortForString()
	{		
		assertTrue( Arrays.asList(new String[]{  "Eight", "Five", "Four", "Nine" ,"One", "Seven", "Six", "Three", "Two"}).equals(
		new InsertionSort<String>(new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"}).sort()
		.sorted()));		
	}
	
	
	public void testInsertionSortIntegerBulk()
	{
		List<Integer> integers = new ArrayList<Integer>();
		for(int count=101;count<1001;count++) integers.add(new Integer(count));
		System.out.println(new InsertionSort<Integer>(integers.toArray(new Integer[0])).sort().sorted());		
	}
}