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

        when(budgetRepo.getAll()).thenReturn(Arrays.asList());


        LocalDate start = LocalDate.of(2019, 4, 1);
        LocalDate end = LocalDate.of(2019, 4, 1);

        Assert.assertEquals(0, budgetService.query(start, end), 0.001);
    }

    @Test
    public void Period_Inside_Budget_Month() {

        when(budgetRepo.getAll()).thenReturn(Arrays.asList(new Budget("201904", 30)));

        LocalDate start = LocalDate.of(2019, 4, 1);
        LocalDate end = LocalDate.of(2019, 4, 1);


        Assert.assertEquals(1, budgetService.query(start, end), 0.001);
    }

    @Test
    public void Period_No_Overlap_Before_Budget_First_Day() {

        when(budgetRepo.getAll()).thenReturn(Arrays.asList(new Budget("201904", 30)));

        LocalDate start = LocalDate.of(2019, 3, 31);
        LocalDate end = LocalDate.of(2019, 3, 31);


        Assert.assertEquals(0, budgetService.query(start, end), 0.001);
    }

    @Test
    public void Period_No_Overlap_After_Budget_Last_Day() {

        when(budgetRepo.getAll()).thenReturn(Arrays.asList(new Budget("201904", 30)));

        LocalDate start = LocalDate.of(2019, 5, 1);
        LocalDate end = LocalDate.of(2019, 5, 1);


        Assert.assertEquals(0, budgetService.query(start, end), 0.001);

    }

    @Test
    public void Period_Overlap_Budget_First_Day() {

        when(budgetRepo.getAll()).thenReturn(Arrays.asList(new Budget("201904", 30)));

        LocalDate start = LocalDate.of(2019, 3, 31);
        LocalDate end = LocalDate.of(2019, 4, 1);


        Assert.assertEquals(1, budgetService.query(start, end), 0.001);

    }

}
