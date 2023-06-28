//package com.example.test.fx123;
//import com.example.fx123.Employees;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class SampleCsvSplittingViaDoubleQuotes {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv"));
//        String line;
//        boolean isheader = true;
//        List proccessedCol = new ArrayList<>();
//        while ((line = br.readLine()) != null) {
//            if (isheader) {
//                isheader = false;
//                continue;
//            }
//            String [] split = line.split("\"");
//
//            for (int i = 0; i < split.length; i++) {
//                if (!split[i].contains(" ")) {
//                    String [] innerIFsplit = split[i].split(",");
//                    for (int ii = 0; ii < )
//                }
//                for (int j = 0; j < split[i].length(); j++) {
//
//                }
//            }
//
//            // try to check if the split has many comma's
//            boolean hasMoreThanOneComma;
//
//
//
//            Arrays.stream(split).forEach(System.out::println);
//            System.out.println("\n\n\n\n\n");
//        }
//    }
//}
