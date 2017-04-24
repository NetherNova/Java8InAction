package chapter9;

public class D implements A, B, C {
		
	public static void main(String[] args) {
		System.out.println(new D().getNumber()); //should print 41 (B's behavior)
	}

}
