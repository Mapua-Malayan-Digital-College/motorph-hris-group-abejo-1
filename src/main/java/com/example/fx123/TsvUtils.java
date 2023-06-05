package com.example.fx123;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TsvUtils {

    public static void updateTsvFile(String filePath, int lineNumber, String[] newData) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == lineNumber) {
                    StringBuilder updatedLine = new StringBuilder();
                    for (String data : newData) {
                        updatedLine.append(data).append("\t");
                    }
                    lines.add(updatedLine.toString().trim());  // Trim trailing tab
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file: " + e.getMessage());
        }
    }

    public static int searchEmployeeNumber(String employeeNumber) {
        String filePath = MainApp.EMPLOYEE_TSV;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String[] columns = line.split("\t");

                if (columns.length > 0 && columns[0].equals(employeeNumber)) {
                    System.out.println("Employee number found at line " + lineNumber);
                    reader.close();
                    return lineNumber; // found
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // not found
    }

    public static void deleteTsvFileLine(String filePath, int lineNumber) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine != lineNumber) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Line deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the line: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // delete selected employee has been tested successfully.
//        deleteTsvFileLine(MainApp.EMPLOYEE_TSV,searchEmployeeNumber("10045"));
        // updating selected employee testing right now
        String [] newValue = "10001\tCrisostomo\tJose\tFebruary 14, 1988\t17/85 Stracke Via Suite 042, Poblacion, Las Piñas 4783 Dinagat Islands \t918-621-603\t49-1632020-8\t382189453145\t317-674-022-000\t441093369646\tRegular\tHR Manager\tN/A\t62,670\t1,500\t1,000\t1,000\t31,335\t373.04".split("\t");
        updateTsvFile(MainApp.EMPLOYEE_TSV,searchEmployeeNumber("10039"),newValue);
    }
}
