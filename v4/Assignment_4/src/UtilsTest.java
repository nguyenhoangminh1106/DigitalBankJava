import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {"123", "1234567", "", "123abc", "abcdef"})
    void isValidAccountNumber(String accountNumber) {
        assertFalse(Utils.isValidAccountNumber(accountNumber));

        assertTrue(Utils.isValidAccountNumber("123456"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"00111111111", "000111111111", "001111111abc", "", "fsafsd"})
    void isValidId(String cccd) {
        User user = new User();

        assertFalse(Utils.isValidId(cccd));

        assertTrue(Utils.isValidId("001000000000"));
    }
}