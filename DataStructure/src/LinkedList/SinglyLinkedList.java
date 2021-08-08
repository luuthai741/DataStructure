package LinkedList;

public interface SinglyLinkedList<T> extends Iterable<T> {
	// O(n)
	void clear();

	// O(1)
	int size();

	// O(1)
	boolean isEmpty();

	// O(1)
	void add(T element);

	// O(1)
	void addFirst(T element);

	// O(n)
	void addLast(T element);

	// O(1)
	T peekFirst();

	// O(n)
	T peekLast();

	// O(1)
	T removeFirst();

	// O(n)
	T removeLast();

	// O(n)
	T remove(SNode<T> node);

	// O(n/2)
	T removeAt(int index);

	// O(n/2)
	int indexOf(Object object);

	// O(n)
	boolean remove(Object object);

	// O(n)
	boolean contains(Object object);
}
