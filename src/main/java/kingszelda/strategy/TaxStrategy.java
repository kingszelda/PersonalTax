package kingszelda.strategy;


import kingszelda.bo.YearSalary;

import java.math.BigDecimal;

public interface TaxStrategy {

    YearSalary calcYearSalary(BigDecimal shouldPayTaxMonthSalary, BigDecimal monthSalary);
}
