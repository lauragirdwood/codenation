package br.com.codenation;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		int elementsSize = elements.length;
		for (int element : elements) {
			sum += element;
		}
		return sum/elementsSize;
	}

	public static int mode(int[] elements) {
		int mode = 0;
		for (int element : elements) {
			for (int j = 0; j < elements.length - 1; j++) {
				if (element == elements[j]) {
					mode = element;
					break;
				}
			}
		}
		return mode;
	}

	public static int median(int[] elements) {
		int elementsSize = elements.length;
		if(elementsSize % 2 != 0) {
			return elements[(elementsSize - 1)/2];
		} else {
			return elements[elementsSize/2] + elements[((elementsSize/2) + 1)/ 2];
		}
	}
}