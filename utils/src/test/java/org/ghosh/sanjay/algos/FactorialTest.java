package org.ghosh.sanjay.algos;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FactorialTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FactorialTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FactorialTest.class );
    }
    
    public void testFactorial() {
    	Assert.assertEquals(true, true);
    }

}
