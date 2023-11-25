import java.util.regex.Pattern;

public class User {
    private String name;
    private String customerId;

    public User() {
    }

    // Cai dat getter va setter cho name va customerId
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        if(isValidId(customerId)) {
            this.customerId = customerId;
        }
    }

    // Kiem tra xem ID co hop le khong
    public boolean isValidId(String customerId) {
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
