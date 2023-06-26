package com.example.fx123;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv"));
        String line;
        boolean is_header = true;
        List<Employees> listEmployees = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (is_header) {
                is_header = false;
                continue;
            }
            String[] splitted_data = line.split(",");
            ArrayList processedColumn = new ArrayList();
            String concatStr = "";
            for (int i = 0; i < splitted_data.length; i++) {
                int lastIndex = splitted_data[i].length() - 1;
                if (splitted_data[i].charAt(0) == '\"') {
                    if (!concatStr.isEmpty()) {
                        if (concatStr.contains("  ")) {
                            String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                            concatStr = convertTwoWhiteSpaceIntoComma;
                            String recipe_str = concatStr;
                            String cooked_str = concatStr.trim();
                            int leadingSpaces = recipe_str.length() - cooked_str.length();
                            String finalString = recipe_str.substring(leadingSpaces);
                            processedColumn.add(finalString);
                            concatStr = splitted_data[i];
                        }
                    } else {
                        concatStr += (" " + splitted_data[i]);
                    }
                } else if (splitted_data[i].charAt(0) == ' ') {
                    concatStr += (" " + splitted_data[i]);
                } else if (splitted_data[i].charAt(lastIndex) == '\"') {
                    concatStr += (" " + splitted_data[i].trim());
                    if (concatStr.charAt(concatStr.length() - 1) == '\"') {
                        String addCommaToStrNumber = concatStr.replace(" ", "");
                        processedColumn.add(addCommaToStrNumber);
                        concatStr = "";
                    }
                } else {
                    if (splitted_data[i].charAt(0) == '\"' && splitted_data[i].charAt(lastIndex) == '\"') {
                        String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                        processedColumn.add(convertTwoWhiteSpaceIntoComma);
                        concatStr = "";
                    } else if (!concatStr.isEmpty()) {
                        lastIndex = concatStr.length() - 1;
                        if (concatStr.charAt(0) == '\"' && (concatStr.charAt(lastIndex) == '\"')) {
                            String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                            processedColumn.add(convertTwoWhiteSpaceIntoComma.replace("\"\"", ""));
                            concatStr = "";
                            i--;// helloworld
                        } else if (concatStr.charAt(0) == '\"' && (concatStr.charAt(lastIndex) == ' ')) {
                            String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                            processedColumn.add(convertTwoWhiteSpaceIntoComma.replace("\"\"", ""));
                            concatStr = "";
                        }
                    } else {
                        concatStr = splitted_data[i];
                        processedColumn.add(concatStr);
                        concatStr = "";
                    }
                }
            }
            // remove double quotes
            String BasicSalary = (String.valueOf(processedColumn.get(13)).replaceAll("\"", ""));
            String RiceSubsidy = (String.valueOf(processedColumn.get(14)).replaceAll("\"", ""));
            String PhoneAllowance = (String.valueOf(processedColumn.get(15)).replaceAll("\"", ""));
            String ClothingAllowance = (String.valueOf(processedColumn.get(16)).replaceAll("\"", ""));
            String GrossSemimonthlyRate = (String.valueOf(processedColumn.get(17)).replaceAll("\"", ""));
            String HourlyRate = (String.valueOf(processedColumn.get(18)).replaceAll("\"", ""));

            Employees employees = new Employees(
                    String.valueOf(processedColumn.get(0)),
                    String.valueOf(processedColumn.get(1)),
                    String.valueOf(processedColumn.get(2)),
                    String.valueOf(processedColumn.get(3)),
                    String.valueOf(processedColumn.get(4)),
                    String.valueOf(processedColumn.get(5)),
                    String.valueOf(processedColumn.get(6)),
                    String.valueOf(processedColumn.get(7)),
                    String.valueOf(processedColumn.get(8)),
                    String.valueOf(processedColumn.get(9)),
                    String.valueOf(processedColumn.get(10)),
                    String.valueOf(processedColumn.get(11)),
                    String.valueOf(processedColumn.get(12)),
                    Integer.parseInt((BasicSalary).replace(",", "")),// remove comma
                    Integer.parseInt((RiceSubsidy).replace(",", "")),// remove comma
                    Integer.parseInt((PhoneAllowance).replace(",", "")),// remove comma
                    Integer.parseInt((ClothingAllowance).replace(",", "")),// remove comma
                    Integer.parseInt((GrossSemimonthlyRate).replace(",", "")),// remove comma
                    Float.parseFloat((HourlyRate).replace(",", "")));// remove comma
            listEmployees.add(employees);
        }
        System.out.println("Total employees = " + listEmployees.size());
        attendanceReader();
    }

    public static void attendanceReader() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Attendance Record.csv"));
        String line;
        boolean is_header = true;
        List<Attendance> listAttendance = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (is_header) {
                is_header = false;
                continue;
            }
            String[] splitted_data = line.split(",");
            ArrayList processedColumn = new ArrayList();
            String concatStr = "";
            for (int i = 0; i < splitted_data.length; i++) {
                int lastIndex = splitted_data[i].length() - 1;
                if (splitted_data[i].charAt(0) == '\"') {
                    if (!concatStr.isEmpty()) {
                        if (concatStr.contains("  ")) {
                            String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                            concatStr = convertTwoWhiteSpaceIntoComma;
                            String recipe_str = concatStr;
                            String cooked_str = concatStr.trim();
                            int leadingSpaces = recipe_str.length() - cooked_str.length();
                            String finalString = recipe_str.substring(leadingSpaces);
                            processedColumn.add(finalString);
                            concatStr = splitted_data[i];
                        }
                    } else {
                        concatStr += (" " + splitted_data[i]);
                    }
                } else if (splitted_data[i].charAt(0) == ' ') {
                    concatStr += (" " + splitted_data[i]);
                } else if (splitted_data[i].charAt(lastIndex) == '\"') {
                    concatStr += (" " + splitted_data[i].trim());
                    if (concatStr.charAt(concatStr.length() - 1) == '\"') {
                        String addCommaToStrNumber = concatStr.replace(" ", "");
                        processedColumn.add(addCommaToStrNumber);
                        concatStr = "";
                    }
                } else {
                    if (splitted_data[i].charAt(0) == '\"' && splitted_data[i].charAt(lastIndex) == '\"') {
                        String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                        processedColumn.add(convertTwoWhiteSpaceIntoComma);
                        concatStr = "";
                    } else if (!concatStr.isEmpty()) {
                        lastIndex = concatStr.length() - 1;
                        if (concatStr.charAt(0) == '\"' && (concatStr.charAt(lastIndex) == '\"')) {
                            String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                            processedColumn.add(convertTwoWhiteSpaceIntoComma.replace("\"\"", ""));
                            concatStr = "";
                            i--;// helloworld
                        } else if (concatStr.charAt(0) == '\"' && (concatStr.charAt(lastIndex) == ' ')) {
                            String convertTwoWhiteSpaceIntoComma = concatStr.replace("  ", ", ");
                            processedColumn.add(convertTwoWhiteSpaceIntoComma.replace("\"\"", ""));
                            concatStr = "";
                        }
                    } else {
                        concatStr = splitted_data[i];
                        processedColumn.add(concatStr);
                        concatStr = "";
                    }
                }
            }
            Attendance attendance = new Attendance(Integer.parseInt(String.valueOf(processedColumn.get(0))),
                    String.valueOf(processedColumn.get(1)),
                    String.valueOf(processedColumn.get(2)),
                    String.valueOf(processedColumn.get(3)),
                    String.valueOf(processedColumn.get(4)),
                    String.valueOf(processedColumn.get(5)));
            listAttendance.add(attendance);
        }
        System.out.println("Size of Attendance = " + listAttendance.size());
    }
}