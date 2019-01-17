package kingszelda.strategy;

import kingszelda.bo.TaxTable;
import kingszelda.bo.YearSalary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseOldTaxStrategyImpl implements TaxStrategy {
    private static List<TaxTable> taxTableList = new ArrayList<>();

    static {
        taxTableList.add(new TaxTable(new BigDecimal("-1"), new BigDecimal("1500"), new BigDecimal("0.03"), new BigDecimal(new BigInteger("0"))));
        taxTableList.add(new TaxTable(new BigDecimal("1500"), new BigDecimal("4500"), new BigDecimal("0.10"), new BigDecimal(new BigInteger("105"))));
        taxTableList.add(new TaxTable(new BigDecimal("4500"), new BigDecimal("9000"), new BigDecimal("0.20"), new BigDecimal(new BigInteger("555"))));
        taxTableList.add(new TaxTable(new BigDecimal("9000"), new BigDecimal("35000"), new BigDecimal("0.25"), new BigDecimal(new BigInteger("1005"))));
        taxTableList.add(new TaxTable(new BigDecimal("35000"), new BigDecimal("55000"), new BigDecimal("0.30"), new BigDecimal(new BigInteger("2755"))));
        taxTableList.add(new TaxTable(new BigDecimal("55000"), new BigDecimal("80000"), new BigDecimal("0.35"), new BigDecimal(new BigInteger("5505"))));
        taxTableList.add(new TaxTable(new BigDecimal("80000"), new BigDecimal("99999999"), new BigDecimal("0.45"), new BigDecimal(new BigInteger("13505"))));
    }

    @Override
    public YearSalary calcYearSalary(BigDecimal shouldPayTaxMonthSalary, BigDecimal monthSalary) {
        BigDecimal baseLine = getBaseLine();
        BigDecimal salaryShouldPayTax = shouldPayTaxMonthSalary.subtract(baseLine);
        if (salaryShouldPayTax.compareTo(BigDecimal.ZERO) <= 0) {
            salaryShouldPayTax = BigDecimal.ZERO;
        }
        BigDecimal monthTax = null;
        for (TaxTable taxTable : taxTableList) {
            if (taxTable.match(salaryShouldPayTax)) {
                monthTax = taxTable.calc(salaryShouldPayTax);
                break;
            }
        }
        BigDecimal yearSalaryWithoutTax = shouldPayTaxMonthSalary.subtract(monthTax).multiply(new BigDecimal("12"));
        BigDecimal yearSalary = shouldPayTaxMonthSalary.multiply(new BigDecimal("12"));
        BigDecimal yearTax = yearSalary.subtract(yearSalaryWithoutTax);
        return new YearSalary(monthSalary, yearSalary, yearSalaryWithoutTax, yearTax);
    }

    protected abstract BigDecimal getBaseLine();
}
