public class Deduction implements Contribution_SSS, Contribution_PAGIBIG, Contribution_PHILHEALTH
{
    float CONTRIB_SSS, CONTRIB_PAGIBIG, CONTRIB_PHILHEALTH;
    float EMPLOYEE_SHARE;
    float WITH_HOLDING_TAX;
    float TAXABLE_INCOME;
    float TOTAL_DEDUCTION;
    public void Deduction_SSS(int salary)
    {
        int count = 0;

        if (Integer.valueOf(salary) < 3250)
        {
            this.CONTRIB_SSS = contrib_range[0];
        }

        else if (Integer.valueOf(salary) > 24_750)
        {
            this.CONTRIB_SSS = contrib_range[44];

        }

        else
        {
            for (int minimum: min_sss)
            {
                if (Integer.valueOf(salary) < minimum)
                {
                    this.CONTRIB_SSS = contrib_range[count];
                    break;
                }
                count++;
            }
        }
    }



    public void Deduction_PHILHEALTH(int salary)
    {
        if (salary < 10_000)
        {
            this.CONTRIB_PHILHEALTH = LOWER();
            EMPLOYEE_SHARE = CONTRIB_PHILHEALTH / 2;
        }
        else if (salary > 10_000 && salary < 60_000)
        {
            this.CONTRIB_PHILHEALTH = MEDIAN(salary);
            EMPLOYEE_SHARE = CONTRIB_PHILHEALTH / 2;
        }
        else
        {
            this.CONTRIB_PHILHEALTH = HIGHER();
            EMPLOYEE_SHARE = CONTRIB_PHILHEALTH / 2;
        }
    }
    public void Deduction_PAGIBIG(int salary)
    {
        if (salary < 1_000)
        {
            this.CONTRIB_PAGIBIG = 0;
        }
        else if (salary > 1_000 && salary < 1_500)
        {
            this.CONTRIB_PAGIBIG = LEAST(salary);
        }
        else
        {
            this.CONTRIB_PAGIBIG = OVER(salary);
        }
    }

    public void totalDeduction(){
        final float sum = this.CONTRIB_PAGIBIG + this.CONTRIB_PHILHEALTH + this.CONTRIB_SSS;
        this.TOTAL_DEDUCTION = sum;
    }

    public void Deduction_WITH_HOLDING_TAX(int salary)
    {

        this.TAXABLE_INCOME = salary - this.TOTAL_DEDUCTION;


        if (salary <= 20_832)
        {
            this.WITH_HOLDING_TAX = 0;
        }
        else if (salary > 20_832 && salary < 33_333)
        {
            this.WITH_HOLDING_TAX =  (salary - this.TOTAL_DEDUCTION) * 0.2f;
        }
        else if (salary >= 33_333 && salary < 66_667)
        {
            this.WITH_HOLDING_TAX =  2_500 + (salary - this.TOTAL_DEDUCTION) * 0.25f;
        }
        else if (salary >= 66_668 && salary < 166_667)
        {
            this.WITH_HOLDING_TAX =  10_833 + (salary - this.TOTAL_DEDUCTION) * 0.3f;
        }
        else if (salary >= 166_667 && salary < 666_667)
        {
            this.WITH_HOLDING_TAX =  40_833.33f + (salary - this.TOTAL_DEDUCTION) * 0.32f;
        }
        else
        {
            this.WITH_HOLDING_TAX =  200_833.33f + (salary - this.TOTAL_DEDUCTION) * 0.35f;
        }
    }
}
