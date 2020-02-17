package org.ghosh.sanjay.algos;

import static org.ghosh.sanjay.algos.Streams.integers;
import static org.ghosh.sanjay.algos.Utils.evenCheckerInteger;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StreamTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StreamTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StreamTest.class );
    }
    
    /**
     * 
     * 
     */
    public void testNaturalIntStream() {
    	assertEquals(false, integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).allMatch(evenCheckerInteger()));    	
    }
    
    /**
     * 
     */
    public void testEvenIntStream() {
    	assertEquals(true, integers(2, 4, 6, 8, 10).allMatch(evenCheckerInteger()));    	
    }
 
    /**
     * 
     * 
     * 
     */
    public void testOddIntStream() {
    	assertEquals(false, integers(1, 3, 5, 7, 9, 11, 13).allMatch(evenCheckerInteger()));
    }
}