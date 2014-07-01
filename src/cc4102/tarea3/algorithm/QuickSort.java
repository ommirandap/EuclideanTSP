package cc4102.tarea3.algorithm;


public class QuickSort {
	CustomComparator comparator;
	int stackSize = 0;
	public final long[] sort(long[] array, CustomComparator comparator) {
		this.comparator = comparator;
		quicksort(array, 0, array.length - 1);
		return array;
	}
	
	private final void quicksort(long[] array, int left, int right) {
		System.out.println("ss:"+(stackSize++)+"\n"+left+" "+right);
		if (left < right) {
			int pivotIndex = (int)(Math.random()*(right-left)+left);
			// Get lists of bigger and smaller items and final position of pivot
			int pivotNewIndex = partition(array, left, right, pivotIndex);
			// Recursively sort elements smaller than the pivot (assume pivotNewIndex - 1 does not underflow)
			quicksort(array, left, pivotNewIndex - 1);
			// Recursively sort elements at least as big as the pivot (assume pivotNewIndex + 1 does not overflow)
			quicksort(array, pivotNewIndex + 1, right);
		}
		stackSize--;
	}
	
	private int partition(long[] array, int left, int right, int pivotIndex) {
	    long pivotValue = array[pivotIndex];
	    long tmp = array[right];
	    array[right] = pivotValue;
	    array[pivotIndex] = tmp;
	    int storeIndex = left;
	    for (int i = left; i < right; i++) {
	        //if (array[i] <= pivotValue) {
	    	if (!comparator.compare(array[i], pivotValue)) {
	        	swap(array, i, storeIndex++);
	        }
	    }
	    swap(array, storeIndex, right);
	    return storeIndex;
	}
	
	public static final void swap(long[] array, int pos1, int pos2) {
		long tmp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = tmp;
	}
	
	interface CustomComparator {
		boolean compare(long l1, long l2);
	}
}
