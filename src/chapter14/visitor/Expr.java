package chapter14.visitor;

public abstract class Expr {
	protected String name;
	
	abstract public Expr accept(SimplifyExprVisitor v);

	public String getName() {
		return name;
	}
}
