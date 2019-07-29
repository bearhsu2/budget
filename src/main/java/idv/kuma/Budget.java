package idv.kuma;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private String yearMonth;
    private int amount;

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public LocalDate getFirstDay() {
        YearMonth yearMonth = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return yearMonth.atDay(1);
    }

    public LocalDate getLastDay() {
        YearMonth yearMonth = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return yearMonth.atEndOfMonth();


    }

    public Period toPeriod() {
        return new Period(getFirstDay(), getLastDay());
    }

    public int getNumberOfDays() {
        YearMonth yearMonth = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return yearMonth.lengthOfMonth();
    }

    int getDailyAmount() {
        return amount / getNumberOfDays();
    }
}
