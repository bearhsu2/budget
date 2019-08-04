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

    long getEffectiveDays(Budget budget) {

        long days;
        if (getStart().isBefore(budget.getFirstDay())) {
            days = DAYS.between(budget.getFirstDay(), getEnd()) + 1;
        } else {
            days = DAYS.between(getStart(), getEnd()) + 1;
        }
        return days;
    }
}
