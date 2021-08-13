package Tree;

import java.util.Iterator;

public interface Tree<T extends Comparable<T>> {
	boolean isEmpty();

	int size();

	int height();

	boolean contains(T element);

	void add(T element);

	void remove(T element);
	Iterator<T> traverse(TreeType type);
}
