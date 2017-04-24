package chapter10;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		Optional<Insurance> ins = Optional.of(new Insurance("HUK"));
		Car car = new Car(ins);
		Person p = new Person(Optional.of(car), 25);
		String name = getCarCompanyInsurance(Optional.of(p), 25);
		System.out.println(name);
	}
	
	public static String getCarCompanyInsurance(Optional<Person> person) {
		return person.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse("Unknown");
	}
	
	public static String getCarCompanyInsurance(Optional<Person> person, int minAge) {
		return person.filter(p -> (p.getAge() > minAge))
			.flatMap(Person::getCar)
			.flatMap(Car::getInsurance)
			.map(Insurance::getName)
			.orElse("Unknown");
	}

}
