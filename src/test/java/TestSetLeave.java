import com.example.fx123.Employees;
import com.example.fx123.MainApp;
import com.example.fx123.ManageLeaves;
import com.example.fx123.TsvUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class TestSetLeave {
    public static void main(String[] args) {
        Employees.addAllEmployees();
        ManageLeaves.addAllLeaves();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        ManageLeaves manageLeaves = null;
        try {
            manageLeaves = new ManageLeaves(10001,"Crisostomo", "Jose","Vacation",sdf.parse("06/01/2022"),sdf.parse("06/06/2022"));
            manageLeaves.createEmployeeLeave();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
