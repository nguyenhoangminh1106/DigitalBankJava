package vn.funix.FX20520.java;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ASM01 {
    public static Scanner sc = new Scanner(System.in);
    public static final String AUTHOR = "FX20520";
    public static final String VERSION = "v1.0.0";

    public static void main(String[] args) {
        HashMap<String, String> bangTinhThanh = new HashMap<>();
        String[] info = new String[5];  // Array trich suat du lieu tu CCCD
        //[0]: Tinh thanh
        //[1]: Gioi tinh
        //[2]: 2 chu so dau nam sinh
        //[3]: 2 chu so cuoi nam sinh
        //[4]: Ma ngau nhien


        // Tao menu chinh
        createMenu();

        // Nhap chuc nang 1. Nhap CCCD va 0. Thoat
        int chucNang = chucNangTong();

        if (chucNang == 0) {
            System.out.println("Chuong trinh da ket thuc");
            createBorder();
            System.exit(1);
        }
        else {
            System.out.println();
            xacThuc();


            // Tai du lieu ma tinh thanh
            nhapMaTinhThanh(bangTinhThanh);

            System.out.println();
            String cccd = "";




            do { // Nhap so CCCD
                try {
                    System.out.print("Vui long nhap so CCCD: ");
                    cccd = sc.nextLine();
                } catch (Exception e) {
                    System.out.print("Vui long nhap so CCCD: ");
                }
            } while (!kiemTraCCCD(cccd, bangTinhThanh, info));

            nhapChucNangCCCD(info);
        }




    }

    // Tao vien phan tach cach phan
    public static void createBorder() { // Tao vien duoi cho giao dien ung dung
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();

    }

    // Tao menu chinh
    public static void createMenu() {
        createBorder();
        System.out.println("| NGAN HANG SO | " + ASM01.AUTHOR +"@"+ ASM01.VERSION + " |");
        createBorder();

        System.out.println("| 1. Nhap CCCD");
        System.out.println("| 0. Thoat");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static int chucNangTong() {
        int chucNang = -1;
        while(chucNang != 0 && chucNang != 1) {
            try {
                System.out.print("Chuc nang: ");
                chucNang = sc.nextInt();
                sc.nextLine();

                if (chucNang != 0 && chucNang != 1) { // Nhap sai so chuc nang
                    System.out.println("Vui long nhap 1 (Nhap CCCD) hoac 0 (Thoat):");
                }
            } catch (Exception e) {
                System.out.println("Vui long nhap lai");
                sc.next();
            }
        }
        return chucNang;
    }
    public static void xacThuc() {

        Random random = new Random();

        while (true) {
            try {
                System.out.println("Chon che do xac thuc bao mat: 1 (EASY) hoac 2 (HARD)");

                int cheDoBaoMat = sc.nextInt();
                sc.nextLine();

                if (cheDoBaoMat == 1) {// Xac thuc 3 chu so EASY
                    xacThucEASY(random);
                    break;
                }
                else if (cheDoBaoMat == 2) { // Xac thuc 6 ki tu HARD
                    xacThucHARD(random);
                    break;
                }
                else { // Loi chon che do
                    System.out.println("Chon che do that bai. Vui long nhap lai");
                }
            } catch (Exception e) {
                System.out.println("Chon che do that bai. Vui long nhap lai");
                sc.next();
            }
        }
    }

    public static void xacThucHARD (Random generator) {
        String nhapMaXacThuc = "";
        int lanNhapMaXacThuc = 0; // So lan nhap ma xac thuc (<=5)
        String maXacThuc = "";

        do {
            try {
                if (lanNhapMaXacThuc >= 1 && lanNhapMaXacThuc < 5) {// Nhap sai ma xac thuc
                    System.out.println("Ma xac thuc khong dung. Vui long thu lai");
                }
                else if (lanNhapMaXacThuc >= 5) { // Nhap qua nhieu lan
                    System.out.println("Xac thuc that bai");
                    createBorder();
                    System.exit(-1);
                }

                maXacThuc = randomAlphanumeric(6, generator); // Tao ma xac thuc ngau nhien 6 ki tu

                System.out.println("Nhap ma xac thuc: " + maXacThuc);
                nhapMaXacThuc = sc.nextLine();
                lanNhapMaXacThuc++;

            } catch (Exception e) {
                sc.next();
            }

        }  while (!nhapMaXacThuc.equals(maXacThuc));
    }

    public static void xacThucEASY(Random generator) {
        int nhapMaXacThuc = 0;
        int lanNhapMaXacThuc = 0; // So lan nhap ma xac thuc
        int maXacThuc = -1;

        do {
            try {
                if (lanNhapMaXacThuc >= 1 && lanNhapMaXacThuc < 5) { // Nhap sai ma xac thuc
                    System.out.println("Ma xac thuc khong dung. Vui long thu lai");
                }
                else if (lanNhapMaXacThuc == 5) { // Nhap qua nhieu lan
                    System.out.println("Xac thuc that bai");
                    createBorder();
                    System.exit(-1);
                }


                maXacThuc = generator.nextInt(900) + 100; // Tao ma xac thuc ngau nhien 3 chu so
                System.out.println("Nhap ma xac thuc: " + maXacThuc);

                lanNhapMaXacThuc++;

                nhapMaXacThuc = sc.nextInt();
                sc.nextLine();

            } catch (Exception e) {
                sc.next();
            }

        }  while (nhapMaXacThuc != maXacThuc);
    }


    // Tai du lieu ma tinh thanh
    public static void nhapMaTinhThanh(HashMap<String, String> data) {
        data.put("001", "Ha Noi");
        data.put("002", "Ha Giang");
        data.put("004", "Cao Bang");
        data.put("006", "Bac Kan");
        data.put("008", "Tuyen Quang");
        data.put("010", "Lao Cai");
        data.put("011", "Dien Bien");
        data.put("012", "Lai Chau");
        data.put("014", "Son La");
        data.put("015", "Yen Bai");
        data.put("017", "Hoa Binh");
        data.put("019", "Thai Nguyen");
        data.put("020", "Lang Son");
        data.put("022", "Quang Ninh");
        data.put("024", "Bac Giang");
        data.put("025", "Phu Tho");
        data.put("026", "Vinh Phuc");
        data.put("027", "Bac Ninh");
        data.put("030", "Hai Duong");
        data.put("031", "Hai Phong");
        data.put("033", "Hung Yen");
        data.put("034", "Thai Binh");
        data.put("035", "Hai Nam");
        data.put("036", "Nam Dinh");
        data.put("037", "Ninh Binh");
        data.put("038", "Thanh Hoa");
        data.put("040", "Nghe An");
        data.put("042", "Ha Tinh");
        data.put("044", "Quang Binh");
        data.put("045", "Quang Tri");
        data.put("046", "Thua Thien Hue");
        data.put("048", "Da Nang");
        data.put("049", "Quang Nam");
        data.put("051", "Quang Ngai");
        data.put("052", "Binh Dinh");
        data.put("054", "Phu Yen");
        data.put("056", "Khanh Hoa");
        data.put("058", "Ninh Thuan");
        data.put("060", "Binh Thuan");
        data.put("062", "Kon Tum");
        data.put("064", "Gia Lai");
        data.put("066", "Dac Lak");
        data.put("067", "Dak Nong");
        data.put("068", "Lam Dong");
        data.put("070", "Binh Phuoc");
        data.put("072", "Tay Ninh");
        data.put("074", "Binh Duong");
        data.put("075", "Dong Nai");
        data.put("077", "Ba Ria - Vung Tau");
        data.put("079", "Ho Chi Minh");
        data.put("080", "Long An");
        data.put("082", "Tien Giang");
        data.put("083", "Ben Tre");
        data.put("084", "Tra Vinh");
        data.put("086", "Vinh Long");
        data.put("087", "Dong Thap");
        data.put("089", "An Giang");
        data.put("091", "Kien Giang");
        data.put("092", "Can Tho");
        data.put("093", "Hau Giang");
        data.put("094", "Soc Trang");
        data.put("095", "Bac Lieu");
        data.put("096", "Ca Mau");


    }

    // Trich suat gioi tinh gioi tinh
    public static String[] gioiTinh(int ma) {
        String[] infoGioiTinh = new String[2];
        if (ma % 2 == 0) {
            infoGioiTinh[0] = "Nam";
            infoGioiTinh[1] = String.valueOf((ma)/2 + 19); // 2 chu so dau cua nam sinh
        }
        else {
            infoGioiTinh[0] = "Nu";
            infoGioiTinh[1] = String.valueOf((ma-1)/2 + 19); // 2 chu so dau cua nam sinh
        }
        return infoGioiTinh;
    }

    public static boolean kiemTraCCCD(String cccd, HashMap<String, String> data, String[] info) {
        String regex = "^\\d{12}$"; // Chuoi 12 chu so

        if (Pattern.matches(regex, cccd)) {
            // Tach CCCD thanh cac substring de trich xuat du lieu
            String maTinhThanh = cccd.substring(0, 3);
            String maGioiTinh = cccd.substring(3, 4);
            String maNamSinh = cccd.substring(4, 6);
            String maNgauNhien = cccd.substring(6);

            if (data.containsKey(maTinhThanh)) { // Ma tinh thanh ton tai
                info[0] = data.get(maTinhThanh);

                int soGioiTinh = Integer.parseInt(maGioiTinh);
                info[1] = gioiTinh(soGioiTinh)[0]; // Gioi tinh
                info[2] = gioiTinh(soGioiTinh)[1]; // 2 chu so dau nam sinh

                info[3] = maNamSinh; //2 chu so cuoi nam sinh
                info[4] = maNgauNhien;
            }
            else {
                System.out.println("So CCCD khong hop le");
                return false;
            }

            return true;
        }
        else {
            System.out.println("So CCCD khong hop le");
            return false;
        }
    }

    // Hien thi menu chon chuc nang
    public static void menuChucNang() {
        System.out.println();
        System.out.println("| 1. Kiem tra noi sinh");
        System.out.println("| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("| 3. Kiem tra so ngau nhien");
        System.out.println("| 0. Thoat");
        System.out.print("Chuc nang: ");
    }

    public static void hienThiNoiSinh(String data) {
        System.out.println("Noi sinh: " + data);
    }

    public static void hienThiGioiTinhNamSinh(String gioiTinh, String namSinh1, String namSinh2) {
        // namSinh1: 2 chu so dau nam sinh
        // namSinh2: 2 chu so sau nam sinh
        System.out.println("Gioi tinh: " + gioiTinh + " | " + namSinh1 + namSinh2);
    }

    public static void hienThiSoNgauNhien(String data) {
        System.out.println("So ngau nhien: " + data);
    }

    public static void nhapChucNangCCCD(String[] infoCCCD) {
        while(true) {
            try {
                menuChucNang();
                int chucNangCCCD = sc.nextInt();

                if (chucNangCCCD == 1) { // Hien thi noi sinh
                    hienThiNoiSinh(infoCCCD[0]);
                }
                else if (chucNangCCCD == 2) { // Hien thi gioi tinh, nam sinh
                    hienThiGioiTinhNamSinh(infoCCCD[1], infoCCCD[2], infoCCCD[3]);
                }
                else if (chucNangCCCD == 3) { // Hien thi so ngau nhien
                    hienThiSoNgauNhien(infoCCCD[4]);
                }
                else if (chucNangCCCD == 0) { // Thoat chuong trinh
                    System.out.println("Chuong trinh da ket thuc");
                    createBorder();
                    System.exit(1);
                }
                else {
                    System.out.println("Khong tim thay chuc nang. Vui long nhap lai");
                }

            }
            catch (Exception e ) {
                System.out.println("Khong tim thay chuc nang. Vui long nhap lai");
                sc.next();
            }
        }
    }

    public static String randomAlphanumeric(int length, Random generator) {
        // Array chu os va chu cai
        char[] alphaNumeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int alphaNumericLength = alphaNumeric.length;
        String random = "";

        for (int i = 0; i < length; i++) {
            int index = generator.nextInt(alphaNumericLength); // Chon random mot chi so cua array
            random += String.valueOf(alphaNumeric[index]);
        }
        return random;
    }
}

