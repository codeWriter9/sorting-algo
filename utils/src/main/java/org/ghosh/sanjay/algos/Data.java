package org.ghosh.sanjay.algos;

/**
 * 
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 * @param <T>
 */
public class Data<T extends Comparable<T>> {

	T data;

	/**
	 * 
	 * 
	 * 
	 * @param data
	 */
	public Data(T data) {
		if (data == null)
			throw new IllegalArgumentException("parameter is null");
		this.data = data;
	}
	
	/**
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public static Data<Integer> integer(int integer) {
		return new Data<Integer>(new Integer(integer));
	}
	
	/**
	 * 
	 * 
	 * @param longInteger
	 * @return
	 */
	public static Data<Long> longInteger(long longInteger) {
		return new Data<Long>(new Long(longInteger));
	}
	
	/**
	 * 
	 * 
	 * @param _double
	 * @return
	 */
	public static Data<Double> doubleNumber(double _double) {
		return new Data<Double>(new Double(_double));
	}
	
	/**
	 * 
	 * 
	 * @param _double
	 * @return
	 */
	public static Data<Float> floatNumber(double _float) {
		return new Data<Float>(new Float(_float));
	}

	/**
	 * 
	 * 
	 * 
	 * @param otherData
	 * @return
	 */
	public boolean isEqual(Data<T> otherData) {
		return this.data.equals(otherData.data);
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public T value() {
		return data;
	}

	/**
	 * 
	 * 
	 * 
	 * @param otherData
	 * @return True if current Data<T> is greater than parameter else False
	 */
	public boolean isGreater(Data<T> otherData) {
		return data.compareTo(otherData.value()) > 0;
	}

	/**
	 * 
	 * 
	 * 
	 * @param otherData
	 * @return True if current Data<T> is smaller than parameter else False
	 */
	public boolean isSmaller(Data<T> otherData) {
		return data.compareTo(otherData.value()) < 0;
	}

	/**
	 * 
	 * 
	 * 
	 * @Override
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (o instanceof Data<?>) {
			Data<T> otherData = (Data<T>) o;
			return this.isEqual(otherData);
		} else
			return false;
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public int hashCode() {
		return data.hashCode();
	}
}