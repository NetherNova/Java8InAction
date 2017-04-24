package chapter10;

import java.util.Optional;

public class Person {
	
	private Optional<Car> car;
	private int age;
	
	public Person(Optional<Car> car, int age) {
		this.car = car;
		this.age = age;
	}
	
	public Optional<Car> getCar() {
		return this.car;
	}
	
	public int getAge() {
		return this.age;
	}

}
