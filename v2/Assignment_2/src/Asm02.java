import java.util.Scanner;

public class Asm02 {
    private static final Scanner sc = new Scanner(System.in);

    private static final Bank bank = new Bank(sc);

    public static final String AUTHOR = "FX20520";
    public static final String VERSION = "v2.0.0";


    public static void main(String[] args) {

        while (true) {// Chuong trinh chay lien tuc cho den khi thoat
            System.out.println();
            bank.hienThiGioiThieu();

            bank.hienThiMenuChinh();

            int chucNang = bank.nhapChucNangChinh(); // Chuc nang duoc nhap vao

            if (chucNang == 1) {
                bank.themKhachHang();
            }
            else if (chucNang == 2) {
                bank.themTaiKhoanKhachHang();
            }
            else if (chucNang == 3) {
                bank.hienThiDanhSachKhachHang();
            }
            else if (chucNang == 4) {
                bank.timTheoCCCD();
            }
            else if (chucNang == 5) {
                bank.timTheoTenKhachHang();
            }
            else if (chucNang == 0) {// Thoat
                bank.myExit(1);
            }

        }
    }
}
