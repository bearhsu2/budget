package idv.kuma;

import java.time.LocalDate;
import java.util.List;


public class BudgetService {


    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            return 0D;
        }

        Period period = new Period(start, end);

        List<Budget> budgets = budgetRepo.getAll();

        double total = 0D;
        for (Budget budget : budgets) {

            long days = period.getEffectiveDays(budget.toPeriod());

            double dailyAmount = budget.getDailyAmount();

            total += days * dailyAmount;
        }

        return total;


    }

}
