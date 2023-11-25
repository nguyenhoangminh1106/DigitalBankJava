import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {
    Account account = new Account();


    @Test
    void isPremium() {
        account.setBalance(10_000_000);
        assertTrue(account.isPremium());

        account.setBalance(9_999_999);
        assertFalse(account.isPremium());
    }
}