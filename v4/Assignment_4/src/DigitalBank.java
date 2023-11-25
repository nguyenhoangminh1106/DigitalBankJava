import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public DigitalBank(Scanner sc) {
        super(sc);
    }

    // Method ho tro chuc nang

    @Override
    public Customer searchCustomerByCCCD(String CCCD) {

        return super.searchCustomerByCCCD(CCCD);
    }

    public boolean withdraw(String customerId, String accountNumber, double amount) {
        if (isCustomerExisted(customerId)) {// Neu khach hang ton tai
            Customer customer = searchCustomerByCCCD(customerId);
            return customer.withdraw(accountNumber, amount);
        }
        return false;
    }

    public boolean transfer(String customerId, String accountNumber, Account receiveAccount, double amount) {
        if (isCustomerExisted(customerId)) {// Neu khach hang ton tai
            Customer customer = searchCustomerByCCCD(customerId);
            return customer.transfer(accountNumber, receiveAccount, amount);
        }

        return false;
    }






    // Method chuc nang

    // Hien thi thong tin khach hang

    public void showCustomer() {
        List<Customer> customers = CustomerDao.list();

        if (customers.isEmpty()) {
            System.out.println("Chua co khach hang nao trong danh sach");
            return;
        }

        for (Customer customer : customers) { // Duyet tung khach hang

            customer.displayInformation();
            System.out.println();
        }
    }

    public void themKhachHang() {
        System.out.print("Nhap duong dan den tep: ");
        String customersFileName = sc.nextLine();

        List<List<String>> customersList = TextFileService.readFile(customersFileName);
        List<Customer> customers = new ArrayList<>();

        for (List<String> user : customersList) {
            String cccd = user.get(0);

            String soCCCD = nhapCCCDKhongTonTai(cccd);

            if (!soCCCD.equals("Error")) {
                //          Tao ten va ID khach hang
                Customer customer = new Customer(user);

                // Them khach hang vao ngan hang
                customers.add(customer);

                try {
                    CustomerDao.save(customers);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public void themTaiKhoanATM() {
        Account.input(sc, this);
    }

    public void chuyenTien() {
        boolean flagStk;

        // Nhap so CCCD da ton tai va xet tinh hop le
        String soCCCD = nhapCCCDTonTai();

        if (soCCCD.equals("")) {// Thoat neu CCCC = null
            return;
        }

        Customer customer = searchCustomerByCCCD(soCCCD);

        customer.displayInformation();

        String stk;


        do{
            try {
                flagStk = true;
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (stk.equals("No")) {// Thoat nhap
                    break;
                }

                if (!Utils.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                    flagStk = false;
                }
                else {
                    if (customer.isAccountExisted(stk)) {
                        String stkNhan;
                        boolean flagStkNhan;
                        do{
                            try {
                                flagStkNhan = true;
                                System.out.print("Nhap ma STK nhan: ");
                                stkNhan = sc.nextLine();

                                if (stkNhan.equals("exit")) {// Thoat nhap
                                    break;
                                }

                                if (!Utils.isValidAccountNumber(stkNhan)) {
                                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'exit' de thoat");
                                    flagStkNhan = false;
                                }
                                else {
                                    if (this.isAccountExisted(soCCCD, stkNhan)) {
                                        if (stkNhan.equals(stk)) {
                                            System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'exit' de thoat");
                                            flagStkNhan = false;
                                        }
                                        else {
                                            // Nhap so du
                                            double transferAmount;
                                            boolean flagAmount; // Trang thai cua so tien nhap
                                            do {
                                                try {
                                                    System.out.print("Nhap so tien ban muon chuyen: ");
                                                    transferAmount = sc.nextDouble();
                                                    sc.nextLine();
                                                    flagAmount = true; // Nhap thanh cong
                                                    if (transferAmount == -1){// Thoat nhap so tien
                                                        break;
                                                    }

                                                    System.out.print("Xac nhan thuc hien chuyen " + Utils.formatBalance(transferAmount) + " tu tai khoan [" + stk + "] den tai khoan [" + stkNhan + "] (Y/N): " );
                                                    String confirm = sc.nextLine();

                                                    if (confirm.toUpperCase().equals("Y")) {
                                                        System.out.println("Hoan tat xac nhan");
                                                    }
                                                    else if (confirm.toUpperCase().equals("N")) {
                                                        break;
                                                    }
                                                    else {
                                                        System.out.println("Xac nhan that bai");
                                                        break;
                                                    }


                                                    // Luu giao dich rut tien vao lich su
                                                    Account transactingAccount = getAccountByAccountNumber(stk);
                                                    Account transactedAccount = getAccountByAccountNumber(stkNhan);

                                                    // So tien khong hop le
                                                    if (!transfer(soCCCD, stk, transactedAccount, transferAmount)) {
                                                        System.out.println("GIAO DICH THAT BAI");
                                                        break;
                                                    }

                                                    try {
                                                        AccountDao.update(transactedAccount);
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }

                                                    transactingAccount.createTransaction(transferAmount * -1, Utils.getDateTime(), "TRANSFERS");
                                                    transactedAccount.createTransaction(transferAmount, Utils.getDateTime(), "TRANSFERS");


                                                } catch (InputMismatchException e) {
                                                    System.out.println("So tien rut khong hop le. Vui long nhap lai hoac nhap '-1' de thoat");
                                                    sc.next();

                                                    flagAmount = false;
                                                }
                                            } while(!flagAmount);
                                        }

                                    }
                                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                                        System.out.println("So tai khoan khong ton tai. Vui long nhap lai hoac nhap 'exit' de thoat");
                                        flagStkNhan = false;
                                    }
                                }
                            }
                            catch (InputMismatchException e) {
                                System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'exit' de thoat");
                                flagStkNhan = false;
                            }
                        } while(!flagStkNhan);
                    }
                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                        System.out.println("So tai khoan khong ton tai. Vui long nhap lai hoac nhap 'No' de thoat");
                        flagStk = false;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                flagStk = false;
            }
        } while(!flagStk);
    }

    public void rutTien() {
        boolean flagStk;

        // Nhap so CCCD da ton tai va xet tinh hop le
        String soCCCD = nhapCCCDTonTai();
        if (soCCCD.equals("")) {// Thoat neu CCCC = null
            return;
        }

        Customer customer = searchCustomerByCCCD(soCCCD);
        customer.displayInformation();

        String stk;

        do{
            try {
                flagStk = true;
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (stk.equals("No")) {// Thoat nhap
                    break;
                }

                if (!Utils.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                    flagStk = false;
                }
                else {
                    if (customer.isAccountExisted(stk)) {
                        // Nhap so du
                        double withdrawAmount;
                        boolean flagAmount; // Trang thai cua so tien nhap
                        do {
                            try {
                                System.out.print("Nhap so tien ban muon rut: ");
                                withdrawAmount = sc.nextDouble();
                                sc.nextLine();
                                flagAmount = true; // Nhap thanh cong
                                if (withdrawAmount == -1){// Thoat nhap so tien
                                    break;
                                }

                                // So tien khong hop le
                                if (!withdraw(soCCCD, stk, withdrawAmount)) {
                                    System.out.println("GIAO DICH THAT BAI");
                                    break;
                                }

                                // Luu giao dich rut tien vao lich su
                                Account transactingAccount = getAccountByAccountNumber(stk);

                                transactingAccount.createTransaction(withdrawAmount * -1, Utils.getDateTime(), "WITHDRAW");


                            } catch (InputMismatchException e) {
                                System.out.println("So tien rut khong hop le. Vui long nhap lai hoac nhap '-1' de thoat");
                                sc.next();

                                flagAmount = false;
                            }
                        } while(!flagAmount);

                    }
                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                        System.out.println("So tai khoan khong ton tai. Vui long nhap lai hoac nhap 'No' de thoat");
                        flagStk = false;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                flagStk = false;
            }
        } while(!flagStk);
    }

    public void lichSuGiaoDich() {
        String cccd = nhapCCCDTonTai();
        Customer customer = searchCustomerByCCCD(cccd);

        if (customer != null) {
            customer.displayInformation();

            for (Account account : customer.getAccounts()) {// Duyet tung khach hang va hien thi thong tin
                account.displayTransactionsList();
            }
        }
    }


}
