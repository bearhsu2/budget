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

    private BudgetService budgetService;
    private BudgetRepo budgetRepo;

    @Before
    public void setUp() throws Exception {

        budgetRepo = mock(BudgetRepo.class);
        budgetService = new BudgetService(budgetRepo);

    }

    @Test
    public void No_Budgets() {

        prepareBudgets();

        runAndCheck(0,
                LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 4, 1));
    }

    private void prepareBudgets(Budget... budgets) {
        when(budgetRepo.getAll()).thenReturn(Arrays.asList(budgets));
    }

    @Test
    public void Period_Inside_Budget_Month() {

        prepareBudgets(new Budget("201904", 30));

        runAndCheck(1,
                LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 4, 1));
    }

    @Test
    public void Period_No_Overlap_Before_Budget_First_Day() {

        prepareBudgets(new Budget("201904", 30));

        runAndCheck(0,
                LocalDate.of(2019, 3, 31),
                LocalDate.of(2019, 3, 31));
    }

    @Test
    public void Period_No_Overlap_After_Budget_Last_Day() {

        prepareBudgets(new Budget("201904", 30));

        runAndCheck(0,
                LocalDate.of(2019, 5, 1),
                LocalDate.of(2019, 5, 1));
    }

    @Test
    public void Period_Overlap_Budget_First_Day() {

        prepareBudgets(new Budget("201904", 30));

        runAndCheck(1,
                LocalDate.of(2019, 3, 31),
                LocalDate.of(2019, 4, 1));

    }


    @Test
    public void Period_Overlap_Budget_Last_Day() {

        prepareBudgets(new Budget("201904", 30));

        runAndCheck(1,
                LocalDate.of(2019, 4, 30),
                LocalDate.of(2019, 5, 1));

    }

    private void runAndCheck(int expected, LocalDate start, LocalDate end) {

        Assert.assertEquals(expected, budgetService.query(start, end), 0.001);
    }

}
