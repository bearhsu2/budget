package idv.kuma;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class BudgetService {


    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {

        List<Budget> budgets = budgetRepo.getAll();

        Budget budget = budgets.get(0);


        YearMonth yearMonth = YearMonth.parse(budget.getYearMonth(), DateTimeFormatter.ofPattern("uuuuMM"));
        return budget.getAmount() / yearMonth.lengthOfMonth();


    }
}
