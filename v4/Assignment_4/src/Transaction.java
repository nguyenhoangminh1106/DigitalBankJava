import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String time;

    private final String type;

    public Transaction(String accountNumber, double amount, String time, String type) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.type = type;
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

    public String getType() {
        return type;
    }

    // Hien thi lich su giao dich
    public String toString() {
        // So tien giao dich theo dinh dang vnd
        String stringAmount = Utils.formatBalance(getAmount());
        return String.format("%15s %11s %18s %41s", "[GD]" + getAccountNumber() + " |", getType() + " |", stringAmount + " |", getTime());
    }
}
