package com.example.fx123;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TsvUtils {

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
                        updatedLineBuilder.append(data).append("\t");
                    }
                    String updatedLine = updatedLineBuilder.toString().trim(); // Trim trailing tab
                    updatedLines.add(updatedLine);
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return;
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

    public static void deleteEmployeeRecordByLineNumber(String filePath, int lineNumber) {
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
}
