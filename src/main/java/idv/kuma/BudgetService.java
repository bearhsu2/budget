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

        return budgetRepo.getAll()
                .stream()
                .mapToDouble(budget -> budget.getEffectiveAmount(period))
                .sum();


    }

}
