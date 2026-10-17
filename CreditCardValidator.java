import java.util.Scanner;

public class CreditCardValidator {

    public static boolean luhnCheck(String cardNumber) {
        int checksum = 0;
        boolean isEven = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int d = Character.getNumericValue(cardNumber.charAt(i));

            if (isEven) {
                d *= 2;
                if (d > 9) {
                    d -= 9;
                }
            }
            checksum += d;
            isEven = !isEven;
        }

        return checksum % 10 == 0;
    }

    public static String validateCreditCard(String cardNumber) {
        if (!cardNumber.matches("\\d+")) {
            return cardNumber + " is invalid";
        }

        int length = cardNumber.length();

        if (length < 13 || length > 16) {
            return cardNumber + " is invalid";
        }

        boolean validPrefix = false;
        if (cardNumber.startsWith("4")) { // Visa[cite: 1]
            validPrefix = true;
        } else if (cardNumber.startsWith("5")) { // Mastercard[cite: 1]
            validPrefix = true;
        } else if (cardNumber.startsWith("37")) { // American Express[cite: 1]
            validPrefix = true;
        } else if (cardNumber.startsWith("6")) { // Discover[cite: 1]
            validPrefix = true;
        }

        if (!validPrefix) {
            return cardNumber + " is invalid";
        }

        if (luhnCheck(cardNumber)) {
            return cardNumber + " is valid";
        } else {
            return cardNumber + " is invalid";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a credit card number: ");
        String userInput = scanner.nextLine().trim();
        
        String result = validateCreditCard(userInput);
        System.out.println("Output: " + result);
        
        scanner.close();
    }
}
