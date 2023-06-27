package com.example.fx123;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static void addAllEmployee() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv"));
            String line;
            boolean is_header = true;
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
                                i--; // helloworld
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
                        String.valueOf(processedColumn.get(0)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(1)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(2)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(3)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(4)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(5)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(6)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(7)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(8)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(9)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(10)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(11)).replaceAll("\"",""),
                        String.valueOf(processedColumn.get(12)).replaceAll("\"",""),
                        Integer.parseInt((BasicSalary).replace(",", "")), // remove comma
                        Integer.parseInt((RiceSubsidy).replace(",", "")), // remove comma
                        Integer.parseInt((PhoneAllowance).replace(",", "")), // remove comma
                        Integer.parseInt((ClothingAllowance).replace(",", "")), // remove comma
                        Integer.parseInt((GrossSemimonthlyRate).replace(",", "")), // remove comma
                        Float.parseFloat((HourlyRate).replace(",", ""))); // remove comma
                Employees.records.add(employees);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addAllAttendanceRecord() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\csv\\MotorPH Employee Data - Attendance Record.csv"));
            String line;
            boolean is_header = true;
            while ((line = br.readLine()) != null) {
                if (is_header) {
                    is_header = false;
                    continue;
                }
                String[] splitted_data = line.split(",");
                ArrayList < String > processedColumn = new ArrayList < String > ();
                for (int i = 0; i < splitted_data.length; i++) {
                    processedColumn.add(splitted_data[i]);
                }
                Attendance attendance = new Attendance(Integer.parseInt(String.valueOf(processedColumn.get(0))),
                        String.valueOf(processedColumn.get(1)),
                        String.valueOf(processedColumn.get(2)),
                        String.valueOf(processedColumn.get(3)),
                        String.valueOf(processedColumn.get(4)),
                        String.valueOf(processedColumn.get(5)));
                Attendance.records.add(attendance);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static String addDoubleQuotesIfStringHasComma(String str) {
        return str.contains(",") ? "\""+str+"\"" : str;
    }

    public static String addCommaToStrInt(String num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Double.valueOf(num));
    }

    public static String addCommaAndTwoDecimalsForFloatStr(String num) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(Double.valueOf(num));
    }

    public static void updateByLineNumber(String filePath, int lineNumber, String[] newData) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == lineNumber) {
                    StringBuilder updatedLineBuilder = new StringBuilder();
                    for (String data : newData) {
                        updatedLineBuilder.append(data).append(",");
                    }
                    String updatedLine = updatedLineBuilder.toString().trim(); // Trim trailing tab
                    updatedLines.add(updatedLine);
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file: " + e.getMessage());
        }
    }

    public static int findLineNumberByEmployeeNumber(String filepath, String employeeNumber) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int lineNumber = 0;
            if (filepath.contains("Attendance Record")) {
                while ((line = reader.readLine()) != null) {
                    lineNumber++;

                    String[] columns = line.split("\t");

                    if (columns.length > 0 && columns[0].equals(employeeNumber)) {
                        System.out.println("Employee number found at line " + lineNumber);
                        reader.close();
                        return lineNumber; // found
                    }
                }
            }
            else if (filepath.contains("Employee Details")) {
                if (Employees.records.isEmpty()) Employees.addAllEmployees();
                int [] employeesNumber = Employees.employeeNumbers();
                System.out.println("employees number length = " + employeesNumber.length);
                if (Employees.records.isEmpty()) addAllEmployee();
                    for (int i = 0; i < employeesNumber.length; i++) {
                        lineNumber++;
                        if (Integer.parseInt(employeeNumber) == employeesNumber[i]) {
                            System.out.println("Employee number found at line " + lineNumber);
                            reader.close();
                            return lineNumber; // found
                        }
                    }
            }
            return 0; // not found
            }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}