package idv.kuma;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private String yearMonth;
    private int amount;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }


    private LocalDate getFirstDay() {
        YearMonth yearMonth = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return yearMonth.atDay(1);
    }

    private LocalDate getLastDay() {
        YearMonth yearMonth = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return yearMonth.atEndOfMonth();


    }

    private Period toPeriod() {
        return new Period(getFirstDay(), getLastDay());
    }

    private int getNumberOfDays() {
        YearMonth yearMonth = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return yearMonth.lengthOfMonth();
    }

    double getDailyAmount() {
        return amount / (double) getNumberOfDays();
    }

    double getTotalAmount(Period period) {
        return period.overlapDays(toPeriod()) * getDailyAmount();
    }
}
