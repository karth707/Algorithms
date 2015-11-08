package com.kg.algo;

import java.util.ArrayList;
import java.util.List;

public class WeightedIntervalScheduling {

	
	public static List<Integer> getBestSchedule(int[] startTimes, int[] endTimes, int[] weights){ 
		// considering input is in increasing order of end times
		// weights array is vi array of weights
		if(startTimes.length!=endTimes.length && endTimes.length!=weights.length) return null;
		int numOfSchedules = startTimes.length;
		
		int[] prevBestInterval = new int[numOfSchedules];
		prevBestInterval[0] = -1;
		for(int i=1; i<numOfSchedules; i++){
			int prevIndex = -1;
			for(int j=i-1; j>=0; j--){
				if(startTimes[i] >= endTimes[j]){
					prevIndex = j;
					break;
				}
			}
			prevBestInterval[i] =  prevIndex;
		} // previous non collapsing intervals array pi is created.
		
		int[] maxWeight = new int[numOfSchedules]; // m[] array
		
		maxWeight[0] = weights[0];
		int lastChosenSchedule = -1;
		for(int i=1; i<numOfSchedules; i++){
			int weight1;
			if(prevBestInterval[i]>=0){
				weight1 = weights[i] + maxWeight[prevBestInterval[i]];
			}else{
				weight1 = weights[i];
			}
			int weight2 = maxWeight[i-1];
			if(weight1 > weight2){
				lastChosenSchedule = i;
				maxWeight[i] = weight1;
			}else{
				maxWeight[i] = weight2;
			}
		}
		
		List<Integer> schedule = new ArrayList<Integer>();
		findSolution(lastChosenSchedule, weights, prevBestInterval, maxWeight, schedule);
		
		return schedule;
	}
	
	private static void findSolution(int lastChosenSchedule, int[] weights,
			int[] prevBestInterval, int[] maxWeight, List<Integer> schedule) {
		
		if(lastChosenSchedule==0){
			schedule.add(lastChosenSchedule);
			return;
		}

		int flag = 0;
		int weight1;
		if(prevBestInterval[lastChosenSchedule]!=-1){
			weight1 = weights[lastChosenSchedule] + maxWeight[prevBestInterval[lastChosenSchedule]];
		}else{
			weight1 = weights[lastChosenSchedule];
			flag = 1;
		}
		if(weight1 > maxWeight[lastChosenSchedule-1]){
			schedule.add(lastChosenSchedule);
			if(flag==1) return;
			findSolution(prevBestInterval[lastChosenSchedule], weights, prevBestInterval, maxWeight, schedule);
		}else{
			findSolution(lastChosenSchedule-1, weights, prevBestInterval, maxWeight, schedule);
		}
	}


	public static void main(String args[]){
//		int[] startTimes = {1, 2, 5, 2, 9, 10};
//		int[] endTimes = {4, 6, 7, 11, 12, 13};
//		int[] weights = {2, 4, 4, 7, 2, 1};
		
		int[] startTimes = {1, 2, 6}; 
		int[] endTimes = {3, 5, 8};
		int[] weights = {2, 1, 6};
		
		System.out.println(getBestSchedule(startTimes, endTimes, weights));
	}
}
