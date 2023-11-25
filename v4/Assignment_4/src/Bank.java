import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;
import java.util.UUID;

public class Bank implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String name;

    public final Scanner sc;

    public Bank(Scanner sc) {
        this.name = "NGAN HANG SO";
        this.id = String.valueOf(UUID.randomUUID());
        this.sc = sc;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    } // Lay ID ngau nhien cua ngan hang

    public boolean isCustomerExisted(String customerId) {
        // Duyet qua so ID cua tung khach hang
        return CustomerDao.list().stream()
                .anyMatch(customer -> customer.getCustomerId().equals(customerId));
    } // Xem nguoi dung da ton tai chua


    // Method ho tro chuc nang
    public  boolean isAccountExisted (String customerId, String newAccountNumber) {
        if (isCustomerExisted(customerId)) {// Neu ID hop le
            return CustomerDao.list().stream()
                    .anyMatch(customer -> customer.isAccountExisted(newAccountNumber));
        }
        return false;
    }

    public Customer searchCustomerByCCCD(String CCCD) {
        // Duyet qua tung khach hang va so sanh so CCCD
        for (Customer customer : CustomerDao.list()) {
            if ((customer.getCustomerId().equals(CCCD))) {
                    return customer;

            }
        }

        return null;
    } // Tim khach hang theo so CCCD


    public Account getAccountByAccountNumber(String accountNumber) {
        for (Customer customer : CustomerDao.list()) { // Duyen qua toan bo customer
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }

        return null;
    }


    // Method nhap du lieu
    public String nhapCCCDTonTai() { // Nhap so CCCD ton tai
        String soCCCD;
        do {
            System.out.print("Nhap so CCCD: ");

            soCCCD = sc.nextLine();

            if (soCCCD.equals("No")) {// Dung nhap CCCD
                return "";
            }


            try {
                CustomerIdNotValidException.validateId(soCCCD);
            } catch (CustomerIdNotValidException e) {
                System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                continue;

            }

            if (!this.isCustomerExisted(soCCCD)) { // So CCCD khong ton tai
                    System.out.println("So CCCD khong ton tai");
                    System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                }
        } while(!this.isCustomerExisted(soCCCD));

        return soCCCD;
    } // Nhap so CCCD da ton tai trong ngan hang

    public String nhapCCCDKhongTonTai(String soCCCD) {


        // Nhap so CCCD chua ton tai
            try {
                if (!Utils.isValidId(soCCCD)) {// Kiem tra dinh dang CCCD
                    System.out.println("So CCCD " + soCCCD + " khong hop le, them khach hang khong thanh cong");
                    return "Error";
                }
                if (this.isCustomerExisted(soCCCD)){ // So CCCD da ton tai
                    System.out.println("So CCCD " + soCCCD + " da ton tai, them khac hang khong thanh cong");
                    return "Error";
                }
                else {
                    System.out.println("Da them khach hang " + soCCCD + " vao danh sach");

                }
            }
            catch (Exception e) {
                System.out.println("So CCCD " + soCCCD + " khong hop le, them khach hang khong thanh cong");
                return "Error";
            }

        return soCCCD;

    } // Nhap so CCCD chua ton tai trong ngan hang

    public int nhapChucNangChinh() {
        int chucNang = -1;
        // Nhap chuc nang 0-5, neu khong thoa man cho nhap lai
        do {
            try {
                System.out.print("Chuc nang: ");

                chucNang = sc.nextInt();
                sc.nextLine();

                if (0 > chucNang || chucNang > 6) {
                    System.out.println("Nhap du lieu that bai. Vui long nhap lai");

                }
            }
            catch (Exception e) {
                System.out.println("Nhap du lieu that bai. Vui long nhap lai");
                sc.next();

            }
        } while (0 > chucNang || chucNang > 6);

        return chucNang;
    } // Yeu cau nguoi dung nhap cac chuc nang de su dung

    public double nhapBalance() {
        // Nhap so du
        double balance = 0;
        do {
            try {
                System.out.print("Nhap so du: ");
                balance = sc.nextDouble();
                sc.nextLine();

                if (balance < 50000) {
                    System.out.println("So du tai khoan phai it nhat 50_000 vnd. Vui long nhap lai");
                }
            } catch (Exception e) {
                System.out.println("So du khong hop le. Vui long nhap lai");
                sc.next();
            }
        } while (balance < 50000);

        return balance;
    }
}
