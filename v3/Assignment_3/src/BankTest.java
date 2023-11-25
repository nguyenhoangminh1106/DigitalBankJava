import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank bank = new Bank(new Scanner(System.in));
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();




    @BeforeEach
    void init() {
        customer1.setCustomerId("001000000000");
        customer1.setName("A");
        bank.addCustomer(customer1);

        customer2.setCustomerId("001000000001");
        customer2.setName("B");
        bank.addCustomer(customer2);
    }

    @Test
    void isCustomerExisted() {
        assertTrue(bank.isCustomerExisted("001000000000"));
        assertTrue(bank.isCustomerExisted("001000000001"));
        assertFalse(bank.isCustomerExisted("001000000002"));


    }

    @Test
    void searchCustomerByCCCD() {
        assertEquals(customer1, bank.searchCustomerByCCCD("001000000000"));
        assertEquals(customer2, bank.searchCustomerByCCCD("001000000001"));
        assertNotEquals(customer1, bank.searchCustomerByCCCD("001000000001"));
        assertNotEquals(customer2, bank.searchCustomerByCCCD("001000000000"));
    }
}