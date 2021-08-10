package Tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import Stack.LinkedListStack;
import Stack.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
	private int nodeCount = 0;
	private Node root = null;

	@Override
	public boolean isEmpty() {
		return nodeCount == 0;
	}

	@Override
	public int size() {
		return nodeCount;
	}

	@Override
	public int height() {
		return height(root);
	}

	@Override
	public boolean contains(T element) {
		return contains(root, element);
	}

	@Override
	public boolean add(T element) {
		if (contains(element))
			return false;
		else {
			root = add(root, element);
			nodeCount++;
			return true;
		}
	}

	@Override
	public boolean remove(T element) {
		if (!contains(element))
			return false;
		else {
			root = remove(root, element);
			nodeCount--;
			return true;
		}
	}

	@Override
	public Iterator<T> traverse(TreeType type) {
		switch (type) {
		case IN_ORDER: {

			return inOrderTraverse();
		}
		case LEVEL_ORDER: {

			return levelOrderTraverse();
		}
		case POST_ORDER: {

			return postOrderTraverse();
		}
		case PRE_ORDER: {

			return preOrderTraverse();
		}
		default: {
			return null;
		}
		}
	}

	private Iterator<T> postOrderTraverse() {
		// TODO Auto-generated method stub
		return null;
	}

	private Iterator<T> levelOrderTraverse() {
		// TODO Auto-generated method stub
		return null;
	}

	private Iterator<T> inOrderTraverse() {
		final int expectedCount = nodeCount;
		Stack<Node> stack = new LinkedListStack<Node>();
		stack.push(root);
		return new Iterator<T>() {
			Node trav = root;

			@Override
			public boolean hasNext() {
				if (expectedCount != nodeCount)
					throw new ConcurrentModificationException();
				return root != null && stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedCount != nodeCount)
					throw new ConcurrentModificationException();
				while (trav != null && trav.getLeft() != null) {
					stack.push(trav.getLeft());
					trav = trav.getLeft();
				}
				Node node = stack.pop();
				if (node.getRight() != null) {
					stack.push(node.getRight());
					trav = node.getRight();
				}
				return (T) node.getData();
			}
		};
	}

	private Iterator<T> preOrderTraverse() {
		final int expectedCount = nodeCount;
		Stack<Node> stack = new LinkedListStack<Node>();
		stack.push(root);
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedCount != nodeCount)
					throw new ConcurrentModificationException();
				return root != null && stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedCount != nodeCount)
					throw new ConcurrentModificationException();
				Node node = stack.pop();
				if (node.getRight() != null) {
					stack.push(node.getRight());
				}
				if (node.getLeft() != null) {
					stack.push(node.getLeft());
				}
				return (T) node.getData();
			}
		};
	}

	private int height(Node node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.getLeft()), height(node.getLeft()));
	}

	private boolean contains(Node<T> node, T element) {
		if (node == null)
			return false;
		int result = element.compareTo(node.getData());

		if (result < 0) {
			return contains(node.getLeft(), element);
		} else if (result > 0) {
			return contains(node.getRight(), element);
		} else {
			return true;
		}
	}

	private Node add(Node<T> node, T element) {
		if (node == null) {
			node = new Node<T>(element, null, null);
		} else {
			int index = element.compareTo(node.getData());
			if (index > 0) {
				node.setRight(add(node.getRight(), element));
			} else {
				node.setLeft(add(node.getLeft(), element));
			}
		}
		return node;
	}

	private Node remove(Node node, T element) {
		int result = element.compareTo((T) node.getData());
		if (result < 0) {
			node.setLeft(remove(node.getLeft(), element));
		} else if (result > 0) {
			node.setRight(remove(node.getRight(), element));
		} else {
			if (node.getLeft() == null) {
				Node rightNode = node.getRight();
				node.setData(null);
				node = null;
				return rightNode;
			} else if (node.getRight() == null) {
				Node leftNode = node.getLeft();
				node.setData(null);
				node = null;
				return leftNode;
			} else {
				T temp = minRight(node);
				node.setData(temp);
				node.setRight(remove(node.getRight(), temp));
			}
		}
		return node;
	}

	private T minRight(Node node) {
		while (node.getLeft() != null) {
			node.getLeft();
		}
		return (T) node.getData();
	}
}
