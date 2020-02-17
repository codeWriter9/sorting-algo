package org.ghosh.sanjay.algos;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.asList;
import static org.ghosh.sanjay.algos.Utils.isLessThan;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class PrimeNumberService implements PrimeService {

	private static final BigInteger TWO = ONE.add(ONE);
	private static final BigInteger THREE = TWO.add(ONE);
	private static final BigInteger FIVE = TWO.add(THREE);
	private static final BigInteger SEVEN = TWO.add(FIVE);
	private static final BigInteger ELEVEN = SEVEN.add(THREE).add(ONE);

	private BigInteger upperBound;

	private Set<BigInteger> primes;

	{
		primes = new LinkedHashSet<>(asList(TWO, THREE, FIVE, SEVEN, ELEVEN));
	}

	public PrimeNumberService(BigInteger upperBound) {
		this.upperBound = upperBound;
	}

	public Set<BigInteger> primes() {
		if (!(isLessThan(upperBound, ELEVEN.add(ONE)) || ELEVEN.add(ONE).equals(upperBound))) {
			for (BigInteger loop = ELEVEN.add(ONE); isLessThan(loop, upperBound); loop = loop.add(ONE)) {
				boolean isPrime = true;
				for (BigInteger currentPrime : primes) {
					if (loop.mod(currentPrime).equals(ZERO))
						isPrime = false;
				}
				if (isPrime)
					primes.add(loop);
			}
		}
		return primes;
	}
}