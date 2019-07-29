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

        if (budgets.size() == 0) {
            return 0;
        }

        double totalAmount = 0D;
        for (Budget budget : budgets) {

            totalAmount += budget.getTotalAmount(period);

        }
        return totalAmount;

    }

}
