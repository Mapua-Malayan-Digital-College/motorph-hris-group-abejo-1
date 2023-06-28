package com.example.fx123;

public class Deduction {
    private double basic_salary;
    private double compensation;

    public double getBasic_salary() {
        return basic_salary;
    }

    public double getCompensation() {
        return compensation;
    }

    public Deduction(double basicSalary, double compensation) {
        this.basic_salary = basicSalary;
        this.compensation = compensation;
    }

    public double deductPhilHealth() {
        double premium_rate = 0.03f, employee_share = 2;
        if (basic_salary <= 10_000)
            return 300 / employee_share;
        else if (basic_salary > 10_000 && basic_salary < 60_000)
            return (basic_salary * premium_rate) / employee_share;
        else
            return 1_800 / employee_share;
    }

    public double deductPagIbig() {
        double contribution = basic_salary >= 1_500 ? basic_salary * 0.02f
                : (basic_salary >= 1_000)               ? basic_salary * 0.01f
                : 0.00f;

        return (contribution >= 100) ? 100 : contribution;
    }

    public double deductSSS() {
        if (compensation < 3_250)
            return 135.00f;

        else if (compensation >= 24_750)
            return 1_125.00f;

        else {
            int minimum_salary_limit = 3_250,
                maximum_salary_limit = 3_750;
            float contribution = 157.50f;
            while (contribution < 1125){
                if (compensation >= minimum_salary_limit && compensation < maximum_salary_limit) {
                    return contribution;
                }
                else {
                    minimum_salary_limit += 500;
                    maximum_salary_limit += 500;
                    contribution += 22.50f;
                }
            }
            return 0.00d;
        }
    }

    public double TotalContribution() {
        System.out.println("Total Deduction Breakdown:");
        System.out.println("Pagibig   = " + deductPagIbig());
        System.out.println("SSS   = " + deductSSS());
        System.out.println("Philhealth= " + deductPhilHealth());
        return deductPagIbig() + deductSSS() + deductPhilHealth();
    }

    public double getWithholdingTax() {
        double taxable_income = compensation - TotalContribution();

        if (taxable_income <= 20_832)
            return 0;
        else if (taxable_income < 33_333)
            return (taxable_income - 20_833) * 0.2d;
        else if (taxable_income < 66_667)
            return ((taxable_income - 33_333) * 0.25d) + 2_500;
        else if (taxable_income < 166_667)
            return ((taxable_income - 66_667) * 0.3d) + 10_833;
        else if (taxable_income < 666_667)
            return ((taxable_income - 166_667) * 0.32d) + 40_833.33f;
        else
            return ((taxable_income - 666_667) * 0.35d) + 200_833.33f;
    }
}
