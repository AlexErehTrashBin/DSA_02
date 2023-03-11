package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	/**
	 * "Первая" нода
	 */
	private ListNode head = null;
	/**
	 * "Последняя" нода
	 */
	private ListNode tail = null;
	/**
	 * Поле для размера списка
	 */
	private int size = 0;

	/**
	 * Создаёт пустой связный список.
	 */
	public LinkedList() {
	}

	/**
	 * Добавить элемент в начало списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 *
	 * @param value элемент, который нужно добавить.
	 */
	public void addFirst(T value) {
		head = new ListNode(value, head);
		if (isEmpty()) {
			tail = head;
		}
		size++;
	}

	/**
	 * Добавить элемент в конец списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 *
	 * @param value элемент, который нужно добавить.
	 */
	public void addLast(T value) {
		if (isEmpty()) {
			head = tail = new ListNode(value);
		} else {
			tail.next = new ListNode(value);
			tail = tail.next;
		}
		size++;
	}

	/**
	 * Добавить несколько элементов в конец списка.
	 * <br>
	 * Временная сложность - <b>O(N)</b>.
	 *
	 * @param values элементы, которые нужно добавить.
	 */
	@SafeVarargs
	public final void addLast(T... values) {
		for (T value : values) {
			addLast(value);
		}
	}

	private void checkEmptyError() throws LinkedListException {
		if (isEmpty()) {
			throw new LinkedListException("Empty list");
		}
	}

	/**
	 * Метод, который получает ноду с индексом index.
	 * <br>
	 * Временная сложность - <b>O(N)</b>.
	 *
	 * @param index индекс ноды, которую надо получить.
	 * @return нода по индексу index.
	 */
	private ListNode getNode(int index) {
		ListNode curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	/**
	 * Метод, который удаляет первый элемент.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 *
	 * @return
	 * @throws LinkedListException если список пустой.
	 */
	public T removeFirst() throws LinkedListException {
		checkEmptyError();
		T first = head.value;
		head = head.next;
		if (size == 1) {
			tail = null;
		}
		size--;
		return first;
	}

	/**
	 * Метод, который удаляет последний элемент.
	 * <br>
	 * Временная сложность - <b>O(N).</b>
	 *
	 * @return
	 * @throws LinkedListException если список пустой.
	 */
	public T removeLast() throws LinkedListException {
		checkEmptyError();
		T last = tail.value;
		if (size == 1) {
			head = tail = null;
		} else {
			tail = getNode(size - 2);
			tail.next = null;
		}
		size--;
		return last;
	}

	/**
	 * Метод, который удаляет элемент с индексом index.
	 * <br>
	 * Временная сложность - <b>O(N).</b>
	 *
	 * @param index индекс, по которому надо удалить элемент.
	 * @throws LinkedListException если index находится за пределом допустимых значений индекса списка.
	 */
	public T remove(int index) throws LinkedListException {
		checkEmptyError();
		if (index < 0 || index >= size) {
			throw new LinkedListException("Array out of bounds exception");
		}
		if (index == 0) {
			T first = head.value;
			removeFirst();
			return first;
		}
		ListNode prev = getNode(index - 1);
		T indexElement = prev.next.value;
		prev.next = prev.next.next;
		if (prev.next == null) {
			tail = prev;
		}
		size--;
		return indexElement;
	}
	public void clear(){
		if (isEmpty()) return;
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Метод получения размера списка
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 *
	 * @return размер списка
	 */
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Получить элемент списка по индексу
	 * <br>
	 * Временная сложность - <b>O(N).</b>
	 *
	 * @param index индекс, по которому надо вернуть элемент.
	 * @return элемент списка по индексу index, если список не пустой.
	 * Если список пустой - возвращается null.
	 */
	public T get(int index) {
		return !isEmpty() ? getNode(index).value : null;
	}

	/**
	 * Получить первый элемент списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>
	 *
	 * @return первый элемент списка.
	 * @throws LinkedListException если список пустой.
	 */
	public T getFirst() throws LinkedListException {
		checkEmptyError();
		return head.value;
	}

	/**
	 * Получить последний элемент списка.
	 * <br>
	 * Временная сложность - <b>O(1)</b>.
	 *
	 * @return последний элемент списка.
	 * @throws LinkedListException если список пустой.
	 */
	public T getLast() throws LinkedListException {
		checkEmptyError();
		return tail.value;
	}

	/**
	 * Отсортировать список методом пузырьковой сортировки.
	 * <br>
	 * Временная сложность - <b>O(N<sup>2</sup>)</b>
	 *
	 * @param comparator компаратор, который определяет критерий для установления порядка итогового списка.
	 */
	public void bubbleSort(Comparator<T> comparator) {
		if (size <= 1) return;

		// Цикл, проходящийся по каждому элементу списка
		for (int i = 0; i < size - 1; i++) {
			ListNode previousNode = head;

			// Проверяем голову (на беды с башкой)
			boolean hasSwapped = false;
			ListNode top = head;
			if (comparator.compare(top.value, top.next.value) > 0) {
				head = top.next;
				ListNode temp = top.next.next;
				top.next.next = top;
				top.next = temp;
				hasSwapped = true;
			}

			int j = 0;
			// Для оставшихся элементов в списке - проверяем, и если требуется - обмениваем их местами.
			for (ListNode currentNode = head; j < size - i - 1; currentNode = currentNode.next) {
				// Проверяем компаратором текущую и следующую ноды, следует ли менять их местами:
				if (comparator.compare(currentNode.value, currentNode.next.value) > 0) {
					// Если дошли до конца списка - меняем ссылку tail.
					if (j == size - 3) {
						tail = currentNode.next;
					}
					ListNode temporaryNode = currentNode.next;
					/// Меняем ссылки для текущего и следующего за текущим нод списка.
					currentNode.next = currentNode.next.next;
					temporaryNode.next = currentNode;
					/// Устанавливаем указатели для элементов, которые перед сравниваемыми
					previousNode.next = temporaryNode;
					// Обновляем текущую ноду, чтобы она стала временной, т.к. позиции изменились
					currentNode = temporaryNode;

					hasSwapped = true;
				}
				j++;
				/// Двигаемся вперёд (ставим указатели на следующие за ними элементы)
				previousNode = currentNode;
			}
			if (!hasSwapped) {
				break;
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		class LinkedListIterator implements Iterator<T> {
			ListNode curr = head;

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
		sb.append('[');
		for (T element : this) {
			sb.append(element).append(", ");
		}
		if (this.isEmpty()){
			sb.append(']');
			return sb.toString();
		}
		sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

	public static class LinkedListException extends Exception {
		public LinkedListException(String message) {
			super(message);
		}
	}

	/**
	 * Элемент связного списка, который содержит данные (value) и указатель на следующий узел (next).
	 */
	protected class ListNode {
		public T value;
		public ListNode next;

		public ListNode(T value, ListNode next) {
			this.value = value;
			this.next = next;
		}

		public ListNode(T value) {
			this(value, null);
		}
	}
}
