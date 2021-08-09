package Queue;

import java.util.Iterator;

import LinkedList.DoublyLinkedListImpl;

public class QueueLinkedList<T> implements Queue<T> {
	private DoublyLinkedListImpl<T> linkedList;

	public QueueLinkedList() {
		this.linkedList = new DoublyLinkedListImpl<T>();
	}

	@Override
	public void enQueue(T element) {
		linkedList.add(element);
	}

	@Override
	public T dequeue() {
		return linkedList.removeFirst();
	}

	@Override
	public T peek() {
		return linkedList.peekFirst();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return linkedList.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return linkedList.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}
}
