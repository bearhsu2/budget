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


        if (end.isBefore(another.getStart()) || start.isAfter(another.getEnd())) {
            return 0L;
        }

        LocalDate realStart = start.isBefore(another.start)
                ? another.start
                : start;

        long days = DAYS.between(realStart, end) + 1;

        return days;
    }
}
