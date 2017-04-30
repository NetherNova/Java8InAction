package chapter14;

import org.junit.Test;
import java.lang.Math;

public class TreeTester {

	@Test
	public void test() {
		Tree t = new Tree("abb", 12, null, null);
		String test = "a";
		for (int i = 0; i < 2000000; i++) {
			t = TreeProcessor.update1(String.valueOf(i), 2, t);
		}
		long start = System.currentTimeMillis();
		System.out.println("Searching for " + test);
		System.out.println(TreeProcessor.lookup(test, 0, t));
		long diff = System.currentTimeMillis() - start;
		System.out.println(diff + " milliseconds");
	}
}
