package kingszelda.bo;

import java.math.BigDecimal;

public class TaxTable {

    private BigDecimal baseLine;

    private BigDecimal topLine;

    private BigDecimal rate;

    private BigDecimal fastSubNumber;

    public TaxTable(BigDecimal baseLine, BigDecimal topLine, BigDecimal rate, BigDecimal fastSubNumber) {
        this.baseLine = baseLine;
        this.topLine = topLine;
        this.rate = rate;
        this.fastSubNumber = fastSubNumber;
    }

    public boolean match(BigDecimal salaryShouldPayTax) {
        return salaryShouldPayTax.compareTo(baseLine) > 0 && salaryShouldPayTax.compareTo(topLine) <= 0;
    }

    public BigDecimal calc(BigDecimal salaryShouldPayTax) {
        return salaryShouldPayTax.multiply(rate).subtract(fastSubNumber);
    }


    public BigDecimal getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(BigDecimal baseLine) {
        this.baseLine = baseLine;
    }

    public BigDecimal getTopLine() {
        return topLine;
    }

    public void setTopLine(BigDecimal topLine) {
        this.topLine = topLine;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getFastSubNumber() {
        return fastSubNumber;
    }

    public void setFastSubNumber(BigDecimal fastSubNumber) {
        this.fastSubNumber = fastSubNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaxTable{");
        sb.append("baseLine=").append(baseLine);
        sb.append(", topLine=").append(topLine);
        sb.append(", rate=").append(rate);
        sb.append(", fastSubNumber=").append(fastSubNumber);
        sb.append('}');
        return sb.toString();
    }
}
