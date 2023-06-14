import com.example.fx123.Employees;
import com.example.fx123.ManageLeaves;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSetLeave {
    public static void main(String[] args) {
        Employees.addAllEmployees();
        ManageLeaves.addAllLeavesOfEmployee("10025");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        ManageLeaves manageLeaves = null;
        try {
            manageLeaves = new ManageLeaves(10025,"Crisostomo","Jose","Sick",sdf.parse("07/06/2022"),sdf.parse("07/11/2022"));
//            manageLeaves.createEmployeeLeave(10025,"Crisostomo","Jose","Sick",sdf.parse("06/25/2022"),sdf.parse("06/30/2022"));
//            manageLeaves.createEmployeeLeave(10025,"Crisostomo","Jose","Sick",sdf.parse("07/01/2022"),sdf.parse("07/01/2022"));
//            manageLeaves.createEmployeeLeave(10025,"Crisostomo","Jose","Emergency",sdf.parse("07/05/2022"),sdf.parse("07/05/2022"));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
