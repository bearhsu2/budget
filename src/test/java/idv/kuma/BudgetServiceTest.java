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

        LocalDate start = LocalDate.of(2019,4,1);
        LocalDate end = LocalDate.of(2019,4,1);

        Assert.assertEquals(0, new BudgetService().query(start,end),0.001);
    }
}
