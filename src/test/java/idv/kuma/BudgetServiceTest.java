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

    @Test
    public void period_end_after_budget_last_day() {


        prepareBudgets(
                new Budget("201904", 30)
        );

        runAndCheck(1D,
                LocalDate.of(2019, 04, 30),
                LocalDate.of(2019, 05, 01));

    }


    @Test
    public void period_no_overlap_before_budget_first_day() {


        prepareBudgets(
                new Budget("201904", 30)
        );

        runAndCheck(0D,
                LocalDate.of(2019, 03, 29),
                LocalDate.of(2019, 03, 30));

    }

    @Test
    public void period_no_overlap_after_budget_last_day() {


        prepareBudgets(
                new Budget("201904", 30)
        );

        runAndCheck(0D,
                LocalDate.of(2019, 5, 11),
                LocalDate.of(2019, 5, 15));

    }


    @Test
    public void daily_amount_more_than_1() {


        prepareBudgets(
                new Budget("201904", 300)
        );

        runAndCheck(300,
                LocalDate.of(2019, 3, 29),
                LocalDate.of(2019, 5, 3));

    }


    @Test
    public void more_than_one_budgets() {


        prepareBudgets(
                new Budget("201903", 31),
                new Budget("201904", 300),
                new Budget("201905", 31000)
        );

        runAndCheck(2 + 300 + 5000,
                LocalDate.of(2019, 3, 30),
                LocalDate.of(2019, 5, 5));

    }



    @Test
    public void illegal_qeury() {


        prepareBudgets(
                new Budget("201904", 30)
        );

        runAndCheck(0D,
                LocalDate.of(2019, 4, 5),
                LocalDate.of(2019, 4, 2));

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
