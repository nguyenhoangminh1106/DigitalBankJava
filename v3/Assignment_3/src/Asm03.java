import java.util.Scanner;

public class Asm03 {
    public static final String AUTHOR = "FX20520";
    public static final String VERSION = "v3.0.0";
    public static final String CUSTOMER_ID = "001215000001";
    public static final String CUSTOMER_NAME = "FUNIX";

    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank(sc);


    public static void main(String[] args) {
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        while (true) {
            activeBank.hienThiGioiThieu();
            hienThiMenuChinh();
            int chucNang = activeBank.nhapChucNangChinh();


            switch (chucNang) {
                case 0:
                    activeBank.myExit(1);
                    break;
                case 1:
                    activeBank.showCustomer();
                    break;
                case 2:
                    activeBank.themTaiKhoanATM();
                    break;
                case 3:
                    activeBank.themTaiKhoanTinDung();
                    break;
                case 4:
                    activeBank.rutTien();
                    break;
                case 5:
                    activeBank.lichSuGiaoDich();
                    break;
            }
        }

    }

    public static void hienThiMenuChinh() {
        System.out.println();
        System.out.println("| 1. Thong tin khach hang");
        System.out.println("| 2. Them tai khoan ATM");
        System.out.println("| 3. Them tai khoan tinh dung");
        System.out.println("| 4. Rut tien");
        System.out.println("| 5. Lich su giao dich");
        System.out.println("| 0. Thoat");

        System.out.println(Utils.getDivider());
    }


}
