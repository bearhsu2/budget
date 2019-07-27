package idv.kuma;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        List<Budget> budgets = budgetRepo.getAll();

        double sum = 0D;

        for (Budget budget : budgets) {

            formatter = DateTimeFormatter.ofPattern("uuuuMM");
            YearMonth budgetYearMonth = YearMonth.parse(budget.getYearMonth(), formatter);

            YearMonth startYearMonth = YearMonth.from(start);
            YearMonth endYearMonth = YearMonth.from(end);

            if (budgetYearMonth.equals(startYearMonth) || budgetYearMonth.equals(endYearMonth)) {
                sum += budget.getAmount();
            }

        }


        return sum;

    }

    private String dateToString(LocalDate start) {
        String monthStr = String.format("%02d", start.getMonthValue());

        return "" + start.getYear() + monthStr;
    }
}
