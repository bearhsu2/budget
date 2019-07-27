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
    public void When_Single_Whole_Month_Then_Whole_Jan() {

        BudgetRepo mockedRepo = mock(BudgetRepo.class);

        when(mockedRepo.getAll()).thenReturn(Arrays.asList(
                new Budget("201901", 3100)
        ));


        BudgetService budgetService = new BudgetService(mockedRepo);


        Assert.assertEquals(3100,
                budgetService.query(
                        givenLocalDate(2019, 1, 1),
                        givenLocalDate(2019, 1, 31)), 0.001);
    }

    @Test
    public void When_Single_Whole_Month_Then_Whole_Feb() {

        BudgetRepo mockedRepo = mock(BudgetRepo.class);

        when(mockedRepo.getAll()).thenReturn(Arrays.asList(
                new Budget("201901", 3100),
                new Budget("201902", 2800)
        ));


        BudgetService budgetService = new BudgetService(mockedRepo);


        Assert.assertEquals(2800,
                budgetService.query(
                        givenLocalDate(2019, 2, 1),
                        givenLocalDate(2019, 2, 28)), 0.001);
    }

    private LocalDate givenLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }


}
