public class Deduction implements Contribution_SSS, Contribution_PAGIBIG, Contribution_PHILHEALTH
{
    float CONTRIB_SSS, CONTRIB_PAGIBIG, CONTRIB_PHILHEALTH;
    float EMPLOYEE_SHARE;
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
        else {
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
}
