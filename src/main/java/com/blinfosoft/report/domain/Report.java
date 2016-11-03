package com.blinfosoft.report.domain;

import java.util.List;

/**
 *
 * @author jel
 */
public interface Report {

    public ReportType getType();

    public ReportRow findMatchingHeaderRow(ReportRow row);

    public List<ReportRow> getRowsInBetween(Integer from, Integer to, RowType type);

    public static enum ReportType {
        INCOME_STATEMENT,
        BALANCE_SHEET
    }

}
