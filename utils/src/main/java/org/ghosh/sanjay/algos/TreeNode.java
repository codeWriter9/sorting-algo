package org.ghosh.sanjay.algos;

public class TreeNode<T extends Comparable<T>> {

	Data<T> data;
	TreeNode<T> left;
	TreeNode<T> right;

	public TreeNode(Data<T> data) {
		if (data == null)
			throw new IllegalArgumentException("parameter data is null");
		this.data = data;
	}

	public TreeNode(Data<T> data, TreeNode<T> left, TreeNode<T> right) {
		this(data);
		this.left = left;
		this.right = right;
	}

	public Data<T> data() {
		return this.data;
	}
	
	public T value() {
		return this.data.value();
	}
	
	public TreeNode<T> left() {
		return left;
	}
	
	public TreeNode<T> right() {
		return right;
	}
}