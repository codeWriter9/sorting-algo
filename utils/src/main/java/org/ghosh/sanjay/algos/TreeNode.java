package org.ghosh.sanjay.algos;

/**
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 * @param <T>
 */
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

	public static TreeNode<Integer> integer(int integer) {
		return new TreeNode<Integer>(Data.integer((integer)));
	}

	public Data<T> data() {
		return this.data;
	}

	public T value() {
		return this.data.value();
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
}