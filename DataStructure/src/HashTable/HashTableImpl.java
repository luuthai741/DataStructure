package HashTable;

import java.util.Arrays;
import java.util.Iterator;

import LinkedList.DoublyLinkedList;
import LinkedList.DoublyLinkedListImpl;

public class HashTableImpl<K, V> implements HashTable<K, V> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final double DEFAULT_LOAD_FACTOR = 0.5;
	private final double loadFactor;
	private int size = 0, capacity, threshold;
	private DoublyLinkedList<Node<K, V>>[] table;

	public HashTableImpl() {
		this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
	}

	public HashTableImpl(int capacity) {
		this(DEFAULT_LOAD_FACTOR, capacity);
	}

	public HashTableImpl(double loadFactor, int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException();
		if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isFinite(loadFactor))
			throw new IllegalArgumentException();
		this.loadFactor = loadFactor;
		this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
		this.threshold = (int) (this.capacity * loadFactor);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int hashCodeToIndex(int hashedKey) {
		return (int) (hashedKey * 0xFFFFFFFFFL) % capacity;
	}

	@Override
	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}

	@Override
	public boolean has(K key) {
		int index = hashCodeToIndex(key.hashCode());
		DoublyLinkedList<Node<K, V>> linkedList = table[index];
		if (linkedList == null || linkedList.isEmpty())
			return false;
		Iterator<Node<K, V>> iterator = linkedList.iterator();
		if (iterator.hasNext()) {
			Node<K, V> node = iterator.next();
			if (node.getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public V insert(K key, V value) {
		int index = hashCodeToIndex(key.hashCode());
		DoublyLinkedList<Node<K, V>> linkedList = table[index];
		if (linkedList == null) table[index] = linkedList =  new DoublyLinkedListImpl<>();
		
		return null;
	}

	@Override
	public V get(K key) {
		int index = hashCodeToIndex(key.hashCode());
		DoublyLinkedList<Node<K, V>> linkedList = table[index];
		if (linkedList == null || linkedList.isEmpty())
			return null;
		Iterator<Node<K, V>> iterator = linkedList.iterator();
		if (iterator.hasNext()) {
			Node<K, V> node = iterator.next();
			if (node.getKey().equals(key))
				return node.getValue();
		}
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
