import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class CustomerDao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final static String FILE_PATH = "store/customers.dat";

    public static void save (List<Customer> customers) throws Exception {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
