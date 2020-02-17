package org.ghosh.sanjay.algos;

import java.lang.reflect.Constructor;
import java.util.Iterator;

public class ConstructorEnumerator implements Iterator<Constructor<?>> {

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