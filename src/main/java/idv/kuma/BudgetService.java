package idv.kuma;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class BudgetService {

    BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {


        return 3100;

    }
}
