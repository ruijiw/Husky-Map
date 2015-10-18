package hw7;

import hw5.Edge;

import java.util.*;

public class ListComparator <N extends Comparable<N>> implements Comparator<List<Edge<N, Double>>>{
	/**
	 * Compare two lists of Edges according to the sum of weights
	 * 
	 * @param list1, the first list of edges to compare
	 * @param list2, the second list of edges to compare
	 * @return return positive number if the first list is 
	 * considered larger than the second list and vice versa.
	 * If the sums of weights are the same, compare them by the 
	 * alphabetical order of their start nodes.
	 */
	@Override
	public int compare(List<Edge<N, Double>> list1, List<Edge<N, Double>> list2) {
		Double sum1 = 0.0;
		Double sum2 = 0.0;
		for (Edge<N, Double> edge1 : list1) {
			sum1 += edge1.getLabel();
		}
		for (Edge<N, Double> edge2 : list2) {
			sum2 += edge2.getLabel();
		}
		int diff = sum1.compareTo(sum2);
		if (diff != 0) {
			return diff;
		} else {
			for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
				N start1 = list1.get(i).getParent().getData();
				N start2 = list2.get(i).getParent().getData();
				if (start1.compareTo(start2) != 0) {
					return start1.compareTo(start2);
				}
			}
			return list1.size() - list2.size();
		}
	}
}
