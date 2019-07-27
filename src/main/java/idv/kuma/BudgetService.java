package idv.kuma;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Hello world!
 */
public class BudgetService {
    private static
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");;

    private BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        if (end.isBefore(start)) return 0;

        List<Budget> budgets = budgetRepo.getAll();

        double sum = 0D;

        for (Budget budget : budgets) {

            YearMonth budgetYearMonth = YearMonth.parse(budget.getYearMonth(), formatter);
            YearMonth startYearMonth = YearMonth.from(start);
            YearMonth endYearMonth = YearMonth.from(end);

            sum += budget.getAmount() * getDays(start, end, budgetYearMonth, startYearMonth, endYearMonth) / budgetYearMonth.lengthOfMonth();
        }


        return sum;

    }

    private long getDays(LocalDate start, LocalDate end, YearMonth budgetYearMonth, YearMonth startYearMonth, YearMonth endYearMonth) {
        long days;
        if (budgetWrappedByStartEnd(budgetYearMonth, startYearMonth, endYearMonth)) {
            days = budgetYearMonth.lengthOfMonth();
        } else if (startEndBothThisMonth(budgetYearMonth, startYearMonth, endYearMonth)) {
            days = DAYS.between(start, end) + 1;

        } else if (onlyEndInThisMonth(budgetYearMonth, startYearMonth, endYearMonth)) {

            days = DAYS.between(budgetYearMonth.atDay(1), end) + 1;

        } else { // startYearMonth.equals(budgetYearMonth) && endYearMonth.isAfter(budgetYearMonth)
            days = DAYS.between(start, budgetYearMonth.atEndOfMonth()) + 1;

        }
        return days;
    }

    private boolean onlyEndInThisMonth(YearMonth budgetYearMonth, YearMonth startYearMonth, YearMonth endYearMonth) {
        return startYearMonth.isBefore(budgetYearMonth) && endYearMonth.equals(budgetYearMonth);
    }

    private boolean startEndBothThisMonth(YearMonth budgetYearMonth, YearMonth startYearMonth, YearMonth endYearMonth) {
        return startYearMonth.equals(budgetYearMonth) && endYearMonth.equals(budgetYearMonth);
    }

    private boolean budgetWrappedByStartEnd(YearMonth budgetYearMonth, YearMonth startYearMonth, YearMonth endYearMonth) {
        return startYearMonth.isBefore(budgetYearMonth) && endYearMonth.isAfter(budgetYearMonth);
    }

}
