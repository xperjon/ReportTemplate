package com.blinfosoft.report.domain.strategy;

import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import java.util.Optional;

/**
 *
 * @author jel
 */
@FunctionalInterface
public interface RowAmountWriter {

    Optional<Double> writeAmount(ReportRow row, Report report);
}
