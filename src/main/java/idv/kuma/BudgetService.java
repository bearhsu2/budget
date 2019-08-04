package idv.kuma;

import java.time.LocalDate;
import java.util.List;


public class BudgetService {


    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        Period period = new Period(start, end);

        List<Budget> budgets = budgetRepo.getAll();

        Budget budget = budgets.get(0);


        long days = period.getEffectiveDays(budget);

        double dailyAmount = budget.getDailyAmount();


        return days * dailyAmount;


    }

}
