package idv.kuma;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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

        double dailyAmount = (budget.getAmount() + 0D) / YearMonth.parse(budget.getYearMonth(), DateTimeFormatter.ofPattern("uuuuMM")).lengthOfMonth();

        long overlappingDays = period.overlappingDays(budget.toPeriod());


        return overlappingDays * dailyAmount;
    }

}
