import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Customer(String customerId, String name) {
        if (Utils.isValidId(customerId)) {
            setCustomerId(customerId);
            setName(name);
        }
    }

    public Customer(List<String> values) {
        this(values.get(0), values.get(1));
    }

    public List<Account> getAccounts() {
        return AccountDao.list().stream()
                .filter(account -> account.getCustomerId().equals(this.getCustomerId()))
                .collect(Collectors.toList());
    }

    public boolean isPremium() {
        for (Account account : getAccounts()) {
            if (account.isPremium()) { // Ton tai 1 account Premium
                return account.isPremium();
            }
        }

        return false;
    } // Kiem tra dang tai khoan (Premium or Normal)

    public boolean isAccountExisted(String newAccountNumber) {
        for (Account account : this.getAccounts()) { // Duyet qua so ID cua tung account
            if (account.getAccountNumber().equals(newAccountNumber)) {
                return true;
            }
        }
        return false;
    } // Kiem tra tai khoan co hop le khong

    public double getBalance() {
        double sum = 0;
        for (Account account : getAccounts()) {
            sum += account.getBalance();
        }

        return sum;
    }

    public void displayInformation() {

        String premiumString = isPremium() ? "Premium": "Normal"; // String in ra Premium / Normal

        // Chuyen sang dinh dang tien vnd
        String balanceString = Utils.formatBalance(getBalance());

        System.out.printf("%15s %30s %10s %30s\n", getCustomerId() + " |", getName() + " |", premiumString + " |", balanceString);
        int i = 1;
        for (Account account: this.getAccounts()) {// In ra du lieu tung account
            System.out.printf(account.toString(i));
            i++;
        }
    } // Hien thi thong tin cua nguoi dung va cac tai khoan

    public Account getAccountByAccountNumber(String accountNumber) {
        for (Account account : this.getAccounts()) { // Duyet qua toan bo account cua Customer
            if (account.getAccountNumber().equals(accountNumber)) { // Neu trung stk thi tra ve tai khoan
                return account;
            }
        }
        return null;
    }

    public boolean withdraw(String accountNumber, double amount){

        if (isAccountExisted(accountNumber)) {// Stk ton tai thi lay tai khoan
            Account account = getAccountByAccountNumber(accountNumber);

            if (account instanceof  SavingsAccount) {// La tai khoang Savings

                //Casing Account => SavingsAccount
                SavingsAccount savingsAccount = (SavingsAccount) account;
                if(savingsAccount.withdraw(amount)) {// Rut thanh cong thi in ra bien lai
                    try {
                        AccountDao.update(account);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Rut tien thanh cong, bien lai dao dich:");
                    savingsAccount.logWithdraw(amount);

                    return true;
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }

    public boolean transfer(String accountNumber, Account receiveAccount, double amount) {

        if (isAccountExisted(accountNumber)) {// Stk ton tai thi lay tai khoan
            Account account = getAccountByAccountNumber(accountNumber);

            if (account instanceof  SavingsAccount) {// La tai khoang Savings

                //Casing Account => SavingsAccount
                SavingsAccount savingsAccount = (SavingsAccount) account;
                if(savingsAccount.transfer(receiveAccount, amount)) {//Chuyen thanh cong thi in ra bien lai
                    System.out.println("Chuyen tien thanh cong, bien lai giao dich: ");
                    savingsAccount.logTransfer(receiveAccount, amount);
                    try {
                        AccountDao.update(account);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }


}


