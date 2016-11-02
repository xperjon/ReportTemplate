package com.blinfosoft.report.domain.strategy;

import com.blinfosoft.report.domain.ReportRow;

/**
 *
 * @author jel
 */
@FunctionalInterface
public interface RowPrinter {

    String print(ReportRow row);
}
