package homework49;

import java.util.*;

public class BankAccAppl {
    /*
    Задача 1
    Дан список строк вида:
    DE1613321892788;Jack Johnson
    DE1613324427567;Ivan Ivanov
    DE1612324427565;Ann Smith

    т.е. список банковских счетов с именем владельца. К сожалению, список пришел некорректный:
    у некоторых счетов отсутствуют имена владельцев, некоторые счета не начинаются на DE или короче 15 символов.
    Ваш метод должен сформировать список объектов типа Account (String iban, Person owner),
    ЕСЛИ ВСЕ ЗАПИСИ корректны, либо выкинуть exception. Причем в последнем случае, должна быть
    сохранена информация о том, какие строки и какие проблемы содержали.
    */

    public static void main(String[] args) {
        List<String> listOwners = new ArrayList<>(List.of("DE1613321892788;Jack Johnson", "DE161232BB27565;Boris " +
                "Wolf", "DE1613324427567;Ivan Ivanov", "DE1612324427565;Ann Smith", "DE1613321892729; ",
                "DE161232442;Petr Petrov"));

        List<Account> createdAccounts = new ArrayList<>();
        for (Map.Entry<String, String> entry : createEntryMap(listOwners).entrySet()) {
            try {
                createdAccounts.add(accountCreator(entry));
            } catch (AccountCreationExceptions exception) {
                System.out.println(exception.getErrors());
            }
        }
        System.out.println();
        System.out.println("=================== created accounts ===================");
        System.out.println();
        System.out.println(createdAccounts);
    }

    public static Map<String, String> createEntryMap(List<String> list) {
        Map<String, String> mapAccOwners = new HashMap<>();
        for (String str : list) {
            mapAccOwners.put(str.split(";")[0], str.split(";")[1]);
        }
        return mapAccOwners;
    }

    public static Account accountCreator(Map.Entry<String, String> entry) throws AccountCreationExceptions {
        List<String> errors = new ArrayList<>();
        AccountCreationExceptions exceptions = new AccountCreationExceptions(errors);
        if (entry == null || entry.getKey().equals(" ") || entry.getValue().equals(" ")) {
            errors.add("--> " + entry + ": There is no object.");
        }
        if (entry.getKey().length() < 14 || entry.getKey().length() > 16) {
            errors.add("--> " + entry + ": The IBAN should contain from 14 to 16 characters.");
        }
        if (!entry.getKey().substring(2).matches("^[0-9]+$")) {
            errors.add("--> " + entry + ": After the country code only digits are allowed.");
        }
        if (!entry.getKey().startsWith("DE")) {
            errors.add("--> " + entry + ": The country code is wrong. Only 'DE' is allowed.");
        }

        Account account = new Account(entry.getKey(), new Person(entry.getValue()));

        if (!errors.isEmpty()) throw exceptions;

        return account;
    }
}





 /* public static List<Account> createNewAccount(List<String> list) throws AccountCreationExceptions {
         List<Account> accountList = new ArrayList<>();
         Map<String, String> mapAccOwners = new HashMap<>();

         for (String str : list) {
             mapAccOwners.put(str.split(";")[0], str.split(";")[1]);
         }
         List<String> errors = new ArrayList<>();
         AccountCreationExceptions exceptions = new AccountCreationExceptions(errors);
         for (Map.Entry<String, String> entry : mapAccOwners.entrySet()) {
             if (entry == null || entry.getKey() == null || entry.getValue() == null || entry.getKey().equals(" ") ||
              entry.getValue().equals(" ")) {
                 errors.add("\n" + "--> " + entry + ": There is no object.");
                 continue;
             }
             if (entry.getKey().length() < 14 || entry.getKey().length() > 16) {
                 errors.add("\n" + "--> " + entry + ": The IBAN should contain from 14 to 16 characters.");
                 continue;
             }
             if (!entry.getKey().substring(2).matches("^[0-9]+$")) {
                 errors.add("\n" + "--> " + entry + ": After the country code only digits are allowed.");
                 continue;
             }
             if (!entry.getKey().startsWith("DE")) {
                 errors.add("\n" + "--> " + entry + ": The country code is wrong. Only 'DE' is allowed.");
                 continue;
             }

             Account account = new Account(entry.getKey(), new Person(entry.getValue()));
             accountList.add(account);
         }
         System.out.println(exceptions.getErrors());
         return accountList;
     }
     */