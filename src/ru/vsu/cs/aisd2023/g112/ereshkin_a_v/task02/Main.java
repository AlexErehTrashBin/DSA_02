package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

public class Main {
	public static void main(String[] args) {
		CustomLinkedList<Integer> list = new CustomLinkedList<>();
		list.addLast(5, 3, 2, 4, 4, 7);
		System.out.println(list);
		list.bubbleSort();
		System.out.println(list);
	}
}