package Array;

import java.util.Iterator;

import LinkedList.DNode;

public class ArrayList<T> implements Iterable<T> {
	private T[] arr;
	private int size = 0;
	private int capacity = 0;

	public ArrayList() {
		this(10);
	}

	public ArrayList(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity cannot be negative " + capacity);
		this.capacity = capacity;
		this.arr = (T[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int size() {
		return size;
	}

	public T get(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		return arr[index];
	}

	public void set(int index, T value) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		arr[index] = value;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			arr[i] = null;
		}
		size = 0;
	}

	public void add(T element) {
		if (size >= capacity - 1) {
			if (capacity == 0)
				capacity = 1;
			else {
				capacity *= 2;
			}
			T[] newArr = (T[]) new Object[capacity];
			for (int i = 0; i < arr.length; i++) {
				newArr[i] = arr[i];
			}
			arr = newArr;
		}
		arr[size++] = element;
	}

	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		T value = arr[index];
		T[] newArr = (T[]) new Object[arr.length - 1];
		for (int i = 0, j = 0; i < newArr.length; i++, j++) {
			if (i != index) {
				newArr[i] = arr[i];
			} else {
				j--;
			}
		}
		arr = newArr;
		capacity = --size;
		return value;
	}

	public T remove(Object obj) {
		int index = indexOf(obj);
		if (index > -1)
			removeAt(index);
		return null;
	}

	public int indexOf(Object obj) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(obj))
				return i;
		}
		return -1;
	}

	public boolean contains(Object obj) {
		if (indexOf(obj) != -1)
			return true;
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return i < size;
			}

			@Override
			public T next() {
				return arr[i++];
			}
		};
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		else {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for (int i = 0; i < size - 1; i++) {
				builder.append(arr[i]).append(",");
			}
			builder.append(arr[size - 1]).append("]");
			return builder.toString();
		}
	}
}
