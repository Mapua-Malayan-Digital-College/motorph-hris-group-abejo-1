package com.example.fx123;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    public static ArrayList<Employees> records = new ArrayList<>();
    private final String id;
    private final String l_name;
    private final String f_name;
    private final String birthday;
    private final String address;
    private final String phone_num;
    private final String sss_num;
    private final String philhealth_num;
    private final String tin_num;
    private final String pagibig_num;
    private final String status;
    private final String position;
    private final String immediate_supervisor;

    private final int basic_salary;
    private final int rice_subsidy;
    private final int phone_alw;
    private final int clothing_alw;
    private final int gross_semi_monthly_rate;
    private final float hourly_rate;

    public String getId() {
        return id;
    }

    public String getL_name() {
        return l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getSss_num() {
        return sss_num;
    }

    public String getPhilhealth_num() {
        return philhealth_num;
    }

    public String getTin_num() {
        return tin_num;
    }

    public String getPagibig_num() {
        return pagibig_num;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getImmediate_supervisor() {
        return immediate_supervisor;
    }

    public int getBasic_salary() {
        return basic_salary;
    }

    public int getRice_subsidy() {
        return rice_subsidy;
    }

    public int getPhone_alw() {
        return phone_alw;
    }

    public int getClothing_alw() {
        return clothing_alw;
    }

    public int getGross_semi_monthly_rate() {
        return gross_semi_monthly_rate;
    }

    public float getHourly_rate() {
        return hourly_rate;
    }

    Employees(String id, String lName, String fName, String birthDate,
              String address, String phoneNumber, String sssNumber,
              String philhealthNumber, String tinNumber, String pagibigNumber,
              String status, String position, String immediateSupervisor,
              int basicSalary, int riceSubsidy, int phoneAlw, int clothAlw,
              int grossSemiMonthlyRate, float hourlyRate) {
        this.id = id;
        this.l_name = lName;
        this.f_name = fName;
        this.birthday = birthDate;
        this.address = address;
        this.phone_num = phoneNumber;
        this.sss_num = sssNumber;
        this.philhealth_num = philhealthNumber;
        this.tin_num = tinNumber;
        this.pagibig_num = pagibigNumber;
        this.status = status;
        this.position = position;
        this.immediate_supervisor = immediateSupervisor;
        this.basic_salary = basicSalary;
        this.rice_subsidy = riceSubsidy;
        this.phone_alw = phoneAlw;
        this.clothing_alw = clothAlw;
        this.gross_semi_monthly_rate = grossSemiMonthlyRate;
        this.hourly_rate = hourlyRate;
    }

    public static void clearEmployees() {
        records.clear();
    }

    public static void addAllEmployees() {
        CsvUtils.addAllEmployee();
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id='" + id + '\'' + ", l_name='" + l_name + '\'' + ", f_name='"
                + f_name + '\'' + ", birthday='" + birthday + '\'' + ", address='"
                + address + '\'' + ", phone_num='" + phone_num + '\'' + ", sss_num='"
                + sss_num + '\'' + ", philhealth_num='" + philhealth_num + '\''
                + ", tin_num='" + tin_num + '\'' + ", pagibig_num='" + pagibig_num
                + '\'' + ", status='" + status + '\'' + ", position='" + position + '\''
                + ", immediate_supervisor='" + immediate_supervisor + '\''
                + ", basic_salary=" + basic_salary + ", rice_subsidy=" + rice_subsidy
                + ", phone_alw=" + phone_alw + ", clothing_alw=" + clothing_alw
                + ", gross_semi_rate=" + gross_semi_monthly_rate
                + ", hourly_rate=" + hourly_rate + '}';
    }

    public static int[] employeeNumbers() {
        int [] arr_employee_numbers = new int[records.size()];
        for (int c = 0; c < arr_employee_numbers.length; c++)
            arr_employee_numbers[c] = Integer.valueOf(records.get(c).getId());
        return arr_employee_numbers;
    }
}
