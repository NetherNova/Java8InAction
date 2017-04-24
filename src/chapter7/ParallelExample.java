package chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelExample {

	public static void main(String[] args) {
		long n = 10_000_000;
		//measurePerformance(ParallelExample::sumParallel, n);
		measurePerformance(ParallelExample::sum, n);
		measurePerformance(ParallelExample::nativeSum, n);
		measurePerformance(ParallelExample::rangeParallelSum, n);
	}
	
	public static void measurePerformance(Function<Long, Long> func, long n) {
		long fastest = Long.MAX_VALUE;
		long result = 0;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			result = func.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if(duration < fastest) {
				fastest = duration;
			}
		}
		System.out.println(result);
		System.out.println("Fastest execution over 10 runs = " + fastest + " milliseconds");
	}
	
	public static long sumParallel(long n) {
		return Stream.iterate(1L, x -> x + 1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
	}
	
	public static long sum(long n) {
		return Stream.iterate(1L, x -> x + 1)
				.limit(n)
				.reduce(0L, Long::sum);
	}
	
	public static long nativeSum(long n) {
		long result = 0;
		for (long i = 1; i <= n; i++) {
			result += i;
		}
		return result;
	}
	
	public static long rangeParallelSum(long n) {
		return LongStream.rangeClosed(1, n)
				.parallel()
				.reduce(0L, Long::sum);
	}
}
