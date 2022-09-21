class Calculator {

    String calculate(String input) throws Exception {
        String[] expression = input.split(" ");

        if (expression.length != 3) {
            throw new Exception("Wrong input format. Input example: 1 + 2 or I + II");
        }

        String num1 = expression[0];
        String operator = expression[1];
        String num2 = expression[2];

        checkNumeralSystem(num1, num2);

        if (isOperandArabic(num1) && isOperandArabic(num2)) {
            return String.valueOf(calculateArabicNumbers(num1, operator, num2));
        } else if (isOperandRoman(num1) && isOperandRoman(num2)) {
            int value = calculateRomanNumbers(num1, operator, num2);
            return String.valueOf(RomanNumeralSystem.values()[value - 1]);
        } else {
            throw new Exception("Number must be from 1 to 10 inclusive");
        }
    }

    private void checkNumeralSystem(String num1, String num2) throws Exception {
        boolean firstOperandArabic = isOperandArabic(num1);
        boolean secondOperandArabic = isOperandArabic(num2);
        boolean firstOperandRoman = isOperandRoman(num1);
        boolean secondOperandRoman = isOperandRoman(num2);

        if (firstOperandArabic && secondOperandRoman || firstOperandRoman && secondOperandArabic) {
            throw new Exception("Wrong input format. Different number systems are used simultaneously");
        }
    }

    private boolean isOperandArabic(String num) {
        return (num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4") || num.equals("5") ||
                num.equals("6") || num.equals("7") || num.equals("8") || num.equals("9") || num.equals("10"));
    }

    private boolean isOperandRoman(String num) {
        return (num.equals("I") || num.equals("II") || num.equals("III") || num.equals("IV") || num.equals("V") ||
                num.equals("VI") || num.equals("VII") || num.equals("VIII") || num.equals("IX") || num.equals("X"));
    }

    private int calculateArabicNumbers(String num1, String operator, String num2) throws Exception {
        return calculate(Integer.parseInt(num1), operator, Integer.parseInt(num2));
    }

    private int calculateRomanNumbers(String firstOperand, String operator, String secondOperand) throws Exception {
        int num1 = RomanNumeralSystem.valueOf(firstOperand).getValue();
        int num2 = RomanNumeralSystem.valueOf(secondOperand).getValue();
        int result = calculate(num1, operator, num2);

        if (result < 1) {
            throw new Exception("The result of the calculator with Roman numbers can only be positive numbers");
        } else {
            return result;
        }
    }

    private int calculate(int num1, String operator, int num2) throws Exception {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new Exception("The operator must be +, -, * or /");
        };
    }
}
