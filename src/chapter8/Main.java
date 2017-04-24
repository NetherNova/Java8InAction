package chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		System.out.println(ProductFactory.getInstance().createProduct("test", 12, 12, "me").getClass().getName());
		System.out.println(ProductFactory.getInstance().createProduct("normal", 12, 12, "me").getClass().getName());
		
		List<Integer> numbers = Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9,10});
		numbers.stream()
				.peek(x -> System.out.println(x))
				.filter(i -> i % 2 == 0)
				.map(i -> i * i)
				.peek(x -> System.out.println(x))
				.collect(Collectors.toList());

	}

}
