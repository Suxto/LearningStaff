package Ch10;

import java.time.*;

import static java.lang.System.out;

public class P10_1 {
    public static void main(String[] args) {
        long se = System.currentTimeMillis();
        Time time = new Time(se);

    }
}

class Time {
    int hour, minute, second;

    Time() {
        LocalTime localTime = LocalTime.now();
        hour = localTime.getHour();
        minute = localTime.getMinute();
        second = localTime.getSecond();
    }

    Time(long es) {
        Instant instant = Instant.ofEpochMilli(es);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        hour = localDateTime.getHour();
        minute = localDateTime.getMinute();
        second = localDateTime.getSecond();
    }

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}