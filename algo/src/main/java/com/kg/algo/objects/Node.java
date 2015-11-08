package com.kg.algo.objects;

public class Node {
	
	private Integer startTime;
	private Integer endTime;
	private Integer weight;
	
	public Node(int startTime, int endTime, int weight) {
		setStartTime(startTime);
		setEndTime(endTime);
		setWeight(weight);
	}
	public Integer getStartTime() {
		return startTime;
	}
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
	public Integer getEndTime() {
		return endTime;
	}
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null && !(obj instanceof Node)){
			return false;
		}
		return this.toString().equals(obj);
	}
	@Override
	public String toString() {
		return getStartTime() + "::" + getEndTime() + "::" + getWeight();
	}
	
}
