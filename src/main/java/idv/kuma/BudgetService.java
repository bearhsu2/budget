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

        Budget budget = budgets.get(0);

        double dailyAmount = budget.getDailyAmount();

        long overlappingDays = period.overlappingDays(budget.toPeriod());


        return overlappingDays * dailyAmount;
    }

}
