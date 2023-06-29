package com.example.test.fx123;

import com.example.fx123.CsvUtils;

public class SampleUpdateEmployeeData {
    public static void main(String[] args) {
        // Sample Update
        // Jose Crisostomo Name to Jomari Abejo
        // Birthday February 14, 1988 to November 28, 2001
        // Basic salary 62,670 to 100k
        // Gross Semi monthly 31,335 to 50k
        // Hourly Rate 373.04 to 573.11
        String [] newData =
                {
                        "10001",
                        "Abejo",
                        "Jomari",
                        "\"November 28, 2001\"",
                        "\"17/85 Stracke Via Suite 042, Poblacion, Las Piñas 4783 Dinagat Islands \"",
                        "918-621-603",
                        "49-1632020-8",
                        "382189453145",
                        "317-674-022-000",
                        "441093369646",
                        "Regular",
                        "HR Manager",
                        "N/A",
                        CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt("\"100000\"".replaceAll("\"",""))),
                        "\"1,500\"",
                        "\"1,000\"",
                        "\"1,000\"",
                        CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt("\"50000\"".replaceAll("\"",""))),
                        CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaAndTwoDecimalsForFloatStr("\"573.11\"".replaceAll("\"","")))
                };
        CsvUtils.updateByLineNumber(
                "src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv",
                CsvUtils.findLineNumberByEmployeeNumber("src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv",
                        "10001"),
                newData);
    }
}
