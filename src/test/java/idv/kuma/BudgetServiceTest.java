package idv.kuma;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */


public class BudgetServiceTest {

    @Test
    public void start_end_both_in_budget() {

        BudgetRepo mockedBudgetRepo = Mockito.mock(BudgetRepo.class);
        Mockito.when(mockedBudgetRepo.getAll()).thenReturn(
                Arrays.asList(
                        new Budget("201904", 30)
                )
        );

        BudgetService budgetService = new BudgetService(mockedBudgetRepo);

        double result = budgetService.query(
                LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 4, 1)

        );

        Assert.assertEquals(1D, result, 0.001);

    }
}
