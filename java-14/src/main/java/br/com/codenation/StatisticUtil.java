package br.com.codenation;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StatisticUtil {

	public static int average(int[] elements) {
		return (int) Arrays.stream(elements).average().orElse(0.0);
	}

	public static int mode(int[] elements) {
		int mode = 0;
		int countMax = 0;
		int count = 0;

		for (int element : elements) {
			count = 0;
			for (int elementComp : elements) {
				count = element == elementComp ? count + 1 : count;
				if (count > countMax) {
					countMax = count;
					mode = element;
				}
			}
		}
		return mode;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int meio = elements.length/2;

		if(elements.length % 2 != 0) {
			return elements[meio];
		} else {
			return (elements[(meio) - 1] + (elements[meio])) / 2;
		}
	}
}