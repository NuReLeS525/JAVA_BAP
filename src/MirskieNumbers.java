import java.util.Scanner;
import java.util.HashMap;

public class MirskieNumbers {
    static int getValue(String number) {
        HashMap<Character, Integer> dictionary = new HashMap<>();
        dictionary.put('A', 1);
        dictionary.put('B', 10);
        dictionary.put('C', 100);
        dictionary.put('D', 1000);
        dictionary.put('E', 10000);

        int total = 0;
        int maxRight = 0; // Самое большое значение справа от текущего символа

        // Проходим строку справа налево
        for (int i = number.length() - 1; i >= 0; i--) {
            int value = dictionary.get(number.charAt(i));

            // Если текущая цифра меньше максимальной справа, вычитаем
            if (value < maxRight) {
                total -= value;
            } else {
                total += value;
                maxRight = value; // Обновляем максимальное значение справа
            }
        }

        return total;
    }

    static int maxPossibleValue(String number) {
        HashMap<Character, Integer> dictionary = new HashMap<>();
        dictionary.put('A', 1);
        dictionary.put('B', 10);
        dictionary.put('C', 100);
        dictionary.put('D', 1000);
        dictionary.put('E', 10000);

        int currentValue = getValue(number);
        int maxValue = currentValue;

        // Пробуем заменить каждую букву на все возможные (A–E)
        for (int i = 0; i < number.length(); i++) {
            char original = number.charAt(i);

            for (char newChar : "ABCDE".toCharArray()) {
                if (newChar != original) {
                    String newNumber = number.substring(0, i) + newChar + number.substring(i + 1);
                    maxValue = Math.max(maxValue, getValue(newNumber));
                }
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine(); // Пропускаем перевод строки

        String[] results = new String[t];

        for (int i = 0; i < t; i++) {
            String s = scanner.nextLine();
            results[i] = String.valueOf(maxPossibleValue(s));
        }

        scanner.close();

        for (String result : results) {
            System.out.println(result);
        }
    }
}