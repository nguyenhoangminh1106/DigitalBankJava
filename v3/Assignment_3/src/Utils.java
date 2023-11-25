import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

}
