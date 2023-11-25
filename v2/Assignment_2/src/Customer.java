import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Customer extends User {
    private ArrayList<Account> accounts = new ArrayList<>();
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public boolean isPremium() {
        for (Account account : accounts) {
            if (account.isPremium()) { // Ton tai 1 account Premium
                return account.isPremium();
            }
        }

        return false;
    } // Kiem tra dang tai khoan (Premium or Normal)

    public void addAccount (Account newAccount) {
         if (isValidAccount(newAccount)) {
             accounts.add(newAccount);

         }

    }

    public boolean isValidAccount(Account newAccount) {
        for (Account account : accounts) { // Duyet qua so ID cua tung account
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return false;
            }
        }
        return true;
    } // Kiem tra tai khoan co hop le khong

    public double getBalance() {
        double sum = 0;
        for (Account account : accounts) {
            sum += account.getBalance();
        }

        return sum;
    }

    public void displayInformation() {

        String premiumString = isPremium() ? "Premium": "Normal"; // String in ra Premium / Normal

        // Chuyen sang dinh dang tien vnd
        String balanceString = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(getBalance());

        System.out.printf("%15s %30s %10s %30s\n", getCustomerId() + " |", getName() + " |", premiumString + " |", balanceString);
        int i = 1;
        for (Account account: this.getAccounts()) {// In ra du lieu tung account
            System.out.printf(account.toString(i));
            i++;
        }
    } // Hien thi thong tin cua nguoi dung va cac tai khoan

}
