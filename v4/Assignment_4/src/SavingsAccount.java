import java.io.Serial;
import java.io.Serializable;

public class SavingsAccount extends Account implements ReportService, Withdraw, ITransfer, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000;


    @Override
    public void logWithdraw(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s\n", getTitle());
        System.out.printf("NGAY G/D: %28s\n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s\n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s\n", this.getAccountNumber());
        System.out.printf("SO TIEN: %29s\n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s\n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s\n", "0đ");
        System.out.println(Utils.getDivider());
    } // In ra bien lai dung method ham Utils

    @Override
    public void logTransfer(Account receiveAccount, double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s\n", getTitle());
        System.out.printf("NGAY G/D: %28s\n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s\n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s\n", this.getAccountNumber());
        System.out.printf("SO TK NHAN: %26s\n", receiveAccount.getAccountNumber());
        System.out.printf("SO TIEN: %29s\n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s\n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s\n", "0đ");
        System.out.println(Utils.getDivider());
    } // In ra bien lai dung method ham Utils

    public String getTitle() {
        return "BIEN LAI GIAO DICH SAVINGS";
    }

    @Override
    public boolean withdraw(double amount) {
        // So tien du moi
        double newBalance = this.getBalance() - amount;
        if (isAccepted(amount)) {// Amount hop le
            setBalance(newBalance);
            return true;
        }

        return false;
    }

    public boolean transfer(Account receiveAccount, double amount) {
        if (withdraw(amount)) {// Rut tien o tai khoan chuyen
            //Them tien o tai khoan nhap
            double newReceivedAmount = receiveAccount.getBalance() + amount;
            receiveAccount.setBalance(newReceivedAmount);
            return true;
        }

        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount < 50_000 || amount % 10_000 != 0) {
            return false;
        }
        // Doi voi tai khoan thuong
        else if (!this.isPremium() && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW) {
            return false;
        }
        // So du con laij < 50_000
        else if(this.getBalance() - amount < 50_000) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public String toString (int stt) {
        String vndBalance = Utils.formatBalance(this.getBalance());
        //NumberFormat vnd (Tao bo Format cho vnd)                            //.vnd.format()
        String infoString = String.format("%15s %30s %41s\n", (stt) + ") " + this.getAccountNumber()+ " |", "SAVINGS |",vndBalance);
        return infoString;
    } // Chuyen thong tin cua mot so tai khoan ve dinh dang cho truoc
}