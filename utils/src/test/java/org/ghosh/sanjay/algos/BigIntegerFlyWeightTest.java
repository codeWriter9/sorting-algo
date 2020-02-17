package org.ghosh.sanjay.algos;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.math.BigInteger;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BigIntegerFlyWeightTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BigIntegerFlyWeightTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BigIntegerFlyWeightTest.class );
    }
    
    /**
     * 
     * 
     */
    public void testBigInteger1() {
    	Instant start = now();
    	for(Integer loop=0;loop<10000;loop++) {
    		new BigInteger(loop + "");
    	}
    	Instant end = now();
    	System.out.println(start.until(end, MILLIS) + " ms ");
    }
    
    /**
     * 
     * 
     * 
     */
    public void testBigInteger2() {
    	Instant start = now();
    	for(Integer loop=0;loop<10000;loop++) {
    		BigInteger.valueOf(loop);
    	}
    	Instant end = now();
    	System.out.println(start.until(end, MILLIS) + " ms ");
    }
}