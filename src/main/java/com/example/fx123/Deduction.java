package com.example.fx123;

public class Deduction {

    private double basic_salary;
    private double compensation;
    private double taxable_income;

    public double getBasic_salary() {
        return basic_salary;
    }

    public double getCompensation() {
        return compensation;
    }

    public Deduction (double basicSalary, double compensation) {
        this.basic_salary = basicSalary;
        this.compensation = compensation;
    }

    public double deductPhilHealth() {
        double premium_rate = 0.03f, employee_share = 2;
        if (basic_salary <= 10_000) return 300 / employee_share;
        else if (basic_salary > 10_000 && basic_salary < 60_000) return (basic_salary * premium_rate) / employee_share;
        else return 1_800/employee_share;
    }

    public double deductPagIbig() {

        double contribution = basic_salary >= 1_500
            ? basic_salary * 0.02f
            : (basic_salary >= 1_000)
                ? basic_salary * 0.01f
                : 0.00f;

        return (contribution>=100) ? 100 : contribution;
    }

    public double deductSSS() {

        if (this.compensation < 3_250) return 135.00f;

        if (this.compensation >= 24_750) return 1_125.00f;

        double[] compensationRange = {
                3_250, 3_750, 157.50f,
                3_750, 4_250, 180.00f,
                4_250, 4_750, 202.50f,
                4_750, 5_250, 225.00f,
                5_250, 5_750, 247.50f,
                5_750, 6_250, 270.00f,
                6_250, 6_750, 292.50f,
                6_750, 7_250, 315.00f,
                7_250, 7_750, 337.50f,
                7_750, 8_250, 360.00f,
                8_250, 8_750, 382.50f,
                8_750, 9_250, 405.00f,
                9_250, 9_750, 427.50f,
                9_750, 10_250, 450.00f,
                10_250, 10_750, 472.50f,
                10_750, 11_250, 495.00f,
                11_250, 11_750, 517.50f,
                11_750, 12_250, 540.00f,
                12_250, 12_750, 562.50f,
                12_750, 13_250, 585.00f,
                13_250, 13_750, 607.50f,
                13_750, 14_250, 630.00f,
                14_250, 14_750, 652.50f,
                14_750, 15_250, 675.00f,
                15_250, 15_750, 697.50f,
                15_750, 16_250, 720.00f,
                16_250, 16_750, 742.50f,
                16_750, 17_250, 765.00f,
                17_250, 17_750, 787.50f,
                17_750, 18_250, 810.00f,
                18_250, 18_750, 832.50f,
                18_750, 19_250, 855.00f,
                19_250, 19_750, 877.50f,
                19_750, 20_250, 900.00f,
                20_250, 20_750, 922.50f,
                20_750, 21_250, 945.00f,
                21_250, 21_750, 967.50f,
                21_750, 22_250, 990.00f,
                22_250, 22_750, 1_012.50f,
                22_750, 23_250, 1_035.00f,
                23_250, 23_750, 1_057.50f,
                23_750, 24_250, 1_080.00f,
                24_250, 24_750, 1_102.50f
        };

        for (int i = 0; i < compensationRange.length; i += 3) {
            double min_salary_limit = compensationRange[i]; //minimum col
            double max_salary_limit = compensationRange[i + 1]; //maximum col
if (compensation >= min_salary_limit && compensation < max_salary_limit) {
                return compensationRange[i + 2];
            }
        }
        return 0.00d;
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

        if (taxable_income <= 20_832) return 0;
        else if (taxable_income <  33_333) return (taxable_income - 20_833) * 0.2d;
        else if (taxable_income <  66_667) return ((taxable_income - 33_333) * 0.25d) + 2_500;
        else if (taxable_income <  166_667) return ((taxable_income - 66_667) * 0.3d) + 10_833;
        else if (taxable_income < 666_667) return ((taxable_income - 166_667) * 0.32d) + 40_833.33f;
        else return ((taxable_income - 666_667) * 0.35d) + 200_833.33f;
    }
}
