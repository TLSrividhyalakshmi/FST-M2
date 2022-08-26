package activities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class activity2 {
    BankAccount acc;

    @Test
    public void notEnoughFunds()
    {
        acc =new BankAccount(10);
        assertThrows(BankAccount.NotEnoughFundsException.class, () -> acc.withdraw(11),"Balance is less to withdraw");
    }
    @Test
    public void EnoughFunds()
    {
         acc= new BankAccount(1000);
        assertDoesNotThrow(() -> acc.withdraw(100),"Withdrawn Successfully");
    }
}
