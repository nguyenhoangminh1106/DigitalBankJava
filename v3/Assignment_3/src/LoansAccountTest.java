import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoansAccountTest {

    LoansAccount loansAccount = new LoansAccount();


    @Test
    void withdraw() {
        assertFalse(loansAccount.withdraw(100_000_001));

        loansAccount.setBalance(500_000);
        assertFalse(loansAccount.withdraw(455_000));

        loansAccount.setBalance(10_000_000);
        assertTrue(loansAccount.withdraw(500_000));
        assertEquals(9_495_000, loansAccount.getBalance());

        loansAccount.setBalance(5_000_000);
        assertTrue(loansAccount.withdraw(500_000));
        assertEquals(4_475_000, loansAccount.getBalance());

    }

    @Test
    void calculateFee() {

        loansAccount.setBalance(10_000_000);
        assertEquals(5000, loansAccount.calculateFee(500_000));

        loansAccount.setBalance(5_000_000);
        assertEquals(25_000, loansAccount.calculateFee(500_000));


    }
}