import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Bank bankTest = new DigitalBank(new Scanner(System.in));

    Customer customer = bankTest.searchCustomerByCCCD("001000000000");
    Customer customer1 = bankTest.searchCustomerByCCCD("001444444444");

    Account account1 = new Account();
    Account account2 = new Account();
    Account account3 = new Account();


    @Test
    void isPremium() {
        customer.getAccounts();
        assertTrue(customer.isPremium());
        assertFalse(customer1.isPremium());
    }

    @Test
    void isAccountExisted() {
        assertTrue(customer.isAccountExisted("111111"));
        assertTrue(customer.isAccountExisted("222222"));
        assertFalse(customer.isAccountExisted("135790"));




    }

    @Test
    void getBalance() {
        assertEquals(60_550_000, customer.getBalance());
    }

    @Test
    void getAccountByAccountNumber() {
        assertEquals(50_000, customer.getAccountByAccountNumber("111111").getBalance());
        assertEquals(9_000_000, customer.getAccountByAccountNumber("222222").getBalance());
        assertNull(customer.getAccountByAccountNumber("135790"));

    }
}