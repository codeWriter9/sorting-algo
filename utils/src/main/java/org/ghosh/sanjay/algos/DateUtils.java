package org.ghosh.sanjay.algos;

import static java.time.Duration.between;
import static java.time.temporal.ChronoField.CLOCK_HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * 
 * 
 * @author Sanjay Ghosh
 *
 */
public class DateUtils {

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static LocalDate today() {
		return LocalDate.now();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static LocalDateTime todayAtMidNight() {
		return LocalDateTime.now().with(CLOCK_HOUR_OF_DAY, 24).with(MINUTE_OF_HOUR, 0).with(SECOND_OF_MINUTE, 0);
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static Instant now() {
		return Instant.now();
	}

	/**
	 * 
	 * 
	 * 
	 * @param now
	 * @return
	 */
	public static java.util.Date legacyNow(Instant now) {
		return java.util.Date.from(now);
	}

	/**
	 * 
	 * 
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String betweenInMillis(Instant start, Instant end) {
		return between(start, end).toMillis() + "";
	}

	/**
	 * 
	 * 
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String betweenInNanos(Instant start, Instant end) {
		return between(start, end).toNanos() + "";
	}

	/**
	 * 
	 * 
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String betweenInMinutes(Instant start, Instant end) {
		return between(start, end).toMinutes() + "";
	}
}