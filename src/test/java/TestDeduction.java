import com.example.fx123.Deduction;

public class TestDeduction {
    public static void main(String[] args) {
        float hoursWorked = 150;
        Deduction deductionJose     = new Deduction(62_670,(hoursWorked * 373.04f));
        Deduction deductionChristian= new Deduction(42_975,(hoursWorked * 255.80f));
        Deduction deductionBrad     = new Deduction(42_975,(hoursWorked * 255.80f));
        Deduction deductionAnthony  = new Deduction(60_825,(hoursWorked * 362.05f));
        Deduction deductionAlice    = new Deduction(22_500,(hoursWorked * 133.93f));

        Deduction copyOfWitholdingTaxSample    = new Deduction(25_000,25_000);


        System.out.println("\n\n\nSample SSS");
        System.out.println(deductionJose.getCompensation()+"\t=\t"+deductionJose.sssContribution());
        System.out.println(deductionChristian.getCompensation()+"\t=\t"+deductionChristian.sssContribution());
        System.out.println(deductionBrad.getCompensation()+"\t=\t"+deductionBrad.sssContribution());
        System.out.println(deductionAnthony.getCompensation()+"\t=\t"+deductionAnthony.sssContribution());
        System.out.println(deductionAlice.getCompensation()+"\t=\t"+deductionAlice.sssContribution());
        System.out.println(copyOfWitholdingTaxSample.getCompensation()+"\t=\t"+copyOfWitholdingTaxSample.philHealthContribution());

        System.out.println("\n\n\nSample PhilHealth");
        System.out.println(deductionJose.getBasic_salary()+"\t=\t"+deductionJose.philHealthContribution());
        System.out.println(deductionChristian.getBasic_salary()+"\t=\t"+deductionChristian.philHealthContribution());
        System.out.println(deductionBrad.getBasic_salary()+"\t=\t"+deductionBrad.philHealthContribution());
        System.out.println(deductionAnthony.getBasic_salary()+"\t=\t"+deductionAnthony.philHealthContribution());
        System.out.println(deductionAlice.getBasic_salary()+"\t=\t"+deductionAlice.philHealthContribution());
        System.out.println(copyOfWitholdingTaxSample.getBasic_salary()+"\t=\t"+copyOfWitholdingTaxSample.philHealthContribution());

        System.out.println("\n\n\nSample PagIBIG");
        System.out.println(deductionJose.pagIbigContribution());
        System.out.println(deductionChristian.pagIbigContribution());
        System.out.println(deductionBrad.pagIbigContribution());
        System.out.println(deductionAnthony.pagIbigContribution());
        System.out.println(deductionAlice.pagIbigContribution());
        System.out.println(copyOfWitholdingTaxSample.pagIbigContribution());

        System.out.println("\n\n\nSample Withholding Tax");
        System.out.println(deductionJose.getCompensation()+"\t=\t"+deductionJose.getWithholdingTax());
        System.out.println(deductionChristian.getCompensation()+"\t=\t"+deductionChristian.getWithholdingTax());
        System.out.println(deductionBrad.getCompensation()+"\t=\t"+deductionBrad.getWithholdingTax());
        System.out.println(deductionAnthony.getCompensation()+"\t=\t"+deductionAnthony.getWithholdingTax());
        System.out.println(deductionAlice.getCompensation()+"\t=\t"+deductionAlice.getWithholdingTax());
        System.out.println(copyOfWitholdingTaxSample.getCompensation()+"\t=\t"+copyOfWitholdingTaxSample.getWithholdingTax());
    }
}
