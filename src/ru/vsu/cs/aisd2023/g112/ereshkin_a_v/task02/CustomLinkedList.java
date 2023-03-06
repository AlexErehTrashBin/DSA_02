package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import java.util.Iterator;

public class CustomLinkedList<T extends Comparable<T>> implements Iterable<T> {
	public static class CustomLinkedListException extends Exception {
		public CustomLinkedListException(String message) {
			super(message);
		}
	}

	/**
	 * "Первая" нода
	 * */
	private CustomLinkedListNode<T> head = null;
	/**
	 * "Последняя" нода
	 * */
	private CustomLinkedListNode<T> tail = null;
	private int size = 0;

	// O(1)
	public void addFirst(T value) {
		head = new CustomLinkedListNode<T>(value, head);
		if (size == 0) {
			tail = head;
		}
		size++;
	}

	// O(1)
	public void addLast(T value) {
		if (size == 0) {
			head = tail = new CustomLinkedListNode<>(value);
		} else {
			tail.next = new CustomLinkedListNode<>(value);
			tail = tail.next;
		}
		size++;
	}
	public void addLast(T... values){
		for (T value : values) {
			addLast(value);
		}
	}
	private void checkEmptyError() throws CustomLinkedListException {
		if (size == 0) {
			throw new CustomLinkedListException("Empty list");
		}
	}

	// O(n)
	public CustomLinkedListNode<T> getNode(int index) {
		CustomLinkedListNode<T> curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	// O(1)
	public void removeFirst() throws CustomLinkedListException {
		checkEmptyError();
		head = head.next;
		if (size == 1) {
			tail = null;
		}
		size--;
	}

	// O(n)
	public void removeLast() throws CustomLinkedListException {
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
	public void remove(int index) throws CustomLinkedListException {
		checkEmptyError();
		if (index < 0 || index >= size) {
			throw new CustomLinkedListException("Array out of bounds exception");
		}
		if (index == 0) {
			removeFirst();
		} else {
			CustomLinkedListNode<T> prev = getNode(index - 1);
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
		} catch (CustomLinkedListException e){
			return null;
		}
		return getNode(index).value;
	}

	// O(1)
	public T getFirst() throws CustomLinkedListException {
		checkEmptyError();
		return head.value;
	}

	// O(1)
	public T getLast() throws CustomLinkedListException {
		checkEmptyError();
		return tail.value;
	}

	//perform bubble sort in single linked list
	public void bubbleSort() {
		if (head == null || head == tail) {
			return;
		}
		CustomLinkedListNode<T> new_head = null;
		CustomLinkedListNode<T> move_node;
		CustomLinkedListNode<T> prev;
		while (head != null) {
			prev = null;
			CustomLinkedListNode<T> current = head;
			move_node = head;
			while (current != null) {
				//Когда значение текущей ноды больше значения предыдущей ноды
				if (current.next != null && current.next.value.compareTo(move_node.value) > 0) {
					// Меняем ноды
					move_node = current.next;
					prev = current;
				}
				// Делаем текущей нодой следующую
				current = current.next;
			}
			if (move_node == head) {
				head = head.next;
			}
			if (prev != null) {
				prev.next = move_node.next;
			}
			move_node.next = new_head;
			new_head = move_node;
		}
		//Ставим вместо head новый элемент
		head = new_head;
	}
	@Override
	public Iterator<T> iterator() {
		class SimpleLinkedListIterator implements Iterator<T> {
			CustomLinkedListNode<T> curr = head;

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

		return new SimpleLinkedListIterator();
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
