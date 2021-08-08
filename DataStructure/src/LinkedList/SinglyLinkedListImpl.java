package LinkedList;

import java.nio.channels.IllegalChannelGroupException;
import java.util.Iterator;

public class SinglyLinkedListImpl<T> implements SinglyLinkedList<T> {
	private SNode<T> head;
	private int size;

	@Override
	public void clear() {
		if (isEmpty())
			return;
		else {
			SNode<T> currentNode = head;
			while (currentNode != null) {
				currentNode.setNext(null);
				currentNode.setData(null);
				currentNode = currentNode.getNext();
			}
			head = null;
			size = 0;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public void add(T element) {
		addFirst(element);
	}

	@Override
	public void addFirst(T element) {
		SNode<T> newNode = new SNode<T>(element, head);
		newNode.setNext(head);
		head = newNode;
		size++;
	}

	// O(n)
	@Override
	public void addLast(T element) {
		if (isEmpty())
			add(element);
		else {
			SNode<T> currentNode = head;
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			SNode<T> newNode = new SNode<T>(element, null);
			currentNode.setNext(newNode);
			size++;
		}
	}

	@Override
	public T peekFirst() {
		return head.getData();
	}

	@Override
	public T peekLast() {
		SNode<T> currentNode = head;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode.getData();
	}

	@Override
	public T removeFirst() {
		if (isEmpty())
			throw new IllegalArgumentException("Empty linked list");

		T data = head.getData();
		SNode<T> currentNode = head;
		head = currentNode.getNext();
		size--;
		return data;
	}

	@Override
	public T removeLast() {
		if (isEmpty())
			throw new IllegalArgumentException("Empty linked list");
		if (size == 1)
			removeFirst();

		SNode<T> currentNode = head;
		int i = 0;
		while (currentNode.getNext() != null && i < size - 2) {
			currentNode = currentNode.getNext();
			i++;
		}
		T data = currentNode.getNext().getData();
		currentNode.setNext(null);
		size--;
		return data;
	}

	@Override
	public T remove(SNode<T> node) {
		if (node.getNext().equals(null)) {
			return removeFirst();
		} else {
			SNode<T> currentNode = head;
			while (!currentNode.getNext().equals(node)) {
				currentNode = currentNode.getNext();
			}
			SNode<T> nextNode = currentNode.getNext();
			T data = nextNode.getData();
			currentNode.setNext(nextNode.getNext());
			nextNode = null;
			size--;
			return data;
		}

	}

	// O(n)
	@Override
	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		SNode<T> currentNode = head;
		int i = 0;
		while (i != index) {
			currentNode = currentNode.getNext();
			i++;
		}
		return remove(currentNode);
	}

	@Override
	public int indexOf(Object object) {
		int index = 0;
		SNode<T> currentNode = head;
		if (object == null) {
			while (currentNode != null) {
				if (currentNode.getData() == null) {
					return index;
				}
				currentNode = currentNode.getNext();
				index++;
			}
		} else {
			while (currentNode != null) {
				if (currentNode.getData() == object) {
					return index;
				}
				currentNode = currentNode.getNext();
				index++;
			}
		}
		return -1;
	}

	@Override
	public boolean remove(Object object) {
		int index = 0;
		SNode<T> currentNode = head;
		if (object == null) {
			while (currentNode != null) {
				if (currentNode.getData() == null) {
					removeAt(index);
					return true;
				}
				currentNode = currentNode.getNext();
				index++;
			}
		} else {
			while (currentNode != null) {
				if (currentNode.getData() == object) {
					removeAt(index);
					return true;
				}
				currentNode = currentNode.getNext();
				index++;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Object object) {
		return indexOf(object) != -1;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			SNode<T> currentNode = head;

			@Override
			public boolean hasNext() {
				return currentNode.getNext() != null;
			}

			@Override
			public T next() {
				T data = currentNode.getData();
				currentNode = currentNode.getNext();
				return data;
			}
		};
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		else {
			StringBuilder builder = new StringBuilder();
			SNode<T> currentNode = head;
			builder.append("[");
			while (currentNode != null) {
				builder.append(currentNode.getData());
				if (currentNode.getNext() != null)
					builder.append(",");
				currentNode = currentNode.getNext();
			}
			builder.append("]");
			return builder.toString();
		}

	}

}
