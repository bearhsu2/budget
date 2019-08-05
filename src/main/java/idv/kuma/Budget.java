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

    public LocalDate getFirstDay() {
        YearMonth ym = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return ym.atDay(1);

    }

    public LocalDate getLastDay() {
        YearMonth ym = YearMonth.parse(this.yearMonth, DateTimeFormatter.ofPattern("uuuuMM"));
        return ym.atEndOfMonth();

    }
}
