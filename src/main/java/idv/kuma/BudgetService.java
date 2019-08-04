package idv.kuma;

import java.time.LocalDate;
import java.util.List;


public class BudgetService {


    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        List<Budget> budgets = budgetRepo.getAll();

        Budget budget = budgets.get(0);


        double amount =  budget.getDailyAmount();

        return amount;


    }

}
