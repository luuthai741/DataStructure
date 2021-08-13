package Tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import Queue.Queue;
import Queue.QueueLinkedList;
import Stack.LinkedListStack;
import Stack.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
	private int size;
	private Node<T> root = null;

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
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
	public void add(T element) {
		if (contains(element)) {
			return;
		}
		root = add(root, element);
		size++;
	}

	@Override
	public void remove(T element) {
		if (!contains(element)) {
			return;
		} else {
			root = remove(root, element);
			size--;
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

	// ROOT LEFT RIGHT
	private Iterator<T> preOrderTraverse() {
		final int expectedCount = size;
		Stack<Node<T>> stack = new LinkedListStack<Node<T>>();
		stack.push(root);
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				if (expectedCount != size)
					throw new ConcurrentModificationException();
				return root != null && stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedCount != size)
					throw new ConcurrentModificationException();
				else {
					Node<T> node = stack.pop();
					if (node.getLeft() != null) {
						stack.push(node.getLeft());
					} else if (node.getRight() != null) {
						stack.push(node.getRight());
					}
					return node.getData();
				}
			}
		};
	}

	private Iterator<T> postOrderTraverse() {
		final int expectedCount = size;
		Stack<Node<T>> stack = new LinkedListStack<Node<T>>();
		stack.push(root);
		return new Iterator<T>() {
			Node<T> trav = root;

			@Override
			public boolean hasNext() {
				if (expectedCount != size) {
					throw new ConcurrentModificationException();
				}
				return root != null && stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedCount != size) {
					throw new ConcurrentModificationException();
				}
				while (trav != null && trav.getLeft() != null) {
					stack.push(trav.getLeft());
					trav = trav.getLeft();
				}
				if (trav.getRight() != null) {
					stack.push(trav.getRight());
					trav = trav.getRight();
				}
				Node<T> node = stack.pop();
				return node.getData();
			}
		};
	}

	private Iterator<T> levelOrderTraverse() {
		if(root == null) return null;
		Queue<Node<T>> queue = new QueueLinkedList<Node<T>>();
		queue.enQueue(root);
		queue.enQueue(null);
		while(!queue.isEmpty()) {
			Node<T> currentNode = queue.dequeue();
			if(currentNode == null) {
				if(!queue.isEmpty()) {
					queue.enQueue(null);
				}
			}else {
				if(currentNode.getLeft() != null) {
					queue.enQueue(currentNode.getLeft());
				}else if(currentNode.getRight() != null) {
					queue.enQueue(currentNode.getRight());
				}
				queue.dequeue();
			}
		}
		return null;
	}

	// LEFT ROOT RIGHT
	private Iterator<T> inOrderTraverse() {
		final int expectedCount = size;
		Stack<Node<T>> stack = new LinkedListStack<Node<T>>();
		stack.push(root);
		return new Iterator<T>() {
			Node<T> trav = root;

			@Override
			public boolean hasNext() {
				if (expectedCount != size) {
					throw new ConcurrentModificationException();
				}
				return root != null && stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedCount != size) {
					throw new ConcurrentModificationException();
				}
				while (trav != null && trav.getLeft() != null) {
					stack.push(trav.getLeft());
					trav = trav.getLeft();
				}
				Node<T> node = stack.pop();
				if (node.getRight() != null) {
					stack.push(node.getRight());
					trav = node.getRight();
				}
				return node.getData();
			}
		};
	}

	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
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
			int check = element.compareTo(node.getData());
			if (check < 0) {
				node.setLeft(add(node.getLeft(), element));
			} else if (check > 0) {
				node.setRight(add(node.getRight(), element));
			}
		}
		return node;
	}

	private Node remove(Node<T> node, T element) {
		if (node == null)
			return node;
		int check = element.compareTo(node.getData());
		if (check < 0) {
			node.setLeft(remove(node.getLeft(), element));
		} else if (check > 0) {
			node.setRight(remove(node.getRight(), element));
		} else {
			if (node.getLeft() == null && node.getRight() == null) { // đây là node leaf
				return null;
			} else if (node.getRight() != null) {
				node.setData(successor(node));
				node.setRight(remove(node.getRight(), node.getData()));
			} else {
				node.setData(predecessor(node));
				node.setLeft(remove(node.getLeft(), node.getData()));
			}
		}
		return node;
	}

	private T successor(Node<T> node) {
		node = node.getRight();
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node.getData();
	}

	private T predecessor(Node<T> node) {
		node = node.getLeft();
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node.getData();
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		else {
			StringBuilder builder = new StringBuilder();
			return traverseLNR(root, builder).toString();
		}
	}

	private String traverseLNR(Node<T> node, StringBuilder builder) {
		if (node != null) {
			builder.append(node.getData()).append(" ");
			traverseLNR(node.getLeft(), builder);

			traverseLNR(node.getRight(), builder);
		}
		return builder.toString();
	}
}
