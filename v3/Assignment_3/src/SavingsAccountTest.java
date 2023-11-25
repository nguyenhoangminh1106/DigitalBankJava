import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    @Test
    void withdraw() throws Exception {
        SavingsAccount savingsAccount = new SavingsAccount();

        assertFalse(savingsAccount.withdraw(49000));

        savingsAccount.setBalance(10_000_000);
        assertTrue(savingsAccount.withdraw(6_000_000));

        savingsAccount.setBalance(9_000_000);
        assertFalse(savingsAccount.withdraw(6_000_000));

        savingsAccount.setBalance(500_000);
        assertFalse(savingsAccount.withdraw(455_000));

        assertFalse(savingsAccount.withdraw(51000));

        savingsAccount.setBalance(10_000_000);
        assertTrue(savingsAccount.withdraw(500_000));
        assertEquals(9_500_000, savingsAccount.getBalance());
    }
}