import java.io.Serializable;
import java.util.List;

public class AccountDao implements Serializable {
    private static final long serialVersionUID = 1L;
    private final static String FILE_PATH = "store/accounts.dat";

    public static void save (List<Account> accounts) throws Exception {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static void update(Account editAccount) throws Exception {
        List<Account> accounts = list();

        // Lay index neu tai khoan da toan tai (neu khong thi tra -1)
        int index = UpdateAccountThread.findExistingAccount(accounts, editAccount); // List accounts sau khi cap nhat

        if (index < 0) {
            accounts.add(editAccount);
        }
        else {
            accounts.set(index, editAccount); // Thay account o index bang edit account
        }

        save(accounts);
    }
}
