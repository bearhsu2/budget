package idv.kuma;

import java.time.LocalDate;
import java.util.List;


public class BudgetService {


    private BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        Period period = new Period(start, end);


        List<Budget> budgets = budgetRepo.getAll();

        double total = 0D;
        for (Budget budget : budgets) {
            total += period.overlappingDays(budget.toPeriod()) * budget.getDailyAmount();
        }
        return total;
    }

}
