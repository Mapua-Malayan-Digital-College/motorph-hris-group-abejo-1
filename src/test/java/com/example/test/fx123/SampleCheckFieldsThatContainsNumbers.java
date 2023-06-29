package com.example.test.fx123;

public class SampleCheckFieldsThatContainsNumbers {
    public static boolean checkFieldsThatContainsNumbers() {
        String [] arrStrNumbers = {
            "1234",
            "20000",
            "20000",
            "20000",
            ";lkj",
        };

        String pattern = "\\d+";

        for (int i = 0; i < arrStrNumbers.length; i++) {
            if (!arrStrNumbers[i].matches(pattern)) {
                System.out.println("Invalid number : " + arrStrNumbers[i]+ "at index " + i);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        checkFieldsThatContainsNumbers();
    }
}
