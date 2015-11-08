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
		int[] backTrackArray = new int[numOfSchedules];
		backTrackArray[0] = -1;
		int lastChosenSchedule = -1;
		for(int i=1; i<numOfSchedules; i++){
			if(prevBestInterval[i]>=0){
				maxWeight[i] = Math.max(weights[i] + maxWeight[prevBestInterval[i]], maxWeight[i-1]);
			}else{
				maxWeight[i] = Math.max(weights[i], maxWeight[i-1]);
			}
			
			if(prevBestInterval[i] >= 0 && ((weights[i] + maxWeight[prevBestInterval[i]]) > maxWeight[i-1])){
				lastChosenSchedule = i;
				backTrackArray[i] = prevBestInterval[i];
			}else if(weights[i] > maxWeight[i-1]){
				lastChosenSchedule = i;
				backTrackArray[i] = -1;
			}else{
				backTrackArray[i] = -1;
			}
		}
		
		List<Integer> schedule = new ArrayList<Integer>();
		while(lastChosenSchedule != -1){
			schedule.add(lastChosenSchedule);
			//System.out.println(lastChosenSchedule);
			lastChosenSchedule = backTrackArray[lastChosenSchedule];
		}
		return schedule;
	}
	
	
	public static void main(String args[]){
		int[] startTimes = {1, 2, 5, 2, 9, 10};
		int[] endTimes = {4, 6, 7, 11, 12, 13};
		int[] weights = {2, 4, 4, 7, 2, 1};
		System.out.println(getBestSchedule(startTimes, endTimes, weights));
	}
}
