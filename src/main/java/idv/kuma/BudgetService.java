package idv.kuma;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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
        long days = overlapDays(period,budget);

        return days;

    }

    private long overlapDays(Period period, Budget budget) {

        if (period.getEnd().isBefore(budget.getFirstDay())) {
            return 0;
        }

        if (period.getStart().isAfter(budget.getLastDay())) {
            return 0;
        }

        LocalDate overlapStart = period.getEnd().isAfter(budget.getFirstDay())
                ? budget.getFirstDay()
                : period.getEnd();

        return DAYS.between(overlapStart, period.getEnd()) + 1;
    }

}
