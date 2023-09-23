package homework49;

import java.util.List;

public class AccountCreationExceptions extends Exception {

    private List<String> errList;

    public AccountCreationExceptions(List<String> errors) {
        this.errList = errors;
    }

    public List<String> getErrors() {
        return errList;
    }
}
