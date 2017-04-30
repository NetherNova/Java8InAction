package chapter14.visitor;

public class Number extends Expr {
	private int val;
	
	public int getVal() {
		return val;
	}

	public Number(String name, int val) {
		this.name = name;
		this.val = val;
	}

	@Override
	public Expr accept(SimplifyExprVisitor v) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return String.valueOf(val);
	}
}
