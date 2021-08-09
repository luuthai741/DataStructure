package Stack;

import java.util.EmptyStackException;
import java.util.Iterator;

import LinkedList.DoublyLinkedListImpl;
import LinkedList.DoublyLinkedListImpl;

public class LinkedListStack<T> implements Stack<T> {
	private DoublyLinkedListImpl<T> linkedList;
	private int index = -1;

	public LinkedListStack() {
		this.linkedList = new DoublyLinkedListImpl<T>();
	}

	@Override
	public void push(T element) {
		index++;
		linkedList.add(element);
	}

	@Override
	public T pop() {
		if (isEmpty())
			throw new EmptyStackException();
		index--;
		return linkedList.removeLast();
	}

	@Override
	public T top() {
		return linkedList.peekLast();
	}

	@Override
	public int size() {
		return linkedList.size();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return linkedList.iterator();
	}
}
