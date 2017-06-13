package span.home.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Создано Span 15.05.2017.
 */
public class DateAndTime {
    private Date dateAfter, dateBefore;
    private static DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static DateAndTime getDayInterval() {
        LocalDateTime start = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        Date dateStart = convertLocalDateTimeToDate(start);
        Date dateEnd = convertLocalDateTimeToDate(end);
        return new DateAndTime(dateEnd, dateStart);
    }

    private DateAndTime() {
    }

    private DateAndTime(Date dateBefore, Date dateAfter) {
        this.dateAfter = dateAfter;
        this.dateBefore = dateBefore;
    }

    public static DateAndTime getWeekInterval() {
        LocalDateTime m = LocalDateTime.now().with(DayOfWeek.MONDAY);
        LocalDateTime monday = LocalDateTime.of(m.getYear(), m.getMonth(), m.getDayOfMonth(), 0, 0, 0);
        LocalDateTime s = LocalDateTime.now().with(DayOfWeek.SUNDAY);
        LocalDateTime sunday = LocalDateTime.of(s.getYear(), s.getMonth(), s.getDayOfMonth(), 23, 59, 59);
        Date dateStart = convertLocalDateTimeToDate(monday);
        Date dateEnd = convertLocalDateTimeToDate(sunday);
        return new DateAndTime(dateEnd, dateStart);
    }

    public static DateAndTime getMonthInterval() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime s = now.withDayOfMonth(1);
        LocalDateTime e = now.with(lastDayOfMonth());
        e = LocalDateTime.of(e.getYear(), e.getMonth(), e.getDayOfMonth(), 23, 59, 59);
        s = LocalDateTime.of(s.getYear(), s.getMonth(), s.getDayOfMonth(), 0, 0, 0);
        return new DateAndTime(convertLocalDateTimeToDate(e), convertLocalDateTimeToDate(s));
    }

    public static DateAndTime getYearInterval() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime s = LocalDateTime.of(now.getYear(), 1, 1, 0, 0, 0);
        LocalDateTime e = LocalDateTime.of(now.getYear(), 12, 31, 23, 59, 59);

        return new DateAndTime(convertLocalDateTimeToDate(e), convertLocalDateTimeToDate(s));
    }

    public static DateAndTime getDateForPeriod(String da, String db) {
        da = da + " 00:00:00";
        db = db + " 23:59:59";
        LocalDateTime s = LocalDateTime.parse(da, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        LocalDateTime e = LocalDateTime.parse(db, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        return new DateAndTime(convertLocalDateTimeToDate(e), convertLocalDateTimeToDate(s));
    }

    public static DateAndTime getDateForYearPeriod(int year) {
        String da = "01.01." + year + " 00:00:00";
        String db = "31.12." + year + " 23:59:59";
        LocalDateTime s = LocalDateTime.parse(da, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        LocalDateTime e = LocalDateTime.parse(db, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        return new DateAndTime(convertLocalDateTimeToDate(e), convertLocalDateTimeToDate(s));
    }

    public Date getDateAfter() {
        return dateAfter;
    }

    public Date getDateBefore() {
        return dateBefore;
    }

    @Override
    public String toString() {
        return "DateAndTime{" +
                "dateAfter=" + dateAfter +
                ", dateBefore=" + dateBefore +
                '}';
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return new Date(localDateTime.getYear() - 1900,
                localDateTime.getMonth().getValue() - 1,
                localDateTime.getDayOfMonth(),
                localDateTime.getHour(),
                localDateTime.getMinute(),
                localDateTime.getSecond());
    }

}
