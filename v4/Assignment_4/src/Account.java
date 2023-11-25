import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
//    private List<Transaction> transactions = new ArrayList<>();

    private String accountNumber;
    private double balance;
    private String customerId;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Tao getter va setter cho accountNumber va balance
    public String getAccountNumber() {
        return accountNumber;
    }



    public void setAccountNumber(String accountNumber) {
        if (Utils.isValidAccountNumber(accountNumber)) {
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
        return getBalance() >= 10_000_000;
    } // Kiem tra tai khoan la Premium hay Normal

    public String toString (int stt) {
        String vndBalance = Utils.formatBalance(getBalance());
        //NumberFormat vnd (Tao bo Format cho vnd)                            //.vnd.format()
        String infoString = String.format("%15s %72s\n", (stt) + ") " + getAccountNumber() + " |", vndBalance);
        return infoString;
    } // Chuyen thong tin cua mot so tai khoan ve dinh dang cho truoc


    public void addTransactions (Transaction transaction){
        try {// Su dung ham update
            TransactionDao.update(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // Them giao dich vao list

    public List<Transaction> getTransactions() {
        return TransactionDao.list().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(this.getAccountNumber()))
                .collect(Collectors.toList());
    } // Lay list lich su giao dich

    public void createTransaction(double amount, String time, String type) {
        Transaction transaction = new Transaction(getAccountNumber(), amount, time, type);
        addTransactions(transaction);
    }

    public void displayTransactionsList() {
        for (Transaction transaction : getTransactions()) {// Duyet tung transaction
            System.out.println(transaction.toString());
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public static void input(Scanner sc, DigitalBank bank) {
        SavingsAccount accountATM = new SavingsAccount();

        // Nhap so CCCD da ton tai va xet tinh hop le
        String soCCCD = bank.nhapCCCDTonTai();


        if (soCCCD.equals("")) {// Thoat neu CCCC = null
            return;
        }

        // Nhap so tai khoan
        String stk = "";
        do {
            try {
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (stk.equals("No")) {// Thoat nhap
                    break;
                }

                if (!Utils.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                } else {
                    // Stk chua ton tai
                    if (!bank.isAccountExisted(soCCCD, stk)) {
                        double balance = bank.nhapBalance();



                        // Them so du vao tai khoan
                        accountATM.setAccountNumber(stk);
                        accountATM.setBalance(balance);
                        accountATM.setCustomerId(soCCCD);

                        AccountDao.update(accountATM);

                        // Them giao dich vao lich su
                        accountATM.createTransaction(balance, Utils.getDateTime(), "DEPOSIT");

                        System.out.println("Da them tai khoan thanh cong");

                        break; // Thoat khoi vong lap khi thanh cong

                    }
                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                        System.out.println("So tai khoan da ton tai. Vui long nhap lai hoac nhap 'No' de thoat");

                    }
                }
            }
            catch (Exception e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                sc.next();
            }
        } while(!Utils.isValidAccountNumber(stk) || bank.isAccountExisted(soCCCD, stk));
    }
}
