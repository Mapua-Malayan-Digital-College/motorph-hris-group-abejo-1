public class Employee
{
    //  PROFILE
    protected int employee_number;
    protected String first_name;
    protected String last_name;
    protected String birthday;
    protected String address;
    protected String phone_number;
    // BANK
    protected String SSS;
    protected String PHILHEALTH;
    protected String PAGIBIG;
    protected String TIN;
    //  OCCUPATION
    protected String status;
    protected String position;
    protected String immediate_supervisor;
    //  SALARY
    protected int  basic_salary;
    protected float gross_semi_monthly_rate;
    //  ALLOWANCE
    protected int rice;
    protected int phone;
    protected int clothing;
    protected float hourly_rate;
    // outer[0] = employee number
    // outer[1] = last name
    // outer[2] = first name
    // outer[3] = birthday
    // outer[4] = address
    // outer[5] = phone number
    // outer[6] = SSS
    // outer[7] = PHILHEALTH
    // outer[8] = TIN
    // outer[9] = PAGIBIG
    // outer[10] = Status
    // outer[11] = Position
    // outer[12] = Immediate Supervisor
    // outer[13] = Basic Salary
    // outer[14] = Rice Subsidy
    // outer[15] = Phone Allowance
    // outer[16] = Clothing Allowance
    // outer[17] = Gross Semi-monthly Rate
    // outer[18] = Hourly Rate

    Employee
    (
            String eNumber,
            String eLastName,
            String eFirstName,
            String eBirthDate,
            String eAddress,
            String ePhoneNumber,
            String eNumSSS,
            String eNumPAGIBIG,
            String eNumPHILHEALTH,
            String eNumTIN,
            String eStatus,
            String ePosition ,
            String eImmediateSupervisor,
            String sal_BS,
            String alw_R,
            String alw_P,
            String alw_C,
            String sal_GSMR,
            String sal_HR
    )
    {
        employee_number = Integer.parseInt(eNumber);
        first_name      = eLastName;
        last_name       = eFirstName;
        birthday        = eBirthDate;
        address         = eAddress;
        phone_number    = ePhoneNumber;
        SSS             = eNumSSS;
        PHILHEALTH      = eNumPAGIBIG;
        PAGIBIG         = eNumPHILHEALTH;
        TIN             = eNumTIN;
        status          = eStatus;
        position        = ePosition;
        immediate_supervisor = eImmediateSupervisor;
        basic_salary    = Integer.parseInt(sal_BS.replace(",",""));
        gross_semi_monthly_rate = Float.parseFloat(sal_GSMR.replace(",",""));
        rice            = Integer.parseInt(alw_R.replace(",",""));
        phone           = Integer.parseInt(alw_P.replace(",",""));
        clothing        = Integer.parseInt(alw_P.replace(",",""));
        hourly_rate     = Float.parseFloat(sal_HR.replace(",",""));

    }

    /*-------Getter FUNCs.--------*/

    public int getEmployee_number() {
        return employee_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSSS() {
        return SSS;
    }

    public String getPHILHEALTH() {
        return PHILHEALTH;
    }

    public String getPAGIBIG() {
        return PAGIBIG;
    }

    public String getTIN() {
        return TIN;
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

    public float getGross_semi_monthly_rate() {
        return gross_semi_monthly_rate;
    }

    public float getHourly_rate() {
        return hourly_rate;
    }

    public int getRice() {
        return rice;
    }

    public int getPhone() {
        return phone;
    }

    public int getClothing() {
        return clothing;
    }

    /*---------Setter FUNCs.---------*/

    public void setEmployee_number(int employee_number) {
        this.employee_number = employee_number;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setSSS(String SSS) {
        this.SSS = SSS;
    }

    public void setPHILHEALTH(String PHILHEALTH) {
        this.PHILHEALTH = PHILHEALTH;
    }

    public void setPAGIBIG(String PAGIBIG) {
        this.PAGIBIG = PAGIBIG;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setImmediate_supervisor(String immediate_supervisor) {
        this.immediate_supervisor = immediate_supervisor;
    }

    public void setBasic_salary(int basic_salary) {
        this.basic_salary = basic_salary;
    }

    public void setGross_semi_monthly_rate(float gross_semi_monthly_rate) {
        this.gross_semi_monthly_rate = gross_semi_monthly_rate;
    }

    public void setHourly_rate(float hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public void setRice(int rice) {
        this.rice = rice;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setClothing(int clothing) {
        this.clothing = clothing;
    }
} //    Employee Class Closed
