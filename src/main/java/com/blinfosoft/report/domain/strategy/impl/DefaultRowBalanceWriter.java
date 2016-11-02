package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import java.util.Optional;
import com.blinfosoft.report.domain.strategy.RowBalanceWriter;

/**
 *
 * @author jel
 */
public class DefaultRowBalanceWriter implements RowBalanceWriter {

    @Override
    public Optional<Double> writeBalance(ReportRow row, Report report) {
        //TODO refactor?
        return Optional.empty();
    }
}
