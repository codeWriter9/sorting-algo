package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import org.slf4j.Logger;

public class ConstructorEnumerator implements Iterator<Constructor<?>> {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());

	private Class<?> clazz;
	private Constructor<?>[] m;
	private int index;

	public ConstructorEnumerator(String className) throws ClassNotFoundException {
		clazz = Class.forName(className);
		m = clazz.getConstructors();
		index = 0;
	}

	@Override
	public boolean hasNext() {
		if (index < m.length)
			return true;
		else
			return false;
	}

	@Override
	public Constructor<?> next() {
		return m[index++];
	}

}