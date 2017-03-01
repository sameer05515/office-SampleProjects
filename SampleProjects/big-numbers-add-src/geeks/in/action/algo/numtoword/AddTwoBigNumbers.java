package geeks.in.action.algo.numtoword;

public class AddTwoBigNumbers {

public static void main(String[] args) {
    try {
        System.out.println("Addition:" + addIntegers("9999", "9999"));
        System.out.println("Addition:" + addIntegers("1233434323432454521", "99872343237868642"));
        System.out.println("Addition:" + addIntegers("1233434323432454521", "37868642"));
        System.out.println("Addition:" + addIntegers("37868642", "1233434323432454521"));
    } catch (final Exception e) {
        System.out.println("Exception : " + e.getMessage());
    }
}

private static String addIntegers(String number1, String number2) throws Exception {
    validate(number1, number2);
    System.out.println("Adding: " + number1 + "+" + number2);
    char[] num1char = number1.toCharArray();
    char[] num2char = number2.toCharArray();
    if (num1char.length > num2char.length) {
        num2char = formatToSameLength(num1char, num2char);
    } else if (num1char.length < num2char.length) {
        num1char = formatToSameLength(num2char, num1char);
    }
    final char[] addition = new char[num1char.length + 1];
    char carrry = '0';
    for (int i = num1char.length - 1; i >= 0; i--) {
        final int sum = Character.getNumericValue(num1char[i]) + Character.getNumericValue(num2char[i]) + Character.getNumericValue(carrry);
        final char[] csum = String.valueOf(sum).toCharArray();
        carrry = '0';
        if (csum.length > 1 && i == 0) {
            addition[i + 1] = csum[1];
            addition[0] = csum[0];
        } else if (csum.length > 1) {
            carrry = csum[0];
            addition[i + 1] = csum[1];
        } else {
            addition[i + 1] = csum[0];
        }
    }
    return String.valueOf(addition);
}

private static void validate(String number1, String number2) throws Exception {
    if (number1.trim().isEmpty() || number2.trim().isEmpty() || !isNumber(number1) || !isNumber(number2)) {
        throw new Exception("Number is not present or not a valid number");
    }
}

private static boolean isNumber(String number) {
    try {
        Integer.parseInt(number);
        return true;
    } catch (final NumberFormatException nfe) {
    }
    return false;
}

private static char[] formatToSameLength(char[] num1char, char[] num2char) {
    final int diff = num1char.length - num2char.length;
    final char[] num = new char[num1char.length];
    for (int i = 0; i < diff; i++) {
        num[i] = '0';
    }
    for (int i = 0; i < num2char.length; i++) {
        num[diff + i] = num2char[i];
    }
    return num;
}
}