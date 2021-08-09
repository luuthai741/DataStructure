package Queue;

public interface Queue<T> extends Iterable<T> {
	void enQueue(T element);
	T dequeue();
	T peek();
	int size();
	boolean isEmpty();
}
