package idv.kuma;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");;

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

    double getDailyAmount() {

        YearMonth yearMonth = YearMonth.parse(this.yearMonth, formatter);
        return getAmount() / yearMonth.lengthOfMonth();
    }
}
