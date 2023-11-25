import java.util.InputMismatchException;
import java.util.Scanner;

public class DigitalBank extends Bank{

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

            // Casting Customer => DigitalCustomer
            if(customer instanceof DigitalCustomer) {
                DigitalCustomer digitalCustomer = (DigitalCustomer) customer;
                return digitalCustomer.withdraw(accountNumber, amount);

            }



        }

        return false;
    }



    public void addCustomer(String customerId, String name) {
        Customer newCustomer = new DigitalCustomer();
        if (!isCustomerExisted(customerId)) {
            newCustomer.setCustomerId(customerId);
            newCustomer.setName(name);

            super.addCustomer(newCustomer);
        }
    }

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


    // Method chuc nang

    // Hien thi thong tin khach hang
    public void showCustomer() {
        Customer customer = this.searchCustomerByCCCD(Asm03.CUSTOMER_ID);

        if (customer != null) {
            customer.displayInformation();
        }
    }

    public void themTaiKhoanATM() {
        SavingsAccount accountATM = new SavingsAccount();

        // Nhap so tai khoan
        String stk = "";
        do {
            try {
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (stk.equals("No")) {// Thoat nhap
                    break;
                }

                if (!accountATM.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                } else {
                    // Stk chua ton tai
                    if (!super.isAccountExisted(Asm03.CUSTOMER_ID, stk)) {
                        double balance = nhapBalance();

                        // Them so du vao tai khoan
                        accountATM.setAccountNumber(stk);
                        accountATM.setBalance(balance);

                        super.addAccount(Asm03.CUSTOMER_ID, accountATM);
                        accountATM.setSaving(true); // Luu la tai khoan Savings

                        // Them giao dich vao lich su
                        Transaction transaction = new Transaction(stk, balance, Utils.getDateTime(), true);
                        accountATM.addTransactions(transaction);

                        System.out.println("Da them tai khoan thanh cong");

                        break; // Thoat khoi vong lap khi thanh cong

                    }
                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                        System.out.println("So tai khoan da ton tai. Vui long nhap lai hoac nhap 'No' de thoat");

                    }
                }
            }
            catch (Exception e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                sc.next();
            }
        } while(!accountATM.isValidAccountNumber(stk) || this.isAccountExisted(Asm03.CUSTOMER_ID, stk));
    }

    public void themTaiKhoanTinDung() {
        LoansAccount accountTinDung = new LoansAccount();

        // Nhap so tai khoan
        String stk = "";
        do {
            try {
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (stk.equals("No")) {// Thoat nhap
                    break;
                }

                if (!accountTinDung.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                } else {
                    // Tai khoan chua ton tai
                    if (!super.isAccountExisted(Asm03.CUSTOMER_ID, stk)) {
                        double balance = nhapBalance();

                        // Them so du vao tai khoan
                        accountTinDung.setAccountNumber(stk);
                        accountTinDung.setBalance(balance);
                        super.addAccount(Asm03.CUSTOMER_ID, accountTinDung);
                        accountTinDung.setSaving(false); // Danh dau la tai khoang Loans

                        // Them giao dich vao lich su
                        Transaction transaction = new Transaction(stk, balance, Utils.getDateTime(), true);
                        accountTinDung.addTransactions(transaction);
                        System.out.println("Da them tai khoan thanh cong");

                        break; // Thoat khoi vong lap khi thanh cong

                    }
                    else { // So tai khoan da ton tai => Yeu cau nhap lai
                        System.out.println("So tai khoan da ton tai. Vui long nhap lai hoac nhap 'No' de thoat");

                    }
                }
            }
            catch (Exception e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                sc.next();
            }
        } while(!accountTinDung.isValidAccountNumber(stk) || this.isAccountExisted(Asm03.CUSTOMER_ID, stk));
    }

    public void rutTien() {
        Account account = new Account();
        boolean flagStk;

        String stk;

        do{
            try {
                flagStk = true;
                System.out.print("Nhap ma STK gom 6 chu so: ");
                stk = sc.nextLine();

                if (stk.equals("No")) {// Thoat nhap
                    break;
                }

                if (!account.isValidAccountNumber(stk)) {
                    System.out.println("So tai khoan khong hop le. Vui long nhap lai hoac nhap 'No' de thoat");
                    flagStk = false;
                }
                else {
                    if (this.isAccountExisted(Asm03.CUSTOMER_ID, stk)) {
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
                                if (!withdraw(Asm03.CUSTOMER_ID, stk, withdrawAmount)) {
                                    System.out.println("GIAO DICH THAT BAI");
                                    break;
                                }

                                // Luu giao dich rut tien vao lich su
                                Account transactingAccount = getAccountByAccountNumber(stk);

                                Transaction transaction = new Transaction(stk, withdrawAmount * -1, Utils.getDateTime(), true);
                                transactingAccount.addTransactions(transaction);

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
        for (Customer customer : getCustomers()) {// Duyet tung khach hang va hien thi thong tin
            customer.displayInformation();
            for (Account account : customer.getAccounts()) {
                // Voi moi account cua tung khach hang in ra lich su giao dich
                for (Transaction transaction : account.getTransactions()) {
                    transaction.displayTransaction();
                }
            }
        }
    }


}
