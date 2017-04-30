package chapter14;

import java.util.function.Predicate;
import java.util.function.Supplier;

interface MyList<T> {
	T head();
	MyList<T> tail();
	
	default boolean isEmpty() {
		return true;
	}
	
	default MyList<T> filter(Predicate<T> p) {
		return isEmpty() ?
				this :
					p.test(head()) ?
							new LazyList<T>(head(), () -> tail().filter(p)) :
								tail().filter(p);
	}
}

public class LinkedList<T> implements MyList<T> {
	private final T head;
	private final MyList<T> tail;

	public LinkedList(T head, MyList<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public T head() {
		return head;
	}

	@Override
	public MyList<T> tail() {
		return tail;
	}
	
	public boolean isEmpty() {
		return false;
	}

}

class LazyList<T> implements MyList<T> {
	final T head;
	final Supplier<MyList<T>> tail;
	public LazyList(T head, Supplier<MyList<T>> tail) {
		this.head = head;
		this.tail = tail;
	}
	@Override
	public T head() {
		return head;
	}
	@Override
	public MyList<T> tail() {
		return tail.get();
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public static LazyList<Integer> from(int n) {
		return new LazyList(n, () -> from(n+1));
	}
	
	public static MyList<Integer> primes(MyList<Integer> numbers) {
		return new LazyList(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
	}
}

class Empty<T> implements MyList<T> {

	@Override
	public T head() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MyList<T> tail() {
		throw new UnsupportedOperationException();
	}
}
