import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank bank = new Bank(new Scanner(System.in));


    @Test
    void isCustomerExisted() {
        assertTrue(bank.isCustomerExisted("001000000000"));
        assertTrue(bank.isCustomerExisted("001111111111"));
        assertFalse(bank.isCustomerExisted("001000000002"));


    }

    @Test
    void searchCustomerByCCCD() {
        assertEquals("Minh", bank.searchCustomerByCCCD("001000000000").getName());
        assertEquals("Linh", bank.searchCustomerByCCCD("001111111111").getName());
    }
}