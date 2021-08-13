package HashTable;

import java.util.Iterator;

public interface HashTable<K, V> extends Iterable<K> {
	int size();

	boolean isEmpty();

	// hashcode
	int hashCodeToIndex(int hashedKey);

	void clear();

	boolean has(K key);

	V insert(K key, V value);

	V get(K key);

	V remove(K key);

}
