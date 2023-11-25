import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String customerId;

    public User() {
    }

    // Cai dat getter va setter cho name va customerId
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        if(Utils.isValidId(customerId)) {
            this.customerId = customerId;
        }
    }


}
