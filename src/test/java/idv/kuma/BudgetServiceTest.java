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

    private BudgetRepo mockedRepo;
    private BudgetService budgetService;

    @Before
    public void setUp() throws Exception {
        mockedRepo = mock(BudgetRepo.class);
        budgetService = new BudgetService(mockedRepo);
    }

    @Test
    public void When_Single_Whole_Month_Then_Whole_Jan() {

        prepareRepo(createBudget("201901", 3100));


        check(3100,
                makeLocalDate(2019, 1, 1),
                makeLocalDate(2019, 1, 31));


    }

    private void check(double expected, LocalDate start, LocalDate end) {
        Assert.assertEquals(expected, budgetService.query(start, end), 0.001);
    }

    private void prepareRepo(Budget... budgets) {
        when(mockedRepo.getAll()).thenReturn(Arrays.asList(
                budgets
        ));
    }

    private Budget createBudget(String s, int i) {
        return new Budget(s, i);
    }

    @Test
    public void When_Single_Whole_Month_Then_Whole_Feb() {

        prepareRepo(createBudget("201901", 3100),
                createBudget("201902", 2800));


        check(2800,
                makeLocalDate(2019, 2, 1),
                makeLocalDate(2019, 2, 28));
    }

    @Test
    public void When_Two_Whole_Months_Then_Sum() {

        prepareRepo(createBudget("201901", 3100),
                createBudget("201902", 2800));


        check(5900,
                makeLocalDate(2019, 1, 1),
                makeLocalDate(2019, 2, 28));
    }


    @Test
    public void When_Three_Whole_Months_Then_Sum() {

        prepareRepo(createBudget("201901", 3100),
                createBudget("201902", 2800),
                createBudget("201903", 3100));


        check(9000,
                makeLocalDate(2019, 1, 1),
                makeLocalDate(2019, 3, 31));
    }

    private LocalDate makeLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }


}
