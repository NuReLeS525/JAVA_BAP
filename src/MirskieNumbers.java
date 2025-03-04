import java.util.Scanner;
import java.util.HashMap;

public class MirskieNumbers {
    static final HashMap<Character, Integer> dictionary = new HashMap<>();

    static {
        dictionary.put('A', 1);
        dictionary.put('B', 10);
        dictionary.put('C', 100);
        dictionary.put('D', 1000);
        dictionary.put('E', 10000);
    }

    static int getValue(String number) {
        int total = 0;
        int maxRight = 0;

        for (int i = number.length() - 1; i >= 0; i--) {
            int value = dictionary.get(number.charAt(i));
            if (value < maxRight) {
                total -= value;
            } else {
                total += value;
                maxRight = value;
            }
        }
        return total;
    }

    static int maxPossibleValue(String number) {
        int currentValue = getValue(number);
        int maxValue = currentValue;

        char[] chars = number.toCharArray();
        int n = chars.length;

        // Найдем первую замену, которая дает максимальный прирост
        for (int i = 0; i < n; i++) {
            char original = chars[i];
            for (char newChar : "EDCBA".toCharArray()) {
                if (newChar != original) {
                    chars[i] = newChar;
                    int newValue = getValue(new String(chars));
                    maxValue = Math.max(maxValue, newValue);
                    chars[i] = original;  // Откатываем замену
                }
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        int[] results = new int[t];

        for (int i = 0; i < t; i++) {
            String s = scanner.nextLine();
            results[i] = maxPossibleValue(s);
        }

        scanner.close();

        for (int result : results) {
            System.out.println(result);
        }
    }
}
