package com.kg.algo.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NodeComparator implements Comparator<Node>{

	public int compare(Node o1, Node o2) {
		return o1.getEndTime().compareTo(o2.getEndTime());
	}
	
	public static void main(String[] args){
		List<Node> nodes = new ArrayList<Node>();
		Node node1 = new Node(1,2,4);
		nodes.add(node1);
		Node node2 = new Node(1,6,4);
		nodes.add(node2);
		Node node3 = new Node(1,3,4);
		nodes.add(node3);
		Node node4 = new Node(1,7,4);
		nodes.add(node4);
		NodeComparator nodeComparator = new NodeComparator();
		Collections.sort(nodes, nodeComparator);
		System.out.println(nodes);
	}

}
