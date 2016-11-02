package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.strategy.RowPrinter;

/**
 *
 * @author jel
 */
public class DefaultRowPrinter implements RowPrinter {

    @Override
    public String print(ReportRow row) {
        return row.getLabel();
    }

}
