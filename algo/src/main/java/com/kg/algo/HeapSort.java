package com.kg.algo;

public class HeapSort {
	
	/*
	 * Runs in O(n) time.
	 * loop runs n/2 times and maxHeapify runs O(logn) times -> O(nlogn)
	 * but each step is fewer operations.
	 * one level above leaves have n/4 nodes with 1 or 2 swaps at the max - C operations
	 * two levels above leaves have n/8 nodes with 2C operations and so on
	 * Root has 1 node with max logn opeations
	 * adding: (1c)n/4 + (2c)n/8 + .... + 1(logn)
	 * = c(2^k)(1/2^0 + 1/2^1 + 1/2^2 .....k+1/2^k) (2^k=logn)
	 * = c1(2^k)(c2) => constant*n = O(n) time 
	 */
	private static void buildHeap(int[] input, int endIndex) {
		int size = endIndex+1;
		for(int parentIndex = size/2-1; parentIndex>=0; parentIndex--){
			maxHeapify(input, parentIndex, size);
		}
	}	

	/*
	 * Due to the assumption that maxHeapifyGets a heap that satisfies mhp,
	 * each call does a max of logn swaps (height of tree)
	 */
    private static void maxHeapify(int[] input, int parentIndex, int size) {
    	int leftIndex = 2*parentIndex + 1;
    	int rightIndex = 2*parentIndex + 2;
    	if(leftIndex < size && input[parentIndex] < input[leftIndex]){
    		swap(input, parentIndex, leftIndex);
    		maxHeapify(input, leftIndex, size);
    	} 
    	if(rightIndex < size && input[parentIndex] < input[rightIndex]){
    		swap(input, parentIndex, rightIndex);
    		maxHeapify(input, rightIndex, size);
    	}
	}

	private static void swap(int[] input, int i, int j) {
		int temp = input[j];
		input[j] = input[i];
		input[i] = temp;
	}

	/*
	 * As we swap the root to the last element, only one violation to mhp
	 * So enough to call maxHeapify on the rest of the elemets after decrementing the array size
	 * buildHeap = O(n)
	 * each call to maxHeapify = O(logn)
	 * Total time for heapSort = O(nlogn)
	 */
	private static void heapSort(int[] input) {
		int count = 1;
		HeapSort.buildHeap(input, input.length-count);
		while(count<=input.length){
			swap(input, 0, input.length-count);
			HeapSort.maxHeapify(input, 0, input.length-count);
			count++;
		}
	}


	public static void main( String[] args ){
    	int[] input = {4,5,2,11,65,91,34,1,11};
    	HeapSort.heapSort(input);
    	for(int i: input){
    		System.out.print(i + ",");
    	}
    }
}
