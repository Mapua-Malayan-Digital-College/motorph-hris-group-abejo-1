public class Salary extends Employee
{
    String  basic_salary;
    String gross_semi_monthly_rate;
    String hourly_rate;

    String allowance_rice;
    String allowance_phone;
    String allowance_clothing;

    Salary (String basic_salary, String gross_semi_monthly_rate, String hourly_rate,
            String rice, String phone, String clothing)
    {
        this.basic_salary = (basic_salary);
        this.gross_semi_monthly_rate = (gross_semi_monthly_rate);
        this.hourly_rate = (hourly_rate);
        // allowance
        this.allowance_rice = (rice);
        this.allowance_phone = (rice);
        this.allowance_clothing = (clothing);
    }
}
