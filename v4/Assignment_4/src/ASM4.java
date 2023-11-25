import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ASM4 {
    public static final String AUTHOR = "FX20520";
    public static final String VERSION = "v4.0.0";

    private static final Scanner sc = new Scanner(System.in);

    private static final DigitalBank bank = new DigitalBank(sc);

    public static void main(String[] args) {

        while (true) {
            hienThiGioiThieu();
            hienThiMenuChinh();

            int chucNang = bank.nhapChucNangChinh();

            switch (chucNang){
                case 0:
                    Utils.myExit(1);
                    break;
                case 1:
                    bank.showCustomer();
                    break;
                case 2:
                    try {
                        bank.themKhachHang();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    bank.themTaiKhoanATM();
                    break;
                case 4:
                    bank.chuyenTien();
                    break;
                case 5:
                    bank.rutTien();
                    break;
                case 6:
                    bank.lichSuGiaoDich();
                    break;
            }

        }

    }

    // Method tao giao dien
    public static void hienThiGioiThieu() {
        System.out.println();
        System.out.println(Utils.getDivider()); // Tao vien
        System.out.println("| NGAN HANG SO | " + ASM4.AUTHOR +"@"+ ASM4.VERSION + " |");
        System.out.println(Utils.getDivider());
    }

    public static void hienThiMenuChinh() { // In meny chinh cua chuong trinh
        System.out.println();
        System.out.println("| 1. Xem danh sach khach hang");
        System.out.println("| 2. Nhap danh sach khach hang");
        System.out.println("| 3. Them tai khoan ATM");
        System.out.println("| 4. Chuyen tien");
        System.out.println("| 5. Rut tien");
        System.out.println("| 6. Tra cuu lich su giao dich");
        System.out.println("| 0. Thoat");

        System.out.println(Utils.getDivider());

    }


}

