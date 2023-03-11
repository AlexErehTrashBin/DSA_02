package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import java.util.Arrays;

public class LinkedListUtils {
	public static Integer[] toArray(LinkedList<Integer> integerList) {
		Integer[] resultArray = new Integer[integerList.size()];
		int i = 0;
		for (Integer value : integerList) {
			resultArray[i++] = value;
		}
		return resultArray;
	}

	public static <T> LinkedList<T> fromArray(T[] array) {
		LinkedList<T> resultList = new LinkedList<>();
		Arrays.stream(array).forEachOrdered(resultList::addLast);
		return resultList;
	}
}
