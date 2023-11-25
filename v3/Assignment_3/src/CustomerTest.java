import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer = new Customer();
    Account account1 = new Account();
    Account account2 = new Account();
    Account account3 = new Account();

    @BeforeEach
    void init() {
        account1.setAccountNumber("111111");
        account1.setBalance(10000000);
        account1.setSaving(true);

        account2.setAccountNumber("222222");
        account2.setBalance(50000);
        account2.setSaving(false);

        account3.setAccountNumber("333333");
        account3.setBalance(700000);
        account3.setSaving(true);

        customer.addAccount(account1);
        customer.addAccount(account2);
        customer.addAccount(account3);

    }

    @Test
    void isPremium() {
        assertTrue(customer.isPremium());
        account1.setBalance(0);
        assertFalse(customer.isPremium());
    }

    @Test
    void isAccountExisted() {
        assertTrue(customer.isAccountExisted("111111"));
        assertTrue(customer.isAccountExisted("222222"));
        assertTrue(customer.isAccountExisted("333333"));
        assertFalse(customer.isAccountExisted("123456"));




    }

    @Test
    void getBalance() {
        assertEquals(10_750_000, customer.getBalance());
    }

    @Test
    void getAccountByAccountNumber() {
        assertEquals(account1, customer.getAccountByAccountNumber("111111"));
        assertEquals(account2, customer.getAccountByAccountNumber("222222"));
        assertEquals(account3, customer.getAccountByAccountNumber("333333"));
        assertNull(customer.getAccountByAccountNumber("123456"));

    }
}