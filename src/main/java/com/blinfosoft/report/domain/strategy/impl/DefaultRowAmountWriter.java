package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import java.util.Optional;
import com.blinfosoft.report.domain.strategy.RowAmountWriter;

/**
 *
 * @author jel
 */
public class DefaultRowAmountWriter implements RowAmountWriter {

    @Override
    public Optional<Double> writeAmount(ReportRow row, Report report) {
        //TODO refactor?
        return Optional.empty();
    }
}
