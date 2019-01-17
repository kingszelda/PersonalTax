package kingszelda.strategy;

import java.math.BigDecimal;

/**
 * 18年10-12月的扣税方式
 */
public class OctOldTaxStrategyImplImpl extends BaseOldTaxStrategyImpl {
    private static final BigDecimal BaseLine = new BigDecimal("5000");

    @Override
    protected BigDecimal getBaseLine() {
        return BaseLine;
    }
}
