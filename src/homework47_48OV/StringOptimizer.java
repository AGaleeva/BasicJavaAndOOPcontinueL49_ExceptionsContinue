package homework47_48OV;

import java.util.*;

public class StringOptimizer {
    /*
    Задача 2
    Представьте, у вас есть робот, который понимает команды:
    'С' - шаг на север
    'Ю' - шаг на юг
    'З' - шаг на запад
    'В' - шаг на восток
    Команды роботу поступают в виде строки вида "CЗВЗЗТЮ". Очевидно, если в строке присутствуют рядом взаимно
    противоположные команды например "ЗВ" или "СЮ" их можно сократить. Очевидно, что после сокращения, в строке опять
    взаимно противоположные команды могут оказаться рядом, и их опять можно сократить.
    Напишите метод, который оптимизирует строку команд для робота, сокращая лишние шаги.
    Примеры: "СЮСЗ" -> "СЗ"
    "СЮВЗ" -> ""
    "СВЮЗ" -> "СВЮЗ"
    "СВЗЮВЮЗССЮ" -> "ВЮЗС"
    */

    public static void main(String[] args) {
        String commandLine = "NEWSESWNNS";
        System.out.println(stringOptimize(commandLine));
    }

    public static String stringOptimize(String str) {
        if (str == null || str.isEmpty()) return "";

        Map<Character, Character> pairDirections = new HashMap<>();
        pairDirections.put('N', 'S');
        pairDirections.put('S', 'N');
        pairDirections.put('E', 'W');
        pairDirections.put('W', 'E');

        Deque<Character> stack = new LinkedList<>();
        for (Character ch : str.toCharArray()) {
            if (pairDirections.get(ch).equals(stack.peek())) { // даже, если cтэк пустой и в equals придет null, то
                // результат будет просто
                // false и эксцепшена не будет
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        /*
        Character[] characters = new Character[stack.size()];
        characters = stack.toArray(characters);
        */

        /*
        Object[] array = stack.toArray();
        char[] array2 = new char[array.length];
        int i = array2.length - 1;
        for (Object obj : array) {
            array2[i--] = (Character)obj;
        }
        return new String(array2);
        */

//        stack.descendingIterator();

        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);   // если у нас тип "Character", то можно поставить sb.append(ch.charValue()), чтобы работать
                             // с примитивными типами.
        }
        return sb.reverse().toString();
    }
}
