package homework49;

public class Account {
    private String iban;
    private Person owner;

    public Account(String iban, Person owner) {
        this.iban = iban;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "IBAN: " + iban + "; owner: " + owner;
    }
}
