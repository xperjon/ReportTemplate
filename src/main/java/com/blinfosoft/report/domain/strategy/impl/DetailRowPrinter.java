package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.strategy.RowPrinter;

/**
 *
 * @author jel
 */
public class DetailRowPrinter implements RowPrinter {

    @Override
    public String print(ReportRow row) {
        return "\t" + row.getLabel() +"\t\t"+ row.getBalance();
    }

}
