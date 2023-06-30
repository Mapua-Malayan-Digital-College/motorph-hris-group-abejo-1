package com.example.test.fx123;

import com.example.fx123.Deduction;

public class SampleDeduction {
    public static void main(String[] args) {
        float hoursWorked = 150;
        Deduction deductionJose     = new Deduction(62_670,(hoursWorked * 373.04f));
        Deduction deductionChristian= new Deduction(42_975,(hoursWorked * 255.80f));
        Deduction deductionBrad     = new Deduction(42_975,(hoursWorked * 255.80f));
        Deduction deductionAnthony  = new Deduction(60_825,(hoursWorked * 362.05f));
        Deduction deductionAlice    = new Deduction(22_500,(hoursWorked * 133.93f));

        Deduction copyOfWitholdingTaxSample    = new Deduction(25_000,25_000);


        System.out.println("\n\n\nSample SSS");
        System.out.println(deductionJose.getGross_salary()+"\t=\t"+deductionJose.deductSSS());
        System.out.println(deductionChristian.getGross_salary()+"\t=\t"+deductionChristian.deductSSS());
        System.out.println(deductionBrad.getGross_salary()+"\t=\t"+deductionBrad.deductSSS());
        System.out.println(deductionAnthony.getGross_salary()+"\t=\t"+deductionAnthony.deductSSS());
        System.out.println(deductionAlice.getGross_salary()+"\t=\t"+deductionAlice.deductSSS());
        System.out.println(copyOfWitholdingTaxSample.getGross_salary()+"\t=\t"+copyOfWitholdingTaxSample.deductPhilHealth());

        System.out.println("\n\n\nSample PhilHealth");
        System.out.println(deductionJose.getBasic_salary()+"\t=\t"+deductionJose.deductPhilHealth());
        System.out.println(deductionChristian.getBasic_salary()+"\t=\t"+deductionChristian.deductPhilHealth());
        System.out.println(deductionBrad.getBasic_salary()+"\t=\t"+deductionBrad.deductPhilHealth());
        System.out.println(deductionAnthony.getBasic_salary()+"\t=\t"+deductionAnthony.deductPhilHealth());
        System.out.println(deductionAlice.getBasic_salary()+"\t=\t"+deductionAlice.deductPhilHealth());
        System.out.println(copyOfWitholdingTaxSample.getBasic_salary()+"\t=\t"+copyOfWitholdingTaxSample.deductPhilHealth());

        System.out.println("\n\n\nSample PagIBIG");
        System.out.println(deductionJose.deductPagIbig());
        System.out.println(deductionChristian.deductPagIbig());
        System.out.println(deductionBrad.deductPagIbig());
        System.out.println(deductionAnthony.deductPagIbig());
        System.out.println(deductionAlice.deductPagIbig());
        System.out.println(copyOfWitholdingTaxSample.deductPagIbig());

        System.out.println("\n\n\nSample Withholding Tax");
        System.out.println(deductionJose.getGross_salary()+"\t=\t"+deductionJose.getWithholdingTax());
        System.out.println(deductionChristian.getGross_salary()+"\t=\t"+deductionChristian.getWithholdingTax());
        System.out.println(deductionBrad.getGross_salary()+"\t=\t"+deductionBrad.getWithholdingTax());
        System.out.println(deductionAnthony.getGross_salary()+"\t=\t"+deductionAnthony.getWithholdingTax());
        System.out.println(deductionAlice.getGross_salary()+"\t=\t"+deductionAlice.getWithholdingTax());
        System.out.println(copyOfWitholdingTaxSample.getGross_salary()+"\t=\t"+copyOfWitholdingTaxSample.getWithholdingTax());


        Deduction deduction = new Deduction(21749,21749);
        System.out.println(deduction.deductSSS());
    }
}
