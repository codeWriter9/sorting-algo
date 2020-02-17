package org.ghosh.sanjay.algos;

import java.lang.reflect.Method;
import java.util.Iterator;

public class MethodEnumerator implements Iterator<Method> {

	private Class<?> clazz;
	private java.lang.reflect.Method[] m;
	private int index;

	public MethodEnumerator(String className) throws ClassNotFoundException {
		clazz = Class.forName(className);
		m = clazz.getDeclaredMethods();		
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
	public Method next() {
		return m[index++];
	}
}
