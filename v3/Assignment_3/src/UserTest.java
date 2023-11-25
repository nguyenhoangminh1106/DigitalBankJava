import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @ParameterizedTest
    @ValueSource(strings = {"00111111111", "000111111111", "001111111abc", "", "fsafsd"})
    void isValidId(String cccd) {
        User user = new User();

        assertFalse(user.isValidId(cccd));

        assertTrue(user.isValidId("001000000000"));
    }
}