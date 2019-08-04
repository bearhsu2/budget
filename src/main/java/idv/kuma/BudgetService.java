package idv.kuma;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


public class BudgetService {


    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        List<Budget> budgets = budgetRepo.getAll();

        Budget budget = budgets.get(0);


        long days = DAYS.between(start, end) + 1;

        double dailyAmount =  budget.getDailyAmount();


        return days * dailyAmount;


    }

}
