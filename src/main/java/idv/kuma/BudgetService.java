package idv.kuma;

import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 */
public class BudgetService {

    BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        List<Budget> budgets = budgetRepo.getAll();

        for (Budget budget : budgets) {

            String dateString = dateToString(start);


            if (budget.getYearMonth().equals(dateString)) {
                return budget.getAmount();
            }

        }


        return 0;

    }

    private String dateToString(LocalDate start) {
        String monthStr = String.format("%02d", start.getMonthValue());

        return "" + start.getYear() + monthStr;
    }
}
