import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<Customer> customers;

    public final Scanner sc;

    public Bank(Scanner sc) {
        this.id = String.valueOf(UUID.randomUUID()); //  Một giá trị duy nhất dài 128 bit
        this.customers = new ArrayList<>();
        this.sc = sc;
    }

    public void addCustomer(Customer newCustomer) {
        if(!isCustomerExisted(newCustomer.getCustomerId())) {// Neu so ID chua ton tai
            customers.add(newCustomer);
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    } // Lay danh sach cac nguoi dung cua ngan hang

    public String getId() {
        return id;
    } // Lay ID ngau nhien cua ngan hang

    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) { // Duyet qua so ID cua tung khach hang
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }

        return false;
    } // Xem nguoi dung da ton tai chua

    public void addAccount(String customerId, Account newAccount) {
        if (!isAccountExisted(customerId, newAccount.getAccountNumber())) { // Neu tai khoan hop le
            Customer customer = searchCustomerByCCCD(customerId);
            customer.addAccount(newAccount);
        }
    }


    // Xem tai khoan co hop le khong
    public  boolean isAccountExisted (String customerId, String newAccountNumber) {
        if (isCustomerExisted(customerId)) {// Neu ID hop le
            for (Customer customer : this.getCustomers()) {// Duyet qua tung khach hang trong ngan hang
                if (customer.isAccountExisted(newAccountNumber)) {// Neu tai khoan moi hop le voi tung khach hang duoc duyet
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public Customer searchCustomerByCCCD(String CCCD) {
        // Duyet qua tung khach hang va so sanh so CCCD
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(CCCD)) {
                return customer;
            }
        }

        return null;
    } // Tim khach hang theo so CCCD

    public List<Customer> searchCustomerByName(String name) {
        // Duyet qua tung khach hang va so sanh ten
        List<Customer> foundedCustomer = new ArrayList<>();

        for (Customer customer : customers) {// Duyet qua tung khach hang
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) { // Chuyen 2 ten ve chu thuong
                foundedCustomer.add(customer);
            }
        }

        return foundedCustomer;
    } // Tim khach hang theo ten

    public Account getAccountByAccountNumber(String accountNumber) {
        for (Customer customer : this.getCustomers()) { // Duyen qua toan bo customer
            return customer.getAccountByAccountNumber(accountNumber);
        }

        return null;
    }







//////////////// Method tuong tac voi nguoi dung ////////////////

    // Method tao giao dien
    public void hienThiGioiThieu() {
        System.out.println();
        System.out.println(Utils.getDivider()); // Tao vien
        System.out.println("| NGAN HANG SO | " + Asm03.AUTHOR +"@"+ Asm03.VERSION + " |");
        System.out.println(Utils.getDivider());
    }
    public void hienThiMenuChinh() { // In meny chinh cua chuong trinh
        System.out.println();
        System.out.println("| 1. Them khach hang");
        System.out.println("| 2. Them tai khoan cho khach hang");
        System.out.println("| 3. Hien thi danh sach khach hang");
        System.out.println("| 4. Tim theo CCCD");
        System.out.println("| 5. Tim theo ten khach hang");
        System.out.println("| 0. Thoat");

        System.out.println(Utils.getDivider());

    }


    public void myExit(int flag) { // Thoat chuong trinh
        System.out.println("Chuong trinh da ket thuc");
        System.out.println(Utils.getDivider());
        System.exit(flag);
    } // Thoat chuong trinh


    // Method ho tro kiem duyet
    public int nhapChucNangChinh() {
        int chucNang = -1;
        // Nhap chuc nang 0-5, neu khong thoa man cho nhap lai
        do {
            try {
                System.out.print("Chuc nang: ");

                chucNang = sc.nextInt();
                sc.nextLine();

                if (0 > chucNang || chucNang > 5) {
                    System.out.println("Nhap du lieu that bai. Vui long nhap lai");

                }
            }
            catch (Exception e) {
                System.out.println("Nhap du lieu that bai. Vui long nhap lai");
                sc.next();

            }
        } while (0 > chucNang || chucNang > 5);

        return chucNang;
    } // Yeu cau nguoi dung nhap cac chuc nang de su dung

    public String nhapCCCDTonTai() { // Nhap so CCCD ton tai
        String soCCCD = "";
        do {
            try {
                System.out.print("Nhap so CCCD: ");

                soCCCD = sc.nextLine();

                if (soCCCD.equals("No")) {// Dung nhap CCCD
                    return "";
                }
                else if (!(new Customer()).isValidId(soCCCD)) { // Kiem tra dinh dang CCCD
                    System.out.println("So CCCD khong hop le");
                    System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                }

                else if (!this.isCustomerExisted(soCCCD)) { // So CCCD khong ton tai
                    System.out.println("So CCCD khong ton tai");
                    System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                }
            }
            catch (Exception e) {
                System.out.println("So CCCD khong hop le");
                System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                sc.next();
            }
        } while(!this.isCustomerExisted(soCCCD));

        return soCCCD;
    } // Nhap so CCCD da ton tai trong ngan hang

    public String nhapCCCDKhongTonTai(Customer customer) {
        String soCCCD = "";

        // Nhap so CCCD chua ton tai
        do {
            try {
                System.out.print("Nhap so CCCD: ");

                soCCCD = sc.nextLine();

                if (soCCCD.equals("No")) {
                    return "";
                }
                else if (!customer.isValidId(soCCCD)) {// Kiem tra dinh dang CCCD
                    System.out.println("So CCCD khong hop le");
                    System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                }
                else if (this.isCustomerExisted(soCCCD)){ // So CCCD da ton tai
                    System.out.println("So CCCD da ton tai");
                    System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");
                }
            }
            catch (Exception e) {
                System.out.println("So CCCD khong hop le");
                sc.next();
            }
        } while(!customer.isValidId(soCCCD) || this.isCustomerExisted(soCCCD));

        return soCCCD;

    } // Nhap so CCCD chua ton tai trong ngan hang
    public String nhapTenKhachHang() {
        System.out.print("Nhap ten khach hang: ");
        return sc.nextLine();
    }



    // Method chuc nang
    public void themKhachHang() {
        Customer customer = new Customer();

        String tenKhachHang = nhapTenKhachHang();

        String soCCCD = this.nhapCCCDKhongTonTai(customer); // Nhap so CCCD chua ton tai

        if (soCCCD.equals("")) {// Thoat neu CCCD = null
            return;
        }

        // Tao ten va ID khach hang

        customer.setName(tenKhachHang);
        customer.setCustomerId(soCCCD);

        // Them khach hang vao ngan hang
        this.addCustomer(customer);
        System.out.println("Da them khach hang " + customer.getCustomerId() + " vao danh sach");



    }

    public void themTaiKhoanKhachHang() {
        // Nhap so CCCD da ton tai va xet tinh hop le
        String soCCCD = this.nhapCCCDTonTai();

        if (soCCCD.equals("")) {// Thoat neu CCCC = null
            return;
        }

        Account account = new Account();

        // Nhap so tai khoan
        String stk = "";
        do {
            try {
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (!account.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai");
                }
                else {
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
                        }
                        catch (Exception e) {
                            System.out.println("So du khong hop le. Vui long nhap lai");
                            sc.next();
                        }
                    } while(balance < 50000);



                    if (!this.isAccountExisted(soCCCD, stk)) {
                        // Them so du vao tai khoan
                        account.setAccountNumber(stk);
                        account.setBalance(balance);

                        this.addAccount(soCCCD, account);
                        System.out.println("Da them tai khoan thanh cong");

                        break; // Thoat khoi vong lap khi thanh cong

                    }
                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                        System.out.println("So tai khoan da ton tai. Vui long nhap lai");

                    }
                }
            }
            catch (Exception e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai");
                sc.next();
            }
        } while(!account.isValidAccountNumber(stk) || this.isAccountExisted(soCCCD,stk));
    }

    public void hienThiDanhSachKhachHang() {
        for (Customer customer : this.getCustomers()) { // Duyet tung khach hang
            customer.displayInformation();
            System.out.println();
        }
    }

    public void timTheoCCCD () {
        String soCCCD = this.nhapCCCDTonTai();

        if (soCCCD.equals("")) { // Thoat neu CCCC = null
            return;
        }
        Customer foundedCustomer = this.searchCustomerByCCCD(soCCCD);

        foundedCustomer.displayInformation();
    }

    public void timTheoTenKhachHang () {
        List<Customer> foundedCustomers;
        do {
            String tenKhachHang = nhapTenKhachHang();
            if (tenKhachHang.equals("No")) { // Dung nhap ten khach hang
                return;
            }

            foundedCustomers = this.searchCustomerByName(tenKhachHang);

            if (foundedCustomers.isEmpty()) { // Neu khong tim thay khach hang
                System.out.println("Ten khach hang khong ton tai");
                System.out.println("Vui long nhap lai hoac nhap 'No' de thoat");

            }
        } while(foundedCustomers.isEmpty());

        for (Customer customer : foundedCustomers) { // Duyet tung khach hang tim duoc
            customer.displayInformation();
            System.out.println();
        }
    }

}
