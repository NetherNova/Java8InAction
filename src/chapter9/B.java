package chapter9;

public interface B extends A {
	
	/*
	 * (non-Javadoc)
	 * @see chapter9.A#getNumber()
	 */
	default Integer getNumber() {
		return 41;
	}
}
