package idv.kuma;

import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 */
public class BudgetService {

    private BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            return 0;
        }

        Period period = new Period(start, end);

        List<Budget> budgets = this.budgetRepo.getAll();

        if (budgets.isEmpty()) {
            return 0;
        }

        return budgets
                .stream()
                .mapToDouble(budget -> budget.getTotalAmount(period))
                .sum();
    }

}
