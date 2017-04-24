package chapter9;

public interface A {
	
	/*
	 * Default method as source for inheritance
	 */
	default Integer getNumber() {
		return 42;
	}

}
