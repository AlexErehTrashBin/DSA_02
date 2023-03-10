package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.addLast(7, 6, 5, 4, 3, 7, 6, 5, 4, 3);
		System.out.println(list);
		list.bubbleSort(Comparator.naturalOrder());
		System.out.println(list);
	}
}