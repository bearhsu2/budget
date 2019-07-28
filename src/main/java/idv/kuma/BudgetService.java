package idv.kuma;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Hello world!
 */
public class BudgetService {

    private BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        List<Budget> budgets = this.budgetRepo.getAll();

        if (budgets.size() == 0) {
            return 0;
        }

        long days = DAYS.between(start, end) + 1;
        return days;

    }
}
