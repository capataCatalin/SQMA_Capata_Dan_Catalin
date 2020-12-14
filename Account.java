class ZeroBalanceException extends Exception {
    public ZeroBalanceException(String exMessage) {
        super(exMessage);
    }
}

class InsufficientFoundsException extends Exception {
    public InsufficientFoundsException(String exMessage) {
        super(exMessage);
    }
}

public class Account {
    private String ownerName;
    private double currentBalance;
    private final double commissionValue;
    private final int numberOfFreeOperations;
    private int numberOfOperationsPerformed;

    public Account(String ownerName, double currentBalance, double commissionValue, int numberOfFreeOperations) {
        this.ownerName = ownerName;
        this.currentBalance = currentBalance;
        this.commissionValue = commissionValue;
        this.numberOfFreeOperations = numberOfFreeOperations;
        this.numberOfOperationsPerformed = 0;
    }

    public void withdrawMoney(double amount) throws ZeroBalanceException, InsufficientFoundsException, IllegalArgumentException {
        if(amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive number and greater than zero!");
        }
        if(this.currentBalance == 0) {
            throw new ZeroBalanceException("Can not withdraw when have 0 balance! Please add some money first!");
        }
        if(this.currentBalance - amount < 0) {
            throw new InsufficientFoundsException("You does not have enough founds to withdraw");
        }

        this.takeCommissionIfNumberOfFreeOperationsExceeded();
        this.currentBalance -= amount;
        this.numberOfOperationsPerformed ++;
    }

    public void addMoney(double amount) throws IllegalArgumentException {
        if(amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive number and greater than zero!");
        }
        this.currentBalance += amount;
        this.numberOfOperationsPerformed ++;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getNumberOfOperationsPerformed() {
        return numberOfOperationsPerformed;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    private void takeCommissionIfNumberOfFreeOperationsExceeded() {
        if(this.numberOfFreeOperations <= this.numberOfOperationsPerformed) {
            this.currentBalance -= this.commissionValue;
        }
    }
}
