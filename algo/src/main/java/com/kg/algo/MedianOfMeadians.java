package com.kg.algo;

public class MedianOfMeadians {

	private static int findKthLargest(int[] input, int startIndex, int endIndex, int k) {

		int size = endIndex - startIndex + 1;
		//termination for the medianOfMedians recursion;
		if(size<=5){ 
			return bubbleSort(input, startIndex, endIndex, k);
		}else{
			//We need upper bound (case say total numbers = 23, 4 medians and 1 median for last 3
			int[] medians = new int[size/5 + 1];
			int medianIndex = 0;
			for(int i=startIndex; i<size; i=i+5){
				medians[medianIndex] = getMedian(input, i);
				medianIndex++;
			}
			int medianOfMedians = findKthLargest(medians, 0, medians.length-1, medians.length/2);
			
			int pivotIndex = partition(input, medianOfMedians, startIndex, endIndex);
			if(k == endIndex - pivotIndex + 1){
				return medianOfMedians;
			}else if(k < (endIndex-pivotIndex + 1)){
				return findKthLargest(input, pivotIndex+1, endIndex, k);
			}else{
				return findKthLargest(input, startIndex, pivotIndex-1, k - (endIndex-pivotIndex + 1)); 
			}
		}
	}
	
	/*
	 * Standard Partition algorithm
	 * first swap the pivot with the end of the array
	 * start i,j at 0 and try pushing the values lesser then the pivot to the left while incrementing j
	 * increment i when swapping (i tries not to be the smaller element
	 * finally swap pivot with i and return index of pivot which is i
	 */
	private static int partition(int[] input, int pivotValue, int startIndex, int endIndex) {
		for(int i=startIndex; i< endIndex-startIndex+1 ; i++){
			if(input[i]==pivotValue){
				swap(input,i, endIndex);
			}
		}
		int i = startIndex;
		int j = startIndex;
		while(j<endIndex){
			if(input[j] < input[endIndex]){
				swap(input, i, j);
				i++;
			}
			j++;
		}
		swap(input, i, endIndex);
		return i;
	}

	private static int getMedian(int[] input, int startIndex) {
		int endIndex = startIndex + 4;
		if(input.length-1 > endIndex){
			return bubbleSort(input, startIndex, endIndex, (endIndex - startIndex)/2);
		}else{
			return bubbleSort(input, startIndex, input.length-1, (input.length-1 - startIndex)/2);
		}
	}

	private static int bubbleSort(int[] input, int startIndex, int endIndex, int k) {
		for(int i = startIndex; i<endIndex; i++){
			for(int j=i+1; j<=endIndex; j++){
				if(input[i]>input[j]){
					swap(input, i, j);
				}
			}
		}
		return input[endIndex-k+1];
	}

	private static void swap(int[] input, int i, int j){
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static void main(String[] args){
		int[] input = {4,5,2,11,65,91,34,1};
		int k = 2;
		System.out.println(MedianOfMeadians.findKthLargest(input, 0, input.length-1, k));
	}
}
