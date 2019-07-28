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

    @Test
    public void No_Budgets() {

        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.getAll()).thenReturn(Arrays.asList());


        LocalDate start = LocalDate.of(2019,4,1);
        LocalDate end = LocalDate.of(2019,4,1);

        Assert.assertEquals(0, new BudgetService(budgetRepo).query(start,end),0.001);
    }

    @Test
    public void Period_Inside_Budget_Month() {

        BudgetRepo budgetRepo = mock(BudgetRepo.class);
        when(budgetRepo.getAll()).thenReturn(Arrays.asList(new Budget("201904",30)));

        LocalDate start = LocalDate.of(2019,4,1);
        LocalDate end = LocalDate.of(2019,4,1);

        Assert.assertEquals(1, new BudgetService(budgetRepo).query(start,end),0.001);
    }
}
