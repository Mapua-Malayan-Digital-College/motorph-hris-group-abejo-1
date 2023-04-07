import gui.EmployeeData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        EmployeeData employeeData = new EmployeeData();
        String path = "src/main/csv/MotorPH Employee Data - Employee Details (4).csv";
//        ArrayList<String[]> al = readData(path);
//        al.forEach(outer-> {
//            for (String inner: outer){
//                System.out.println(inner.toString());
//            }
//        });

        // Access Employee Details Employee Number

        ArrayList<String[]> data= EmployeeData.readData2(path);

        data.stream().forEach(s-> {
            System.out.println(s[0]);
        });

    }
}
