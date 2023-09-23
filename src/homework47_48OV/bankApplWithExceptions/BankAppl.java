package homework47_48OV.bankApplWithExceptions;

public class BankAppl {
    /*
    Задача 2
    Представьте, вы пишете банковскую программу. Создайте два checked exception: AccountFormatException и
    WrongAccountCountryCodeException. Напишите метод, который получает строку с номером вида: DE171271782891.
    Если строка короче 14 символов или длиннее 16 - > AccountFormatException.
    Если строка не начинается с символов "DE" - WrongAccountCountryCodeException, если что-то другое
    (например передан null) -> IllegalArgumentException. При нормальном потоке выполнения, метод создает
    новый объект Account и возвращает его.
    Напишите вызов вашего метода с обработкой возможных исключений.
    */
    public static void main(String[] args) {
        try {
//            Account account = createAccount("DD171271782891");
            Account account = createAccount(null);
            System.out.println(account);
        } catch (IllegalArgumentException e) {
            System.err.println("args are not correct");
        } catch (AccountFormatException e) {
            System.err.println("The IBAN should contain from 14 to 16 characters");
        } catch (WrongAccountCountryCodeException e) {
            System.err.println("The country code is wrong. Only 'DE' is allowed.");
        }

    }
    public static Account createAccount(String iban)
            throws AccountFormatException, WrongAccountCountryCodeException {
        if (iban == null || !isNumbersOnly(iban.substring(2))) throw new IllegalArgumentException();
        if (iban.length() < 14 || iban.length() > 16) throw new AccountFormatException();
        if (!iban.startsWith("DE")) throw new WrongAccountCountryCodeException();

        return new Account(iban);
    }
    private static boolean isNumbersOnly(String str) {
        for (char ch : str.toCharArray()) {
            if(!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}
