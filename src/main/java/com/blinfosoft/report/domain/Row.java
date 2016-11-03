package com.blinfosoft.report.domain;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.domain.strategy.RowPrinter;
import java.util.List;
import java.util.Optional;
import com.blinfosoft.report.domain.strategy.RowAmountWriter;

/**
 *
 * @author jel
 */
public class Row implements ReportRow {

    private RowType type;
    private String label;
    private Double amount;
    private RowAmountWriter amountWriter;
    private RowPrinter printer;
    private Optional<List<Account>> accounts;
    private Report report;
    private Integer lineNumber;

    public Row(RowType type, String label, RowAmountWriter balanceWriter, RowPrinter printer, Optional<List<Account>> accounts) {
        this.type = type;
        this.label = label;
        this.amountWriter = balanceWriter;
        this.printer = printer;
        this.accounts = accounts;
    }

    public Row() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public RowType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return printer.print(this) + "\n";
    }

    @Override
    public Optional<Double> getAmount() {
        return amount != null ? Optional.of(amount) : amountWriter.writeAmount(this, report);
    }

    @Override
    public Optional<List<Account>> getAccounts() {
        return accounts;
    }

    @Override
    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public Integer getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean isMatchingHeaderRow(ReportRow otherRow) {
        String typeStr = this.type.name();
        String otherTypeStr = otherRow.getType().name();
        boolean isSameType = typeStr.regionMatches(1, otherTypeStr, 1, 1);
        boolean isSameLevel = typeStr.regionMatches(2, otherTypeStr, 2, 1);
        boolean isSumHeaderPair = !typeStr.regionMatches(0, otherTypeStr, 0, 1);
        return isSameLevel && isSameType && isSumHeaderPair;
    }

    public void setType(RowType type) {
        this.type = type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setBalanceWriter(RowAmountWriter writer) {
        this.amountWriter = writer;
    }

    public void setRowPrinter(RowPrinter printer) {
        this.printer = printer;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = Optional.of(accounts);
    }

    @Override
    public Report getReport() {
        return this.report;
    }

    @Override
    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
