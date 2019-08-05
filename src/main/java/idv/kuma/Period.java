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

    long overlappingDays(Budget budget) {
        long days;
        if (getEnd().isBefore(budget.getFirstDay())) {
            days = 0L;
        } else {
            days = DAYS.between(getStart(), getEnd()) + 1;
        }
        return days;
    }
}
