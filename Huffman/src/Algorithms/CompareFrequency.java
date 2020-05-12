package Algorithms;

import java.util.Comparator;

public class CompareFrequency implements Comparator<HuffmanLeafNode>{

	@Override
	public int compare(HuffmanLeafNode node1, HuffmanLeafNode node2) {
		return node1.frequency - node2.frequency;
	}
	
}
