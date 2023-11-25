public class CustomerIdNotValidException extends Throwable {
    public CustomerIdNotValidException(String message) {
        System.out.println(message);
    }

    static void validateId(String cccd)  throws CustomerIdNotValidException {
        if (!Utils.isValidId(cccd)) {
            throw new CustomerIdNotValidException("So CCCD khong hop le");
        }
    }
}
