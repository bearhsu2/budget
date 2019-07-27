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

    BudgetRepo budgetRepo;
    private DateTimeFormatter formatter;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        if (end.isBefore(start)) return 0;

        List<Budget> budgets = budgetRepo.getAll();

        double sum = 0D;

        for (Budget budget : budgets) {

            formatter = DateTimeFormatter.ofPattern("uuuuMM");
            YearMonth budgetYearMonth = YearMonth.parse(budget.getYearMonth(), formatter);

            YearMonth startYearMonth = YearMonth.from(start);
            YearMonth endYearMonth = YearMonth.from(end);


            long days = 0L;
            long total = 0L;

            if (startYearMonth.isBefore(budgetYearMonth) && endYearMonth.isAfter(budgetYearMonth)) {
                days = budgetYearMonth.lengthOfMonth();
                total = budgetYearMonth.lengthOfMonth();

            } else if (startYearMonth.equals(budgetYearMonth) && endYearMonth.equals(budgetYearMonth)) {
                days = DAYS.between(start, end) + 1;
                total = budgetYearMonth.lengthOfMonth();

            } else if (startYearMonth.isBefore(budgetYearMonth) && endYearMonth.equals(budgetYearMonth)) {

                days = DAYS.between(budgetYearMonth.atDay(1), end) + 1;
                total = budgetYearMonth.lengthOfMonth();

            } else { // startYearMonth.equals(budgetYearMonth) && endYearMonth.isAfter(budgetYearMonth)
                days = DAYS.between(start, budgetYearMonth.atEndOfMonth()) + 1;
                total = budgetYearMonth.lengthOfMonth();

            }

            sum += budget.getAmount() * days / total;
        }


        return sum;

    }

}
