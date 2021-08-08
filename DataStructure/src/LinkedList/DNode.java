package LinkedList;

public class DNode<T> {
	private T data;
	private DNode<T> next, prev;

	public DNode(T data, DNode<T> next, DNode<T> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DNode<T> getNext() {
		return next;
	}

	public void setNext(DNode<T> next) {
		this.next = next;
	}

	public DNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}
	
}
