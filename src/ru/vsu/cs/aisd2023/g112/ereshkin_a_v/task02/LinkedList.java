package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
	public static class LinkedListException extends Exception {
		public LinkedListException(String message) {
			super(message);
		}
	}

	/**
	 * "Первая" нода
	 * */
	private ListNode<T> head = null;
	/**
	 * "Последняя" нода
	 * */
	private ListNode<T> tail = null;
	private int size = 0;

	// O(1)
	public void addFirst(T value) {
		head = new ListNode<T>(value, head);
		if (size == 0) {
			tail = head;
		}
		size++;
	}

	// O(1)
	public void addLast(T value) {
		if (size == 0) {
			head = tail = new ListNode<>(value);
		} else {
			tail.next = new ListNode<>(value);
			tail = tail.next;
		}
		size++;
	}
	@SafeVarargs
	public final void addLast(T... values){
		for (T value : values) {
			addLast(value);
		}
	}
	private void checkEmptyError() throws LinkedListException {
		if (size == 0) {
			throw new LinkedListException("Empty list");
		}
	}

	// O(n)
	public ListNode<T> getNode(int index) {
		ListNode<T> curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	// O(1)
	public void removeFirst() throws LinkedListException {
		checkEmptyError();
		head = head.next;
		if (size == 1) {
			tail = null;
		}
		size--;
	}

	// O(n)
	public void removeLast() throws LinkedListException {
		checkEmptyError();
		if (size == 1) {
			head = tail = null;
		} else {
			tail = getNode(size - 2);
			tail.next = null;
		}
		size--;
	}

	// O(n)
	public void remove(int index) throws LinkedListException {
		checkEmptyError();
		if (index < 0 || index >= size) {
			throw new LinkedListException("Array out of bounds exception");
		}
		if (index == 0) {
			removeFirst();
		} else {
			ListNode<T> prev = getNode(index - 1);
			prev.next = prev.next.next;
			if (prev.next == null) {
				tail = prev;
			}
			size--;
		}
	}

	// O(1)
	public int size() {
		return size;
	}

	// O(n)
	public T get(int index) {
		try {
			checkEmptyError();
		} catch (LinkedListException e){
			return null;
		}
		return getNode(index).value;
	}

	// O(1)
	public T getFirst() throws LinkedListException {
		checkEmptyError();
		return head.value;
	}

	// O(1)
	public T getLast() throws LinkedListException {
		checkEmptyError();
		return tail.value;
	}

	//perform bubble sort in single linked list
	public void bubbleSort() {
		if (head == null || head == tail) {
			return;
		}
		ListNode<T> newHead = null;
		ListNode<T> moveNode;
		ListNode<T> prev;
		while (head != null) {
			prev = null;
			ListNode<T> current = head;
			moveNode = head;
			while (current != null) {
				//Когда значение текущей ноды больше значения предыдущей ноды
				if (current.next != null && current.next.value.compareTo(moveNode.value) > 0) {
					// Меняем ноды
					moveNode = current.next;
					prev = current;
				}
				// Делаем текущей нодой следующую
				current = current.next;
			}
			if (moveNode == head) {
				head = head.next;
			}
			if (prev != null) {
				prev.next = moveNode.next;
			}
			moveNode.next = newHead;
			newHead = moveNode;
		}
		//Ставим вместо head новый элемент
		head = newHead;
	}
	@Override
	public Iterator<T> iterator() {
		class LinkedListIterator implements Iterator<T> {
			ListNode<T> curr = head;

			@Override
			public boolean hasNext() {
				return curr != null;
			}
			@Override
			public T next() {
				T value = curr.value;
				curr = curr.next;
				return value;
			}
		}

		return new LinkedListIterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			sb.append(i > 0 ? ", " : "").append(get(i));
		}
		return sb.toString();
	}
}
