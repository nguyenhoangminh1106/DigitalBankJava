import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class Account {
    String accountNumber;
    double balance;

    // Tao getter va setter cho accountNumber va balance
    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isValidAccountNumber (String accountNumber) {
        String regex = "^\\d{6}$"; // Chuoi 6 chu so

        try {
            return Pattern.matches(regex, accountNumber);
        }
        catch (Exception e) {
            return false;
        }
    } // Xem stk co hop le khong

    public void setAccountNumber(String accountNumber) {
        if (isValidAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
        }
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
        else {
            this.balance = 0;
        }
    }

    public boolean isPremium() {
        return this.balance >= 10_000_000;
    } // Kiem tra tai khoan la Premium hay Normal

    public String toString (int stt) {
        String vndBalance = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(this.balance);
                            //NumberFormat vnd (Tao bo Format cho vnd)                            //.vnd.format()
        String infoString = String.format("%15s %72s\n", (stt) + ") " + this.accountNumber + " |", vndBalance);
        return infoString;
    } // Chuyen thong tin cua mot so tai khoan ve dinh dang cho truoc

}
