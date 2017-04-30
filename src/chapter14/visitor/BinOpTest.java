package chapter14.visitor;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinOpTest {

	@Test
	public void test() {
		Number ten = new Number("ten", 10);
		Number three = new Number("three", 3);
		Number zero = new Number("zero", 0);
		
		BinOp plus = new BinOp("+", ten, zero);
		BinOp plus2 = new BinOp("+", plus, three);
		BinOp plus3 = new BinOp("+", plus2, plus2);
		SimplifyExprVisitor v = new SimplifyExprVisitor();
		System.out.println(plus3);
		System.out.println(plus3.accept(v));
	}
}
