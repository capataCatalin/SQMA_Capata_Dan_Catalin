import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountWithdrawMethodTests {
    private Account account;
    private final int NO_OF_FREE_OPERATIONS = 3;
    private final double COMMISSION_VALUE = 5d;

    @Before
    public void setUpBeforeEachTest() {
        this.account = new Account("Alex", 100d, COMMISSION_VALUE, NO_OF_FREE_OPERATIONS);
    }

    @Test
    public void testWithdrawWorkCorrectly() throws InsufficientFoundsException, ZeroBalanceException {
        //arrange
        double expectedBalance = 20.77d;
        double withdrawAmount = 79.23d;

        //act
        this.account.withdrawMoney(withdrawAmount);

        //assert
        Assert.assertEquals(expectedBalance, this.account.getCurrentBalance(), 0.00001d);
    }

    @Test(expected = InsufficientFoundsException.class)
    public void testWithdrawThrowInsufficientFoundsException() throws InsufficientFoundsException, ZeroBalanceException {
        //arrange
        double withdrawAmount = 119.23d;

        //act
        this.account.withdrawMoney(withdrawAmount);
    }

    @Test(expected = ZeroBalanceException.class)
    public void testWithdrawThrowZeroBalanceException() throws InsufficientFoundsException, ZeroBalanceException {
        //arrange
        double withdrawAmount = 100.00d;
        double secondWithdrawAmount = 10.193d;

        //act
        this.account.withdrawMoney(withdrawAmount);
        this.account.withdrawMoney(secondWithdrawAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmountThrowIllegalArgumentException() throws InsufficientFoundsException, ZeroBalanceException {
        //arrange
        double amountToAdd = -150.5d;

        //act
        this.account.withdrawMoney(amountToAdd);
    }

    @Test
    public void testIfFreeTransactionsAreExceededCommissionIsApplied() throws InsufficientFoundsException, ZeroBalanceException {
        //arrange
        int exceededNumberOfTransactions = NO_OF_FREE_OPERATIONS + 1;
        double withdrawAmount = 10d;
        double expectedResult = this.account.getCurrentBalance() - exceededNumberOfTransactions * withdrawAmount - COMMISSION_VALUE;

        //act
        for(int i = 0; i < exceededNumberOfTransactions; i++) {
            this.account.withdrawMoney(withdrawAmount);
        }

        //assert
        Assert.assertEquals(expectedResult, this.account.getCurrentBalance(), 0.00001d);

    }

    @Test
    public void testMultipleWithdrawMoneyCallsWithoutCommissionWorksAsExpected() throws InsufficientFoundsException, ZeroBalanceException {
        //arrange
        double[] amounts = { 5.5d, 10.5d, 15.15d };
        double expectedAmount = 68.85d;

        //act
        for(double amount : amounts) {
            this.account.withdrawMoney(amount);
        }

        //assert
        Assert.assertEquals(expectedAmount, this.account.getCurrentBalance(), 0.00001d);
    }

}
