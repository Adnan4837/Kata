import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");

        String input = scanner.nextLine();

        try {
            int result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static int calculate(String input) throws Exception {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new Exception("Некорректное выражение");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];

        int num1, num2;
        boolean isRoman = false;

        try {
            num1 = Integer.parseInt(operand1);
            num2 = Integer.parseInt(operand2);
        } catch (NumberFormatException e) {
            num1 = RomanNumeral.convertToInteger(operand1);
            num2 = RomanNumeral.convertToInteger(operand2);
            isRoman = true;
        }

        if (isRoman) {
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }
        } else {
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new Exception("Некорректный оператор");
        }

        if (isRoman) {
            if (result <= 0) {
                throw new Exception("Римские числа не могут быть отрицательными или нулем");
            }
            System.out.println("Результат в римских цифрах: " + RomanNumeral.convertToRoman(result));
        } else {
            System.out.println("Результат в арабских числах: " + result);
        }

        return result;
    }
}

class RomanNumeral {
    public static int convertToInteger(String roman) throws Exception {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new Exception("Некорректное римское число");
        }
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 3999)
            return "Результат вне диапазона";

        String[] thousands = { "", "M", "MM", "MMM" };
        String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] units = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

        return thousands[number / 1000] + hundreds[(number % 1000) / 100] + tens[(number % 100) / 10]
                + units[number % 10];
    }
}