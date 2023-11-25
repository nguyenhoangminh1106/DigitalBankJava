public class LoansAccount extends Account implements ReportService, Withdraw{
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE =  100_000_000;

    private double fee;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public boolean isSavings() {
        return false;
    } // La LoansAccount

    @Override
    // In ra bien lai voi cac ham ho tro o class Utils
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s\n", getTitle());
        System.out.printf("NGAY G/D: %28s\n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s\n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s\n", this.getAccountNumber());
        System.out.printf("SO TIEN: %29s\n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s\n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s\n", Utils.formatBalance(this.getFee()));
        System.out.println(Utils.getDivider());

    }

    public String getTitle() {
        return "BIEN LAI GIAO DICH LOAN";
    }

    @Override
    public boolean withdraw(double amount) {
        // So tien du moi sau phi + vat
        this.setFee(calculateFee(amount));
        double newBalance = this.getBalance() - amount - this.getFee();
        if (isAccepted(amount)) {// So tien hop le
            setBalance(newBalance);
            return true;
        }

        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount > LOAN_ACCOUNT_MAX_BALANCE) {// > 100_000_000
            return false;
        }
        // So tien con du sau khi tru ca phi + vat
        else if(this.getBalance() - amount < 50_000 + calculateFee(amount)) {// Doi voi tai khoan thuong
            return false;
        }
        // Doi voi tai khoan Premium
        else if(this.isPremium() && this.getBalance() - amount < 50_000 + amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE) {
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
        String infoString = String.format("%15s %30s %41s\n", (stt) + ") " + this.accountNumber + " |", "LOANS |",vndBalance);
        return infoString;
    } // Chuyen thong tin cua mot so tai khoan ve dinh dang cho truoc

    //Tinh phi giao dich
    public double calculateFee(double amount) {
        // Tuy theo tai khoan la Premium hay khong
        return this.isPremium() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE: amount * LOAN_ACCOUNT_WITHDRAW_FEE;
    }
}
