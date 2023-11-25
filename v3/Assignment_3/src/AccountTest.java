import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {
    Account account = new Account();

    @ParameterizedTest
    @ValueSource(strings = {"123", "1234567", "", "123abc", "abcdef"})
    void isValidAccountNumber(String accountNumber) {
        assertFalse(account.isValidAccountNumber(accountNumber));

        assertTrue(account.isValidAccountNumber("123456"));
    }
    @Test
    void isPremium() {
        account.setBalance(10_000_000);
        assertTrue(account.isPremium());

        account.setBalance(9_999_999);
        assertFalse(account.isPremium());
    }
}