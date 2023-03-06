package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

public class ListNode<T extends Comparable<T>> {
	public T value;
	public ListNode<T> next;

	public ListNode(T value, ListNode<T> next) {
		this.value = value;
		this.next = next;
	}

	public ListNode(T value) {
		this(value, null);
	}
}
