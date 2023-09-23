package exceptions_continue;

import homework47_48OV.bankApplWithExceptions.Account;
import homework47_48OV.bankApplWithExceptions.AccountFormatException;
import homework47_48OV.bankApplWithExceptions.WrongAccountCountryCodeException;

import java.util.ArrayList;
import java.util.List;

public class BankAppl2 {
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

        List<String> list = List.of("DE1712717828913", "DE171281782891", "DE171271782891123", "DR171271782891123",
                "DD11782891");
        List<Account> accounts = new ArrayList<>();

        for (String str : list) {
            try {
                accounts.add(createAccount(str));
            } catch (AccountCreateException e) {
                System.out.println("--> " + str);
                System.out.println(e.getErrorsList());
            }
        }
        System.out.println();
        System.out.println("=================== created accounts ===================");
        System.out.println(accounts);
    }

    public static Account createAccount(String iban) throws AccountCreateException {
        List<String> errors = new ArrayList<>();
        AccountCreateException exception = new AccountCreateException(errors);

        if (iban == null || !isNumbersOnly(iban.substring(2))) errors.add("args are not correct");
        if (iban.length() < 14 || iban.length() > 16) errors.add("The IBAN should contain from 14 to 16 characters");
        if (!iban.startsWith("DE")) errors.add("The country code is wrong. Only 'DE' is allowed.");

        if (!errors.isEmpty()) throw exception;

        return new Account(iban);
    }

    private static boolean isNumbersOnly(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}
