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
    public void When_Single_Whole_Month_Then_Whole_Month() {

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

    private LocalDate givenLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }


}
