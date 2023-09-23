package exceptions_continue;

import java.util.List;

public class AccountCreateException extends Exception {
    private List<String> errorsList;

    public AccountCreateException(List<String> errorsList) {
        this.errorsList = errorsList;
    }

    public List<String> getErrorsList() {
        return errorsList;
    }
}
