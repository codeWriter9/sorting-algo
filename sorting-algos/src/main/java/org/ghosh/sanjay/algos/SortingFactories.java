package org.ghosh.sanjay.algos;

public final class SortingFactories {

	public static Sort<Integer> integerSort(SortingAlgo algo, Integer[] array) {
		switch (algo) {
		case INSERTION_SORT:
			return new InsertionSort<>(array);
		case MERGE_SORT:
			return new MergeSort<>(array);
		case QUICK_SORT:
			return new QuickSort<>(array);
		case SELECTION_SORT:
			return new SelectionSort<>(array);
		case SHELL_SORT:
			return new ShellSort<>(array);
		default:
			throw new IllegalArgumentException(" algo not found ");
		}
	}

	public static Sort<Long> longSort(SortingAlgo algo, Long[] array) {
		switch (algo) {
		case INSERTION_SORT:
			return new InsertionSort<>(array);
		case MERGE_SORT:
			return new MergeSort<>(array);
		case QUICK_SORT:
			return new QuickSort<>(array);
		case SELECTION_SORT:
			return new SelectionSort<>(array);
		case SHELL_SORT:
			return new ShellSort<>(array);
		default:
			throw new IllegalArgumentException(" algo not found ");
		}
	}
	
	public static Sort<String> stringSort(SortingAlgo algo, String[] array) {
		switch (algo) {
		case INSERTION_SORT:
			return new InsertionSort<>(array);
		case MERGE_SORT:
			return new MergeSort<>(array);
		case QUICK_SORT:
			return new QuickSort<>(array);
		case SELECTION_SORT:
			return new SelectionSort<>(array);
		case SHELL_SORT:
			return new ShellSort<>(array);
		default:
			throw new IllegalArgumentException(" algo not found ");
		}
	}
	
	

	public static <T> Sort<?> sort(SortingAlgo algo, T[] array) {
		if (array instanceof Integer[])
			return integerSort(algo, (Integer[]) array);
		else if (array instanceof Long[])
			return longSort(algo, (Long[]) array);
		else if (array instanceof String[])
			return stringSort(algo, (String[]) array);
		else
			throw new IllegalArgumentException(" algo not found ");
	}
}