package kingszelda.strategy;

import java.math.BigDecimal;

/**
 * 18年1-9月的扣税方式
 */
public class OldTaxStrategyImplImpl extends BaseOldTaxStrategyImpl {
    private static final BigDecimal BaseLine = new BigDecimal("3500");

    @Override
    protected BigDecimal getBaseLine() {
        return BaseLine;
    }
}
