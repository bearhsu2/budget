package idv.kuma;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */


public class BudgetServiceTest {


    private BudgetRepo budgetRepo;
    private BudgetService budgetService;

    @Before
    public void setUp() throws Exception {
        budgetRepo = mock(BudgetRepo.class);
        budgetService = new BudgetService(budgetRepo);
    }

    @Test
    public void period_in_budget() {


        prepareBudgets(
                new Budget("201904", 30)
        );

        runAndCheck(5D,
                LocalDate.of(2019, 04, 01),
                LocalDate.of(2019, 04, 05));

    }

    @Test
    public void period_start_before_budget_first_day() {


        prepareBudgets(
                new Budget("201904", 30)
        );

        runAndCheck(1D,
                LocalDate.of(2019, 03, 30),
                LocalDate.of(2019, 04, 01));

    }



    private void runAndCheck(double expected, LocalDate start, LocalDate end) {
        double result = budgetService.query(
                start,
                end

        );

        Assert.assertEquals(expected, result, 0.001);
    }

    private void prepareBudgets(Budget... budgets) {
        when(budgetRepo.getAll()).thenReturn(Arrays.asList(
              budgets
        ));
    }
}
