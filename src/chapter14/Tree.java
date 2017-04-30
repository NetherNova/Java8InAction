package chapter14;

public class Tree {
	
	String key;
	int val;
	Tree left;
	Tree right;
	public Tree(String k, int v, Tree l, Tree r) {
		key = k; val = v; left = l; right = r;
	}
}

class TreeProcessor {
	public static int lookup(String k, int defaultval, Tree t) {
		if (t == null) return defaultval;
		if (k.equals(t.key)) return t.val;
		return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
	}
	
	public static void update(String k, int v, Tree t) {
		if (t == null) {}
		else if (t.key == k) {t.val = v;}
		else {
			update(k, v, k.compareTo(t.key) < 0 ? t.left : t.right);
		}
	}
	
	public static Tree update1(String k, int v, Tree t) {
		if (t == null) {
			t = new Tree(k, v, null, null);
		}
		else if (t.key == k) {
			t.val = v; 
		}
		else if (k.compareTo(t.key) < 0) {
			t.left = update1(k, v, t.left);
		}
		else {
			t.right = update1(k, v, t.right);
		}
		return t;
	}
	
	public static Tree fupdate(String k, int newval, Tree t) {
		return (t == null) ?
				new Tree(k, newval, null, null) :
					k.equals(t.key) ?
							new Tree(k, newval, t.left, t.right) :
								k.compareTo(t.key) < 0 ? 
										new Tree(k, newval, fupdate(k, newval, t.left), t.right) :
											new Tree(k, newval, t.left, fupdate(k, newval, t.right));
	}
}