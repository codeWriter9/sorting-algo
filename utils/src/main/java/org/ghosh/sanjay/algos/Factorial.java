package org.ghosh.sanjay.algos;

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
	
	private static BigInteger [] factorials = new BigInteger[100000];
	private static BigInteger divider = BigInteger.valueOf((1000 * 1000 * 1000)).add(BigInteger.valueOf(7)); 
	
	static {
		factorials[0] = BigInteger.ONE;
		factorials[1] = BigInteger.ONE;
		factorials[2] = factorials[2 - 1].multiply(BigInteger.valueOf(2l));
		for(int index=3;index<100000;index++) {
			factorials[index] = null;
		}
	}
	
	private static BigInteger modulo(BigInteger factorial) {
		return factorial.mod((divider));
	}
	
	
	private static BigInteger factorial(Integer index) {		
		if(factorials[index] == null) {
			factorials[index] = factorial(index - 1).multiply(BigInteger.valueOf(index));		
		}		
		return factorials[index];
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Integer T = s.nextInt();
		while(T > 0) {
			System.out.println(modulo(factorial(s.nextInt())));
			T--;
		}
	}
}
