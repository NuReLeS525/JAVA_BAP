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
        HashMap<Character, Integer> dictionary = new HashMap<>();
        dictionary.put('A', 1);
        dictionary.put('B', 10);
        dictionary.put('C', 100);
        dictionary.put('D', 1000);
        dictionary.put('E', 10000);

        int n = number.length();
        int[] values = new int[n];
        int[] suffixMax = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = dictionary.get(number.charAt(i));
        }

        suffixMax[n - 1] = values[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(values[i], suffixMax[i + 1]);
        }

        int currentValue = 0;
        for (int i = 0; i < n; i++) {
            if (values[i] < suffixMax[i]) {
                currentValue -= values[i];
            } else {
                currentValue += values[i];
            }
        }

        int maxValue = currentValue;
        for (int i = 0; i < n; i++) {
            for (char newChar : "EDCBA".toCharArray()) {
                if (newChar != number.charAt(i)) {
                    int newValue = dictionary.get(newChar);
                    int oldValue = values[i];

                    int modifiedValue = currentValue;
                    if (oldValue < suffixMax[i]) {
                        modifiedValue += oldValue;
                    } else {
                        modifiedValue -= oldValue;
                    }

                    int newSuffixMax = (i + 1 < n) ? suffixMax[i + 1] : 0;
                    newSuffixMax = Math.max(newSuffixMax, newValue);

                    if (newValue < newSuffixMax) {
                        modifiedValue -= newValue;
                    } else {
                        modifiedValue += newValue;
                    }

                    maxValue = Math.max(maxValue, modifiedValue);
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
