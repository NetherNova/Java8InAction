package chapter14;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList<Integer> l = new LinkedList<>(5, new LinkedList<>(20, new Empty()));
		
		LazyList<Integer> lali = LazyList.from(2);
		int two = LazyList.primes(lali).head();
		int three = LazyList.primes(lali).tail().head();
		int five = LazyList.primes(lali).tail().tail().head();
		
		System.out.println(two + " " + three + " " + five);
	}

}
