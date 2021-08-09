package Queue;

import java.util.Iterator;

public class QueueArray<T> implements Queue<T> {
	private T[] array;
	private int front;
	private int end;
	private int size;

	public QueueArray(int maxSize) {
		size = maxSize + 1;
		front = end = 0;
		array = (T[]) new Object[size];
	}

	@Override
	public void enQueue(T element) {
		array[end] = element;
		if (++end == size)
			end = 0;
		if (end == front)
			throw new RuntimeException("Queue is full");
	}

	@Override
	public T dequeue() {
		if (isEmpty())
			throw new RuntimeException("Queue is empty");
		T data = array[front];
		if (++front == size)
			front = 0;
		return data;
	}

	@Override
	public T peek() {
		if (isEmpty())
			throw new RuntimeException("Queue is empty");
		return array[front];
	}

	@Override
	public int size() {
		if (front > end)
			return (end + size - front);
		return end - front;
	}

	@Override
	public boolean isEmpty() {
		return front == size;
	}

	@Override
	public Iterator<T> iterator() {
		
		// TODO Auto-generated method stub
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return end > front;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
