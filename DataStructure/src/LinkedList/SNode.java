package LinkedList;

public class SNode<T> {
	private T data;
	private SNode<T> next;

	public SNode(T data, SNode<T> next) {
		this.data = data;
		this.next = next;
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

	public SNode<T> getNext() {
		return next;
	}

	public void setNext(SNode<T> next) {
		this.next = next;
	}
	
}
