package com.blinfosoft.report.domain.strategy;

import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import java.util.Optional;

/**
 *
 * @author jel
 */
@FunctionalInterface
public interface RowBalanceWriter {

    Optional<Double> writeBalance(ReportRow row, Report report);
}
