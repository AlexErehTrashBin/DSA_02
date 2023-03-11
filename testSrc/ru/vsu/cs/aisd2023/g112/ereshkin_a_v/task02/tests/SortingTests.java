package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.tests;

import org.junit.Test;
import ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.LinkedList;
import ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.LinkedListUtils;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class SortingTests {
	@Test
	public void test1() {
		Integer[] input = new Integer[]{
				100, 7
		};
		check(input);
	}

	@Test
	public void test2() {
		Integer[] input = new Integer[]{
				5, 10, 2, 200, -5, 40
		};
		check(input);
	}

	@Test
	public void test3() {
		Integer[] input = new Integer[]{
				100, 90, 80, 60, 10, 20, 30, 50, 60, 100
		};
		check(input);
	}

	@Test
	public void test4() {
		Integer[] input = new Integer[]{
				-1, 5, 3, 4, 0
		};
		check(input);
	}
	@Test
	public void test5() {
		Integer[] input = new Integer[]{
				-1
		};
		check(input);
	}

	private void check(Integer[] input) {
		Integer[] expected = Arrays.copyOf(input, input.length);
		Arrays.sort(expected);
		LinkedList<Integer> list = LinkedListUtils.fromArray(input);
		list.bubbleSort(Comparator.naturalOrder());
		Integer[] result = LinkedListUtils.toArray(list);
		assertArrayEquals(expected, result);
	}
}
