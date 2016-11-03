package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.util.NullSummingCollector;
import java.util.List;
import java.util.Optional;
import com.blinfosoft.report.domain.strategy.RowAmountWriter;

/**
 *
 * @author jel
 */
public class SummaryRowAmountWriter implements RowAmountWriter {

    @Override
    public Optional<Double> writeAmount(ReportRow row, Report report) {
        ReportRow matchingHeaderRow = report.findMatchingHeaderRow(row);

        List<ReportRow> detailRows = report.getRowsInBetween(matchingHeaderRow.getLineNumber(),
                row.getLineNumber(),
                RowType.D);

        return detailRows.stream()
                .map(r -> r.getAmount())
                .collect(NullSummingCollector.sum());
    }

}
