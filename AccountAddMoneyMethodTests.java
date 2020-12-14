import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountAddMoneyMethodTests {
    private Account account;

    @Before
    public void setUpBeforeEachTest() {
        this.account = new Account("Alex", 100d, 5d, 3);
    }

    @Test
    public void testAddMoneyWorkCorrectly() {
        //arrange
        double amountToAdd = 150.5d;
        double expectedBalance = 250.5d;

        //act
        this.account.addMoney(amountToAdd);

        //assert
        Assert.assertEquals(expectedBalance, account.getCurrentBalance(), 0.00001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMoneyThrowIllegalArgumentExceptionForNegativeAmount() {
        //arrange
        double amountToAdd = -150.5d;

        //act
        this.account.addMoney(amountToAdd);

        //assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMoneyThrowIllegalArgumentExceptionForZeroAmount() {
        //arrange
        double amountToAdd = 0.0d;

        //act
        this.account.addMoney(amountToAdd);

        //assert
    }

    @Test
    public void testAddMoneyIncrementNumberOfTransactions() {
        //arrange
        double amountToAdd = 10d;
        int expectedNumberOfTransactions = 1;

        //act
        this.account.addMoney(amountToAdd);

        //assert
        Assert.assertEquals(expectedNumberOfTransactions, this.account.getNumberOfOperationsPerformed());
    }

    @Test
    public void testMultipleAddMoneyWorksAsExpected() {
        //arrange
        double[] amounts = { 100.5d, 150.5d, 50.5d };
        double expectedAmount = 401.5d;

        //act
        for(double amount : amounts) {
            this.account.addMoney(amount);
        }

        //assert
        Assert.assertEquals(expectedAmount, this.account.getCurrentBalance(), 0.00001d);
    }
}
