import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {
    // Tao vien cho hien thi cua chuong trinh
    public static String getDivider() {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            border.append("-");
        }
        return border.toString();
    }

    // Lay thoi gian hien tai
    public static String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return java.time.LocalDateTime.now().format(formatter);
    }

    // Chuyen dang Double sang dinh dang vnd
    public static String formatBalance(double amount) {
        String balanceString = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(amount);
        return balanceString;
    }

    public static void myExit(int flag) { // Thoat chuong trinh
        System.out.println("Chuong trinh da ket thuc");
        System.out.println(Utils.getDivider());
        System.exit(flag);
    } // Thoat chuong trinh


    // Method kiem tra du lieu
    public static boolean isValidAccountNumber (String accountNumber) {
        String regex = "^\\d{6}$"; // Chuoi 6 chu so

        try {
            return Pattern.matches(regex, accountNumber);
        }
        catch (Exception e) {
            return false;
        }
    } // Xem stk co hop le khong

    // Kiem tra xem ID co hop le khong
    public static boolean isValidId(String customerId) {
        // Ma tinh thanh hop le trong CCCD
        String[] idKeyList = {"070", "072", "030", "074", "031", "075", "033", "077", "034", "035", "079", "036", "037", "038", "080", "082", "083", "040", "084", "042", "086", "087", "044", "001", "045", "089", "002", "046", "004", "048", "049", "006", "008", "091", "092", "093", "094", "051", "095", "052", "096", "010", "054", "011", "012", "056", "014", "058", "015", "017", "019", "060", "062", "020", "064", "022", "066", "067", "024", "068", "025", "026", "027"};
        String regex = "^\\d{12}$"; // Chuoi 12 chu so

        if (Pattern.matches(regex, customerId)) {
            // Kiem tra xem ma tinh thanh cua Id co hop le khong
            String maTinhThanh = customerId.substring(0, 3); // Trich xuat ma tinh thanh

            for (String idKey : idKeyList) {
                if (idKey.equals(maTinhThanh)) {
                    return true;
                }
            }
        }

        return false;
    }
}
