package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

public class CustomLinkedListNode<T extends Comparable<T>> {
	public T value;
	public CustomLinkedListNode<T> next;

	public CustomLinkedListNode(T value, CustomLinkedListNode<T> next) {
		this.value = value;
		this.next = next;
	}

	public CustomLinkedListNode(T value) {
		this(value, null);
	}
}
