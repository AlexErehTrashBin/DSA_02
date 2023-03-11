package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
	/**
	 * "Первая" нода
	 */
	private ListNode<T> head = null;
	/**
	 * "Последняя" нода
	 */
	private ListNode<T> tail = null;
	/**
	* Поле для размера списка
	* */
	private int size = 0;

	/**
	 * Создаёт пустой связный список.
	 * */
	public LinkedList() {
	}

	/**
	 * Добавить элемент в начало списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 * @param value элемент, который нужно добавить.
	 * */
	public void addFirst(T value) {
		head = new ListNode<>(value, head);
		if (size == 0) {
			tail = head;
		}
		size++;
	}

	/**
	 * Добавить элемент в конец списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 * @param value элемент, который нужно добавить.
	 * */
	public void addLast(T value) {
		if (size == 0) {
			head = tail = new ListNode<>(value);
		} else {
			tail.next = new ListNode<>(value);
			tail = tail.next;
		}
		size++;
	}

	/**
	 * Добавить несколько элементов в конец списка.
	 * <br>
	 * Временная сложность - <b>O(N)</b>.
	 * @param values элементы, которые нужно добавить.
	 * */
	@SafeVarargs
	public final void addLast(T... values) {
		for (T value : values) {
			addLast(value);
		}
	}

	private void checkEmptyError() throws LinkedListException {
		if (size == 0) {
			throw new LinkedListException("Empty list");
		}
	}

	/**
	 * Метод, который получает ноду с индексом index.
	 * <br>
	 * Временная сложность - <b>O(N)</b>.
	 * @param index индекс ноды, которую надо получить.
	 * @return нода по индексу index.
	 * */
	private ListNode<T> getNode(int index) {
		ListNode<T> curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	/**
	 * Метод, который удаляет первый элемент.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 * @throws LinkedListException если список пустой.
	 * */
	public void removeFirst() throws LinkedListException {
		checkEmptyError();
		head = head.next;
		if (size == 1) {
			tail = null;
		}
		size--;
	}

	/**
	 * Метод, который удаляет последний элемент.
	 * <br>
	 * Временная сложность - <b>O(N).</b>
	 * @throws LinkedListException если список пустой.
	 * */
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

	/**
	 * Метод, который удаляет элемент с индексом index.
	 * <br>
	 * Временная сложность - <b>O(N).</b>
	 * @param index индекс, по которому надо удалить элемент.
	 * @throws LinkedListException если index находится за пределом допустимых значений индекса списка.
	 * */
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

	/**
	 * Метод получения размера списка
	 * <br>
	 * Временная сложность - O(1)
	 * @return размер списка
	 * */
	public int size() {
		return size;
	}

	/**
	 * Получить элемент списка по индексу
	 * <br>
	 * Временная сложность - <b>O(N).</b>
	 * @param index индекс, по которому надо вернуть элемент.
	 * @return элемент списка по индексу index, если список не пустой.
	 * Если список пустой - возвращается null.
	 * */
	public T get(int index) {
		try {
			checkEmptyError();
		} catch (LinkedListException e) {
			return null;
		}
		return getNode(index).value;
	}

	/**
	 * Получить первый элемент списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>
	 * @return первый элемент списка.
	 * @throws LinkedListException если список пустой.
	 * */
	public T getFirst() throws LinkedListException {
		checkEmptyError();
		return head.value;
	}

	/**
	 * Получить последний элемент списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 * @return последний элемент списка.
	 * @throws LinkedListException если список пустой.
	 * */
	public T getLast() throws LinkedListException {
		checkEmptyError();
		return tail.value;
	}

	/**
	 * Отсортировать список методом пузырьковой сортировки.
	 * <br>
	 * Временная сложность - <b>O(N<sup>2</sup>)</b>
	 * @param comparator компаратор, который определяет критерий для установления порядка итогового списка.
	 * */
	public void bubbleSort(Comparator<T> comparator) {
		if (size <= 1) return;
		// Цикл, проходящийся по каждому элементу списка
		for (int i = 0; i < size; ++i) {
			ListNode<T> previousNode = head;
			// Устанавливаем текущую и предыдущую ей ноду на начало списка (head)
			ListNode<T> currentNode = head;
			// Для оставшихся элементов в списке - проверяем, и если требуется - обмениваем их местами.
			while (currentNode.next != null) {
				// Проверяем компаратором текущую и следующую ноды, следует ли менять их местами:
				if (comparator.compare(currentNode.value, currentNode.next.value) > 0) {
					ListNode<T> temporaryNode = currentNode.next;
					/// Меняем ссылки для текущего и следующего за текущим нод списка.
					currentNode.next = currentNode.next.next;
					temporaryNode.next = currentNode;
					/// Устанавливаем указатели для элементов, которые перед сравниваемыми
					boolean isCurrentNodeHead = currentNode == head;
					if (isCurrentNodeHead){
						/// СИТУАЦИЯ ДЛЯ САМОГО ПЕРВОГО ЭЛЕМЕНТА:
						// Ставим вместо головы временную ноду
						head = temporaryNode;
					} else {
						/// СИТУАЦИЯ ДЛЯ ОСТАЛЬНЫХ ЭЛЕМЕНТОВ:
						// Устанавливаем в следующую после предыдущей ноды временную ноду
						previousNode.next = temporaryNode;
					}
					// Обновляем текущую ноду, чтобы она стала временной, т.к. позиции изменились
					currentNode = temporaryNode;
				}
				/// Двигаемся вперёд (ставим указатели на следующие за ними элементы)
				previousNode = currentNode;
				currentNode = currentNode.next;
			}
		}
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

	public static class LinkedListException extends Exception {
		public LinkedListException(String message) {
			super(message);
		}
	}

	/**
	 * Элемент связного списка, который содержит данные (value) и указатель на следующий узел (next).
	 * @param <E> тип данных, хранимый в данном узле списка.
	 */
	protected class ListNode<E> {
		public E value;
		public ListNode<E> next;

		public ListNode(E value, ListNode<E> next) {
			this.value = value;
			this.next = next;
		}

		public ListNode(E value) {
			this(value, null);
		}
	}
}
