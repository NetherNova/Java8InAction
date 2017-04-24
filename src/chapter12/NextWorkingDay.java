package chapter12;

import java.time.DayOfWeek;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		if(dow == DayOfWeek.FRIDAY) {
			return temporal.plus(Period.ofDays(3));
		}
		else if (dow == DayOfWeek.SATURDAY) {
			return temporal.plus(Period.ofDays(2));
		}
		return temporal.plus(Period.ofDays(1));
	}
}
