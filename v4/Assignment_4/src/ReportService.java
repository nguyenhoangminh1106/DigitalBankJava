public interface ReportService {
    void logWithdraw(double amount); // In ra bien lai
    void logTransfer(Account receiveAccount, double amount);
}
