package com.example.test.fx123;

public class SampleCheckDigitOnly {
    public static void main(String[] args) {
        isTimeDigitOnly("8:00a");
    }
    private static boolean isTimeDigitOnly(String str) {
        String strTime = str.replace(":","");
        try{
            int numTimeIn = Integer.parseInt(strTime);
            boolean isTimeInDigitsOnly = String.valueOf(Integer.valueOf(numTimeIn)).matches("[0-9]+");
            if (isTimeInDigitsOnly){
                System.out.println("Yes it is digit only");
                return true;
            }
        }
        catch (NumberFormatException exception) {
            System.out.println("No it is not string digit only "+exception.getMessage());
        }
        return false;
    }

}
