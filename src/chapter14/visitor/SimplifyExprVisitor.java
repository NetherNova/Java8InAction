package chapter14.visitor;

public class SimplifyExprVisitor {

	public Expr visit(BinOp e) {
		if(e.getName().equals("+") && e.right instanceof Number) {
			if (((Number) e.right).getVal() == 0) {
				if (e.left instanceof BinOp) {
					return visit((BinOp) e.left);
				}
				else {
					return e.left;
				}
			}
		}
		
		if(e.getName().equals("+") && e.left instanceof Number) {
			if (((Number) e.left).getVal() == 0) {
				if (e.right instanceof BinOp) {
					return visit((BinOp) e.right);
				}
				else {
					return e.right;
				}
			}
		}
		if (e.left instanceof BinOp) {
			e.left = visit((BinOp) e.left);
		}
		
		if (e.right instanceof BinOp) {
			e.right = visit((BinOp) e.right);
		}
		return e;
	}
}
