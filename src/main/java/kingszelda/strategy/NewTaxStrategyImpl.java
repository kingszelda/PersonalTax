package kingszelda.strategy;


import kingszelda.bo.TaxTable;
import kingszelda.bo.YearSalary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NewTaxStrategyImpl implements TaxStrategy {
    private static List<TaxTable> taxTableList = new ArrayList<>();

    private static BigDecimal baseLine = new BigDecimal("5000");

    static {
        taxTableList.add(new TaxTable(new BigDecimal("-1"), new BigDecimal("36000"), new BigDecimal("0.03"), new BigDecimal(new BigInteger("0"))));
        taxTableList.add(new TaxTable(new BigDecimal("36000"), new BigDecimal("144000"), new BigDecimal("0.10"), new BigDecimal(new BigInteger("2520"))));
        taxTableList.add(new TaxTable(new BigDecimal("144000"), new BigDecimal("300000"), new BigDecimal("0.20"), new BigDecimal(new BigInteger("16920"))));
        taxTableList.add(new TaxTable(new BigDecimal("300000"), new BigDecimal("420000"), new BigDecimal("0.25"), new BigDecimal(new BigInteger("31920"))));
        taxTableList.add(new TaxTable(new BigDecimal("420000"), new BigDecimal("660000"), new BigDecimal("0.30"), new BigDecimal(new BigInteger("52920"))));
        taxTableList.add(new TaxTable(new BigDecimal("660000"), new BigDecimal("960000"), new BigDecimal("0.35"), new BigDecimal(new BigInteger("85920"))));
        taxTableList.add(new TaxTable(new BigDecimal("960000"), new BigDecimal("99999999"), new BigDecimal("0.45"), new BigDecimal(new BigInteger("181920"))));
    }

    @Override
    public YearSalary calcYearSalary(BigDecimal shouldPayTaxMonthSalary, BigDecimal monthSalary) {
        BigDecimal monthSalaryShouldPayTax = shouldPayTaxMonthSalary.subtract(baseLine);
        BigDecimal yearSalary = shouldPayTaxMonthSalary.multiply(new BigDecimal("12"));
        if (monthSalaryShouldPayTax.compareTo(BigDecimal.ZERO) <= 0) {
            monthSalaryShouldPayTax = BigDecimal.ZERO;
        }
        BigDecimal yearSalaryTax = null;
        for (TaxTable taxTable : taxTableList) {
            if (taxTable.match(monthSalaryShouldPayTax.multiply(new BigDecimal(12)))) {
                yearSalaryTax = taxTable.calc(monthSalaryShouldPayTax.multiply(new BigDecimal(12)));
                break;
            }
        }
        BigDecimal salaryWithoutTax = yearSalary.subtract(yearSalaryTax);
        return new YearSalary(monthSalary, yearSalary, salaryWithoutTax, yearSalaryTax);
    }
}
