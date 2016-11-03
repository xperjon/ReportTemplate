package com.blinfosoft.report.domain;

import com.blinfosoft.report.util.StreamUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author jel
 */
public class IncomeStatement implements Report {

    private Map<Integer, ReportRow> reportRows = new HashMap<>();

    public IncomeStatement(List<ReportRow> rows) {
        //TODO validation
        IntStream.range(0, rows.size())
                .forEach(i -> {
                    ReportRow r = rows.get(i);
                    r.setLineNumber(i);
                    r.setReport(this);
                    reportRows.put(i, rows.get(i));
                });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("-------------START INCOME STATEMENT---------------\n");
        reportRows.entrySet().stream().forEach(r -> sb.append(r.toString()));
        sb.append("-------------END INCOME STATEMENT---------------\n");
        return sb.toString();
    }

    @Override
    public ReportRow findMatchingHeaderRow(ReportRow row) {
        int matchingRowNumber = StreamUtils.reverseIntRange(1, row.getLineNumber())
                .filter(i -> reportRows.get(i).isMatchingHeaderRow(row))
                .findFirst()
                .orElse(0);
        return reportRows.get(matchingRowNumber);
    }

    @Override
    public List<ReportRow> getRowsInBetween(Integer from, Integer to, RowType type) {
        if (to <= from) {
            throw new IllegalArgumentException("From parameter must be strictly less than to parameter");
        }
        Set<Integer> lineNumbers = reportRows.keySet().stream()
                .filter(num -> num < to)
                .filter(num -> num > from)
                .filter(num -> type == reportRows.get(num).getType())
                .collect(Collectors.toSet());

        return reportRows.entrySet().stream()
                .filter(entry -> lineNumbers.contains(entry.getKey()))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public ReportType getType() {
        return ReportType.INCOME_STATEMENT;
    }

}
