package idv.kuma;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private LocalDate start;
    private LocalDate end;

    public Period(LocalDate start, LocalDate end) {

        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    long overlappingDays(Period another) {

        long days;
        if (end.isBefore(another.getStart())) {
            days = 0L;
        } else if (start.isAfter(another.getEnd())) {
            days = 0L;
        } else {
            days = DAYS.between(start, end) + 1;
        }
        return days;
    }
}
