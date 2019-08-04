package idv.kuma;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */


public class BudgetServiceTest {


    @Test
    public void period_in_budget() {


        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.getAll()).thenReturn(Arrays.asList(
                new Budget("201904", 30)
        ));


        BudgetService budgetService = new BudgetService(budgetRepo);

        double result = budgetService.query(
                LocalDate.of(2019,04,01),
                LocalDate.of(2019,04,01)

        );

        Assert.assertEquals(1D, result, 0.001);


    }
}
