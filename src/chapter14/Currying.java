package chapter14;

import java.util.function.DoubleUnaryOperator;

public class Currying {
	
	public static DoubleUnaryOperator curriedConverter(double f, double b) {
		return (double x) -> x * f + b;
	}

}
