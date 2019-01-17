package kingszelda;


import kingszelda.bo.YearSalary;
import kingszelda.strategy.NewTaxStrategyImpl;
import kingszelda.strategy.OctOldTaxStrategyImplImpl;
import kingszelda.strategy.OldTaxStrategyImplImpl;
import kingszelda.strategy.TaxStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxCompute {

    private static BigDecimal baseLine = new BigDecimal("25401");
    private static BigDecimal baseRate = new BigDecimal("0.24");
    private static TaxStrategy newTax = new NewTaxStrategyImpl();
    private static TaxStrategy oldTax = new OldTaxStrategyImplImpl();
    private static TaxStrategy octTax = new OctOldTaxStrategyImplImpl();

    public static void main(String[] args) {
        BigDecimal baseMonthSalary = new BigDecimal("3000");
        List<YearSalary> oldYearSalaryList = new ArrayList<>();
        List<YearSalary> octYearSalaryList = new ArrayList<>();
        List<YearSalary> newYearSalaryList = new ArrayList<>();
        while (baseMonthSalary.compareTo(new BigDecimal("100000")) < 0) {
            BigDecimal shouldPayTaxMonthSalary;
            if (baseMonthSalary.compareTo(baseLine) > 0) {
                shouldPayTaxMonthSalary = baseMonthSalary.subtract(baseLine.multiply(baseRate));
            } else {
                shouldPayTaxMonthSalary = baseMonthSalary.subtract(baseMonthSalary.multiply(baseRate));
            }
            newYearSalaryList.add(newTax.calcYearSalary(shouldPayTaxMonthSalary, baseMonthSalary));
            octYearSalaryList.add(octTax.calcYearSalary(shouldPayTaxMonthSalary, baseMonthSalary));
            oldYearSalaryList.add(oldTax.calcYearSalary(shouldPayTaxMonthSalary, baseMonthSalary));
            baseMonthSalary = baseMonthSalary.add(new BigDecimal("2000"));
        }
        System.out.println("月收入\t应该的年收入" +
                "\told减过五险一金后的税前年收入\told税后年收入\told每年税金" +
                "\toct减过五险一金后的税前年收入\toct税后年收入\toct每年税金" +
                "\tnew减过五险一金后的税前年收入\tnew税后年收入\tnew每年税金"
        );
        printSalary(oldYearSalaryList, octYearSalaryList, newYearSalaryList);
    }

    private static void printSalary(List<YearSalary> oldYearSalaryList, List<YearSalary> octYearSalaryList, List<YearSalary> yearSalaryList) {
        for (int i = 0; i < oldYearSalaryList.size(); i++) {
            System.out.println(oldYearSalaryList.get(i).getMonthSalary() + "\t"
                    + oldYearSalaryList.get(i).getOrdinarySalary() + "\t"
                    + oldYearSalaryList.get(i).getSalary() + "\t"
                    + oldYearSalaryList.get(i).getSalaryWithoutTax() + "\t"
                    + oldYearSalaryList.get(i).getTax() + "\t"
                    + octYearSalaryList.get(i).getSalary() + "\t"
                    + octYearSalaryList.get(i).getSalaryWithoutTax() + "\t"
                    + octYearSalaryList.get(i).getTax() + "\t"
                    + yearSalaryList.get(i).getSalary() + "\t"
                    + yearSalaryList.get(i).getSalaryWithoutTax() + "\t"
                    + yearSalaryList.get(i).getTax() + "\t"

            );
        }
    }
}
