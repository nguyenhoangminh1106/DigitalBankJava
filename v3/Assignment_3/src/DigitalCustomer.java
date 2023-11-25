public class DigitalCustomer extends Customer{
    public boolean withdraw(String accountNumber, double amount) {

        if (isAccountExisted(accountNumber)) {// Stk ton tai thi lay tai khoan
            Account account = getAccountByAccountNumber(accountNumber);

            if (account instanceof  SavingsAccount) {// La tai khoang Savings

                //Casing Account => SavingsAccount
                SavingsAccount savingsAccount = (SavingsAccount) account;
                if(savingsAccount.withdraw(amount)) {// Rut thanh cong thi in ra bien lai
                    savingsAccount.log(amount);
                    return true;
                }
                else {
                    return false;
                }
            }
            else {// La tai khoan Loans
                // Cast Account => LoansAccount
                LoansAccount loansAccount = (LoansAccount) account;
                if(loansAccount.withdraw(amount)) {// Rut thanh cong thi in ra bien lai
                    loansAccount.log(amount);
                    return true;
                }
                else {
                    return false;
                }
            }

        }

        return false;
    }


}
