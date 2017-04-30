package chapter14.visitor;

public class BinOp extends Expr {
	Expr left;
	Expr right;
	public BinOp(String name, Expr left, Expr right) {
		this.name = name;
		this.left = left;
		this.right = right;
	}
	@Override
	public Expr accept(SimplifyExprVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return name + "(" + left.toString() + "," + right.toString() + ")";
	}
}
