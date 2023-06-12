package com.example.fx123;

public class Deduction {

    private float basic_salary;
    private float compensation;

    public float getBasic_salary() {
        return basic_salary;
    }

    public float getCompensation() {
        return compensation;
    }

    public Deduction (float basicSalary, float compensation) {
        this.basic_salary = basicSalary;
        this.compensation = compensation;
    }

    public float philHealthContribution() {
        return (this.basic_salary * 0.03f) / 2;
    }

    public float pagIbigContribution() {
        return this.basic_salary >= 1_500
                ? 100
                : (this.basic_salary >= 1_000)
                ? this.basic_salary * 0.03f
                : 0.00f;
    }

    public float sssContribution() {

        if (this.compensation <= 3_250) return 135.00f;

        if (this.compensation >= 24_750) return 1_125.00f;

        float[] compensationRange = {
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
            float min_salary = compensationRange[i]; //minimum col
            float max_salary = compensationRange[i + 1]; //maximum col
            if (min_salary < this.compensation && max_salary > this.compensation) {
                return compensationRange[i + 2];
            }
        }
        return 0.00f;
    }


    public float TotalDeduction() {
        return pagIbigContribution() + sssContribution() + philHealthContribution();
    }

    public float getWithholdingTax() {

        float taxable_income = compensation - TotalDeduction();

        if (compensation <= 20_832) return 0;
        else if (compensation <  33_333) return (taxable_income - 20_833) * 0.2f;
        else if (compensation <  66_667) return ((taxable_income - 33_333) * 0.25f) + 2_500;
        else if (compensation <  166_667) return ((taxable_income - 66_667) * 0.3f) + 10_833;
        else if (compensation <= 666_667) return ((taxable_income - 166_667) * 0.32f) + 40_833.33f;
        else return ((taxable_income - 666_667) * 0.35f) + 200_833.33f;
    }
}