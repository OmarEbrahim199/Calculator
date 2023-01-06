package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class StringCalculator {


    public static int NegativeNumbersWillThrowNnException(final String numbers, final String delimiter) {
        int returnValue = 0;
        String[] numbersArray = numbers.split(delimiter);
        List negativeNumbers = new ArrayList();
        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                int numberInt = Integer.parseInt(number.trim());
                if (numberInt < 0) {
                    negativeNumbers.add(numberInt);
                }
                returnValue += numberInt;
            }
        }
        if (negativeNumbers.size() > 0) {
            throw new RuntimeException("Negatives not allowed: " + negativeNumbers.toString());
        }
        return returnValue;
    }



    public static int handleAnUnknownAmountOfNumbers(String input) {
        InnerStringCalculator calculator = new InnerStringCalculator(input);
        return calculator.add();
    }

    public static int SupportDifferentDelimiters(final String numbers) {
        String delimiter = ",|n";
        String numbersWithoutDelimiter = numbers;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("//") + 2;
            delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("n") + 1);
        }
        return add(numbersWithoutDelimiter, delimiter);
    }

    private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        String[] numbersArray = numbers.split(delimiter);
        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                returnValue += Integer.parseInt(number.trim());
            }
        }
        return returnValue;
    }

    public static int HandleNewLinesBetweenNumbersInsteadOfCommas(final String numbers) {
        int returnValue = 0;
        String[] numbersArray = numbers.split(",|n"); // Added |n to the split regex
        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                returnValue += Integer.parseInt(number.trim());
            }
        }
        return returnValue;
    }
    static class InnerStringCalculator {
        private final String input;

        private String delimiter = "[,\n]";
        private String numbersWithDelimiters;
        private List<String> negativeTokens = new ArrayList<String>();

        private InnerStringCalculator(String input) {
            this.input = input;
        }

        public int add() {
            if (isInputEmpty()) {
                return 0;
            }
            parseDelimiterAndFindLineWithNumbersAndDelimiters();
            String[] inputSplittedByDelimiter = splitInputByDelimiter();
            return calculateSum(inputSplittedByDelimiter);
        }

        private boolean isInputEmpty() {
            return input.length() == 0;
        }

        private void parseDelimiterAndFindLineWithNumbersAndDelimiters() {
            if (hasCustomDelimiter()) {
                parseDelimiter();
                findLineWithNumbersAndDelimiters();
            } else {
                numbersWithDelimiters = input;
            }
        }

        private boolean hasCustomDelimiter() {
            return input.startsWith("//");
        }


        private void findLineWithNumbersAndDelimiters() {
            int firstNewLine = input.indexOf("\n");
            numbersWithDelimiters = input.substring(firstNewLine + 1);
        }

        private void parseDelimiter() {
            delimiter = "";
            addDelimiters();
            delimiter = StringUtils.chop(delimiter);
        }

        private void addDelimiters() {
            int startIndexDelimiter = 0;
            while (true) {
                startIndexDelimiter = input.indexOf("[", startIndexDelimiter) + 1;
                if (startIndexDelimiter == 0) {
                    break;
                }
                int endIndexDelimiter = input.indexOf("]", startIndexDelimiter);
                delimiter += input.substring(startIndexDelimiter, endIndexDelimiter) + "|";
            }
        }

        private String[] splitInputByDelimiter() {
            return numbersWithDelimiters.split(delimiter);
        }

        private int calculateSum(String[] inputSplittedByDelimiter) {
            int result = 0;
            for (String token : inputSplittedByDelimiter) {
                result += addSingleToken(token);
            }
            throwExceptionIfNegativeTokensExist();
            return result;
        }

        private void throwExceptionIfNegativeTokensExist() {
            if (hasNegativeTokens()) {
                throw new IllegalArgumentException(String.format("negatives not allowed (%s)", StringUtils.join(negativeTokens, ",")));
            }
        }

        private boolean hasNegativeTokens() {
            return negativeTokens.size() > 0;
        }

        private int addSingleToken(String token) {
            Integer valueAsInteger = Integer.parseInt(token);
            if (isNegative(valueAsInteger)) {
                negativeTokens.add(token);
            } else if (isInValidRange(valueAsInteger)) {
                return valueAsInteger;
            }
            return 0;
        }

        private boolean isNegative(Integer valueAsInteger) {
            return valueAsInteger < 0;
        }

        private boolean isInValidRange(Integer valueAsInteger) {
            return valueAsInteger < 1000;
        }
    }
}