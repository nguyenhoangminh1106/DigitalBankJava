import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final static String FILE_PATH = "store/transactions.dat";

    public static void save (List<Transaction> transactions) throws Exception {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static void update(Transaction editTransaction) throws Exception {
        List<Transaction> transactions = list();

        List<Transaction> updatedTransactions = new ArrayList<>(); // List accounts sau khi cap nhat

        // Them toan bo transactions cu va transaction moi
        updatedTransactions.addAll(transactions);
        updatedTransactions.add(editTransaction);

        save(updatedTransactions);
    }
}
