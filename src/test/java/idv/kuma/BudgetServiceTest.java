package idv.kuma;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */


public class BudgetServiceTest {

    private BudgetService budgetService;
    private BudgetRepo mockedBudgetRepo;

    @Before
    public void setUp() throws Exception {

        mockedBudgetRepo = Mockito.mock(BudgetRepo.class);
        budgetService = new BudgetService(mockedBudgetRepo);
    }

    @Test
    public void start_end_both_in_budget() {

        prepareBudgetRepo(new Budget("201904", 30));


        runAndCheck(1D, LocalDate.of(2019, 4, 1), LocalDate.of(2019, 4, 1));

    }

    private void runAndCheck(double expected, LocalDate start, LocalDate end) {
        Assert.assertEquals(expected, budgetService.query(start, end), 0.001);
    }

    private void prepareBudgetRepo(Budget... budgets) {
        Mockito.when(mockedBudgetRepo.getAll()).thenReturn(
                Arrays.asList(budgets)
        );
    }
}
