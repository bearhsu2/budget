package idv.kuma;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {

    LocalDate start;
    LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    long getEffectiveDays(Period another) {

        if (end.isBefore(another.start) || start.isAfter(another.end)) {
            return 0;
        }

        LocalDate realStart = start.isBefore(another.start)
                ? another.start
                : start;

        LocalDate realEnd = end.isAfter(another.end)
                ? another.end
                : end;

        return DAYS.between(realStart, realEnd) + 1;
    }

}
