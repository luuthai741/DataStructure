package Stack;

import java.util.EmptyStackException;
import java.util.Iterator;

import Array.ArrayList;



public class StackArray<T> implements Stack<T> {
	private ArrayList<T> arrayList;
	private int index = -1;

	public StackArray(int size) {
		this.arrayList = new ArrayList<T>(size);
	}

	@Override
	public Iterator<T> iterator() {
		return arrayList.iterator();
	}

	@Override
	public void push(T element) {
		index++;
		arrayList.add(element);
	}

	@Override
	public T pop() {
		if(isEmpty()) throw new EmptyStackException();
		return arrayList.removeWithoutMoving(index--);
	}

	@Override
	public T top() {
		return arrayList.get(index);
	}

	@Override
	public int size() {
		return arrayList.size();
	}

	@Override
	public boolean isEmpty() {
		return arrayList.isEmpty();
	}

}
