package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.domain.strategy.RowBalanceWriter;
import com.blinfosoft.report.util.NullSummingCollector;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jel
 */
public class SummaryRowBalanceWriter implements RowBalanceWriter {

    @Override
    public Optional<Double> writeBalance(ReportRow row, Report report) {
        ReportRow matchingHeaderRow = report.findMatchingHeaderRow(row);

        List<ReportRow> detailRows = report.getRowsInBetween(matchingHeaderRow.getLineNumber(),
                row.getLineNumber(),
                RowType.D);

        return detailRows.stream()
                .map(r -> r.getBalance())
                .collect(NullSummingCollector.sum());
    }

}
