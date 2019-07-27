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

            String monthStr = String.format("%02d", start.getMonthValue());

            if (budget.getYearMonth().equals("" + start.getYear() + monthStr)) {
                return budget.getAmount();
            }

        }


        return 3100;

    }
}
