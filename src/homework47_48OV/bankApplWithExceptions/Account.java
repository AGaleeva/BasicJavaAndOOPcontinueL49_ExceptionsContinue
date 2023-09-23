package homework47_48OV.bankApplWithExceptions;

public class Account {
    private String iban;

    public Account(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "IBAN: " + iban;
    }
}
