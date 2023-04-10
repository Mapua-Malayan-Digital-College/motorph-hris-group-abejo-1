public interface Contribution_PAGIBIG
{
    default float LEAST(int monthlyBasicSalary)
    {

        float employees_contrib_rate = 0.01f;
        float employer_contrib_rate  = 0.02f;
        final float contrib =  monthlyBasicSalary * (employees_contrib_rate + employer_contrib_rate);

        if (contrib > 100)
        {
            return 100;
        }

        else
        {
            return contrib;
        }
    }

    default float OVER(int monthlyBasicSalary)
    {
        float employees_contrib_rate = 0.02f;
        float employer_contrib_rate  = 0.02f;
        final float contrib =  monthlyBasicSalary * (employees_contrib_rate + employer_contrib_rate);

        if (contrib > 100)
        {
            return 100;
        }

        else
        {
            return contrib;
        }
    }
}
