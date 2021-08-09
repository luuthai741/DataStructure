package LinkedList;

public interface DoublyLinkedList<T> extends Iterable<T> {
	// O(n)
	void clear();

	int size();

	boolean isEmpty();

	// O(1)
	void add(T element);

	// O(1)
	void addFirst(T element);

	// O(1)
	void addLast(T element);

	// O(1)
	T peekFirst();

	// O(1)
	T peekLast();

	// O(1)
	T removeFirst();

	// O(1)
	T removeLast();

	// O(n)
	T remove(DNode<T> node);
	
	// O(n/2)
	T removeAt(int index);
	
	// O(n/2)
	int indexOf(Object object);
	// O(n)
	boolean remove(Object object);

	// O(n)
	boolean contains(Object object);
}
