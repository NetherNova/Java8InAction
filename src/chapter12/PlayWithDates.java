package chapter12;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;

public class PlayWithDates {

	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.now().get(ChronoField.DAY_OF_WEEK));
		System.out.println(LocalDate.now().get(ChronoField.YEAR));
		System.out.println(LocalDate.now().isLeapYear());
		System.out.println(LocalDate.now().lengthOfYear());
		
		System.out.println("Birthday:");
		LocalDate birthDay = LocalDate.of(1988, 11, 30);
		System.out.println(birthDay.isLeapYear());
		System.out.println(birthDay.lengthOfYear());

		calculateAge(birthDay, ChronoField.YEAR);
		LocalDate friday = LocalDate.now().plus(Period.ofDays(4));
		LocalDate saturday = friday.plus(Period.ofDays(1));
		System.out.println("Friday: " + friday);
		System.out.println("Saturday: " + saturday);
		TemporalAdjuster nextWorkingDay = new NextWorkingDay();
		LocalDate date = friday.with(nextWorkingDay);
		System.out.println("Friday next: " + date);
		date = saturday.with(nextWorkingDay);
		System.out.println("Saturday next: " + date);
		//Format to German style
		DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		System.out.println(date.format(myFormatter));
	}
	
	public static void calculateAge(LocalDate birthday, TemporalField scale) {
		Period per = Period.between(birthday, LocalDate.now());
		System.out.println("You are: " + per.get(ChronoUnit.YEARS) + " years old!");
	}
}
