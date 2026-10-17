import java.util.Scanner;

public class CreditCardValidator {

    // تنفيذ خوارزمية لاهن للتحقق من صحة رقم البطاقة
    public static boolean luhnCheck(String cardNumber) {
        int checksum = 0;
        boolean isEven = false;

        // المرور على الأرقام من اليمين إلى اليسار
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
        // التأكد من أن الإدخال أرقام فقط
        if (!cardNumber.matches("\\d+")) {
            return cardNumber + " is invalid";
        }

        int length = cardNumber.length();

        // 1. التحقق من الطول (من 13 إلى 16)[cite: 1]
        if (length < 13 || length > 16) {
            return cardNumber + " is invalid";
        }

        // 2. التحقق من البادئة ونوع البطاقة[cite: 1]
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

        // 3. التحقق باستخدام خوارزمية Luhn[cite: 1]
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