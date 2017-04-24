package chapter11;

import java.util.Random;

public class DelayHelper {
	
	public static void fixedDelay(Thread t, long millis) {
		try {
			t.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void randomDelay(Thread t, long max) {
		Random random = new Random();
		long randomDelay = 500 + random.nextInt((int) max); //safe?
		try {
			t.sleep(randomDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
