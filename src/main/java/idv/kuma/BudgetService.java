package idv.kuma;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


public class BudgetService {


    private BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {


        List<Budget> budgets = budgetRepo.getAll();

        Budget budget = budgets.get(0);

        long days;
        if (end.isBefore(budget.getFirstDay())) {
            days = 0L;
        } else {

            days = DAYS.between(start, end) + 1;
        }


        return days;
    }
}
