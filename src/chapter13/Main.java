package chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static List<List<Integer>> subsets(List<Integer> list) {
		if(list.isEmpty()) {
			List<List<Integer>> ans = new ArrayList<>();
			ans.add(Collections.emptyList());
			return ans;
		}
		Integer first = list.get(0);
		List<Integer> rest = list.subList(1, list.size());
		List<List<Integer>> subans = subsets(rest);
		List<List<Integer>> subans2 = insertAll(first, subans);
		return concat(subans, subans2);
	}
	
	public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
		List<List<Integer>> result = new ArrayList<>();
		for(List<Integer> list : subans) {
			List<Integer> copyList = new ArrayList<>();
			copyList.add(first);
			copyList.addAll(list);
			result.add(copyList);
		}
		return result;
	}
	
	public static List<List<Integer>> concat(List<List<Integer>> first, List<List<Integer>> second) {
		List<List<Integer>> result = new ArrayList<>(first);
		result.addAll(second);
		return result;
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(new Integer[] {1,2,3,4});
		System.out.println(subsets(list));
		
	}

}
