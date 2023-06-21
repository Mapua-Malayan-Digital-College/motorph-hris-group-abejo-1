import com.example.fx123.Employees;
import com.example.fx123.EmployeeLeave;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSetLeave {
    public static void main(String[] args) {
        Employees.addAllEmployees();
        EmployeeLeave.addAllLeaves();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        EmployeeLeave manageLeaves = null;
        try {
            manageLeaves = new EmployeeLeave(10001,"Crisostomo", "Jose","Vacation",sdf.parse("06/01/2022"),sdf.parse("06/06/2022"));
            manageLeaves.createEmployeeLeave();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
