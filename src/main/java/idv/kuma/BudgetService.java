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
        Period period = new Period(start, end);


        List<Budget> budgets = this.budgetRepo.getAll();

        if (budgets.size() == 0) {
            return 0;
        }
        Budget budget = budgets.get(0);
        long days = period.overlapDays(new Period(budget.getFirstDay(), budget.getLastDay()));

        return days;

    }

}
