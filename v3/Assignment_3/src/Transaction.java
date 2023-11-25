import java.util.UUID;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private Boolean status;

    public Transaction(String accountNumber, double amount, String time, Boolean status) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status; // Giao dich thanh cong hay that bai
    }

    // Setter and Getter
    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Hien thi lich su giao dich
    public void displayTransaction() {
        // Trang thai giao dich
        String stringStatus = getStatus() ? "[GD]  ": "ERROR ";
        // So tien giao dich theo dinh dang vnd
        String stringAmount = Utils.formatBalance(getAmount());
        System.out.printf("%15s %41s %30s\n", stringStatus + getAccountNumber() + " |", stringAmount + " |", getTime());
    }
}
