package kingszelda.bo;

import java.math.BigDecimal;

public class YearSalary {

    private BigDecimal ordinarySalary;

    private BigDecimal salary;

    private BigDecimal salaryWithoutTax;

    private BigDecimal tax;

    private BigDecimal monthSalary;

    public YearSalary(BigDecimal monthSalary, BigDecimal salary, BigDecimal salaryWithoutTax, BigDecimal tax) {
        this.monthSalary = monthSalary;
        this.ordinarySalary = monthSalary.multiply(new BigDecimal("12"));
        this.salary = salary;
        this.salaryWithoutTax = salaryWithoutTax;
        this.tax = tax;
    }

    public BigDecimal getOrdinarySalary() {
        return ordinarySalary;
    }

    public void setOrdinarySalary(BigDecimal ordinarySalary) {
        this.ordinarySalary = ordinarySalary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalaryWithoutTax() {
        return salaryWithoutTax;
    }

    public void setSalaryWithoutTax(BigDecimal salaryWithoutTax) {
        this.salaryWithoutTax = salaryWithoutTax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(BigDecimal monthSalary) {
        this.monthSalary = monthSalary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("YearSalary{");
        sb.append("ordinarySalary=").append(ordinarySalary);
        sb.append(", salary=").append(salary);
        sb.append(", salaryWithoutTax=").append(salaryWithoutTax);
        sb.append(", tax=").append(tax);
        sb.append(", monthSalary=").append(monthSalary);
        sb.append('}');
        return sb.toString();
    }
}
