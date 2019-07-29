package idv.kuma;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

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

    long getDays() {
        return DAYS.between(start, end) + 1;
    }

    long overlapDays(Period another) {


        if (end.isBefore(another.getStart())) {
            return 0;
        }

        if (start.isAfter(another.getEnd())) {
            return 0;
        }

        LocalDate overlapStart = start.isBefore(another.getStart())
                ? another.getStart()
                : start;


        LocalDate overlapEnd = end.isAfter(another.getEnd())
                ? another.getEnd()
                : end;


        return DAYS.between(overlapStart, overlapEnd) + 1;
    }
}
