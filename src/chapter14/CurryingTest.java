package chapter14;

import static org.junit.Assert.*;

import java.util.function.DoubleUnaryOperator;

import org.junit.Test;

public class CurryingTest {

	@Test
	public void test() {
		DoubleUnaryOperator fahrenheitConv = Currying.curriedConverter(9.0/5,  32);
		assertEquals(fahrenheitConv.applyAsDouble(25), (25* (9.0/5)+32), 1e-99);
	}
}
