package cc4102.tarea3.algorithm.mst;


/**
 * Class used in homework 2 adapted to work in this homework and optimized for sets of repeated elements. Used in {@link Kruskal}}
 * @author Gonzalo
 */
public class QuickSort {
	CustomComparator comparator;
	int stackSize = 0;
	public final long[] sort(long[] array, CustomComparator comparator) {
		this.comparator = comparator;
		quicksort(array, 0, array.length - 1);
		return array;
	}
	
	private final void quicksort(long[] array, int left, int right) {
		//System.out.println("ss:"+(stackSize++)+"\n"+left+" "+right);
		if (left < right) {
			int pivotIndex = (int)(Math.random()*(right-left)+left);
			// Get lists of bigger and smaller items and final position of pivot
			int pivotNewIndex = partition(array, left, right, pivotIndex);
			int rightPivotIndex = pivotNewIndex;
			// Recursively sort elements smaller than the pivot (assume pivotNewIndex - 1 does not underflow)
			while( pivotNewIndex > left+1 && comparator.compare(array[pivotNewIndex-1], array[pivotNewIndex-2]) == 0) pivotNewIndex--;
			quicksort(array, left, pivotNewIndex - 1);
			// Recursively sort elements at least as big as the pivot (assume pivotNewIndex + 1 does not overflow)
			while( rightPivotIndex < right-1 && comparator.compare(array[rightPivotIndex+1], array[rightPivotIndex+2]) == 0) rightPivotIndex++;
			quicksort(array, rightPivotIndex + 1, right);
		}
		//stackSize--;
	}
	
	private int partition(long[] array, int left, int right, int pivotIndex) {
	    long pivotValue = array[pivotIndex];
	    long tmp = array[right];
	    array[right] = pivotValue;
	    array[pivotIndex] = tmp;
	    int storeIndex = left;
	    for (int i = left; i < right; i++) {
	        //if (array[i] <= pivotValue) {
	    	if (comparator.compare(array[i], pivotValue) != 1) {
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
		int compare(long l1, long l2);
	}
}
