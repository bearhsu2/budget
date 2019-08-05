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

    long overlappingDays(Period period) {


        if (period.end.isBefore(getStart()) || period.start.isAfter(getEnd())) {
            return 0L;
        }

        LocalDate realStart = period.start.isBefore(this.start)
                ? this.start
                : period.start;

        LocalDate realEnd = period.end.isAfter(this.end)
                ? this.end
                : period.end;

        long days = DAYS.between(realStart, realEnd) + 1;

        return days;
    }
}
