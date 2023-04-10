public class Employee
{
    String employee_number;
    String first_name;
    String last_name;
    String birthday;
    String address;
    String phone_number;

    Employee (String eNumber, String last_name, String first_name, String birthday, String address, String phone_number)
    {
        this.employee_number = eNumber;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.address = address;
        this.phone_number = phone_number;
    }

    public Employee() {
    }
}
