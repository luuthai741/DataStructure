package LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<T> implements DoublyLinkList<T> {
	private int size;
	private DNode<T> head = null, tail = null;

	@Override
	public void clear() {
		DNode<T> currentNode = head;
		while (currentNode != null) {
			DNode<T> nextNode = currentNode.getNext();
			currentNode.setNext(null);
			currentNode.setPrev(null);
			currentNode.setData(null);
			currentNode = nextNode;
		}
		head = tail = null;
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void add(T element) {
		addLast(element);
	}

	@Override
	public void addFirst(T element) {
		if (isEmpty()) {
			head = tail = new DNode<T>(element, null, null);
		} else {
			DNode<T> newNode = new DNode<T>(element, head, null);
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}

	@Override
	public void addLast(T element) {
		if (isEmpty()) {
			head = tail = new DNode<T>(element, null, null);
		} else {
			DNode<T> newNode = new DNode<T>(element, null, tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}

	@Override
	public T peekFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty linked list");
		return head.getData();
	}

	@Override
	public T peekLast() {
		if (isEmpty())
			throw new RuntimeException("Empty linked list");
		return tail.getData();
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new RuntimeException("Empty linked list");
		}
		T data = head.getData();
		DNode<T> currentNode = head;
		head = currentNode.getNext();
		size--;
		if (isEmpty()) {
			tail = null;
		} else {
			head.setPrev(null);
		}
		return data;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new RuntimeException("Empty linked list");
		}
		T data = tail.getData();
		DNode<T> currentNode = tail;
		tail = currentNode.getPrev();
		size--;
		if (isEmpty()) {
			head = null;
		} else {
			tail.setNext(null);
		}
		return data;
	}

	@Override
	public T remove(DNode<T> node) {
		if (node.getNext() == null) {
			return removeLast();
		}
		if (node.getPrev() == null) {
			return removeFirst();
		}

		DNode<T> prevNode = node.getPrev();
		DNode<T> nextNode = node.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);

		T data = node.getData();
		size--;

		node.setData(null);
		node.setNext(null);
		node.setPrev(null);
		node = null;

		return data;
	}

	@Override
	public boolean remove(Object object) {
		DNode<T> currentNode = head;
		if (object == null) {
			while (currentNode != null) {
				if (currentNode.getData() == null) {
					remove(currentNode);
					return true;
				}
				currentNode = currentNode.getNext();
			}
		} else {
			if (currentNode.getData().equals(object)) {
				remove(currentNode);
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
	}

	@Override
	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		DNode<T> currentNode;
		int i = 0;
		if (index < size / 2) {
			currentNode = head;
			while (i != index) {
				currentNode = currentNode.getNext();
				i++;
			}
		} else {
			currentNode = tail;
			i = size - 1;
			while (i != index) {
				currentNode = currentNode.getPrev();
				i--;
			}
		}
		return remove(currentNode);
	}

	@Override
	public int indexOf(Object object) {
		int index = 0;
		DNode<T> currentNode = head;
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
				if (currentNode.getData().equals(object)) {
					return index;
				}
				currentNode = currentNode.getNext();
				index++;
			}
		}
		return -1;
	}

	@Override
	public boolean contains(Object object) {
		return indexOf(object) != -1;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private DNode<T> currentNode = head;

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
			DNode<T> currentNode = head;
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
