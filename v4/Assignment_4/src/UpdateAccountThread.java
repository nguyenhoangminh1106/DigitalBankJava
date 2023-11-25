import java.util.List;
import java.util.concurrent.*;

public class UpdateAccountThread {
    private static final int MAX_THREAD = 3;

    public static Integer findExistingAccount(List<Account> list, Account target) {
        // Tao MAX_THREAD thread trong pool
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

        // Mang chua cac index tim duoc khi chia thanh 4 subArray
        Future<Integer>[] futureResults = new Future[4];

        Account[] arr = new Account[list.size()];
        list.toArray(arr); // chuyen list => array
        int size = arr.length;
        int chunkSize = size / 4; // Do dai cua mot khoang
        int remaining = size % 4; // Phan du khi chi mang thanh 4 phan
        int startIndex = 0;

        for (int i = 0; i < 4; i++) {
            // Tinh index cuoi
            int endIndex = startIndex + chunkSize + (remaining-- > 0 ? 1 : 0);
            futureResults[i] = executorService.submit(new IndexFinder(startIndex, endIndex, arr, target));

            // Tiep tuc voi subArray tiep theo (cap nhap index dau)
            startIndex = endIndex;
        }

        for (int i = 0; i < 4; i++) {
            try {
                int result = futureResults[i].get();
                if (result >= 0) { // Neu ton tai 1 index >0 thi tra luon ve ket qua
                    executorService.shutdownNow();
                    return result;
                }
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }
        executorService.shutdownNow();
        return -1; // Khong ton tai phan tu bi trung lap
    }


    static class IndexFinder implements Callable<Integer> {
        private final int startIndex;
        private final int endIndex;
        private final Account[] arr;
        private final Account target;

        IndexFinder(int startIndex, int endIndex, Account[] arr, Account target) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.arr = arr;
            this.target = target;
        }

        @Override
        public Integer call() {
            for (int i = startIndex; i < endIndex; i++) {
                // Neu co phan tu bi lap thi tra ve index
                if (arr[i].getAccountNumber().equals(target.getAccountNumber())) {
                    return i;
                }
            }
            return -1; // Khong bi lap tra ve -1
        }
    }
}