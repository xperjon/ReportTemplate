package com.blinfosoft.report.domain.support;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.Row;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.domain.strategy.RowBalanceWriter;
import com.blinfosoft.report.domain.strategy.RowPrinter;
import com.blinfosoft.report.domain.strategy.impl.DefaultRowBalanceWriter;
import com.blinfosoft.report.domain.strategy.impl.DefaultRowPrinter;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jel
 */
public class RowBuilder {

    private final Row row;

    public RowBuilder() {
        this.row = new Row(RowType.BLANK, "", new DefaultRowBalanceWriter(), new DefaultRowPrinter(), Optional.empty());
    }

    public RowBuilder type(RowType type) {
        this.row.setType(type);
        return this;
    }

    public RowBuilder label(String label) {
        this.row.setLabel(label);
        return this;
    }

    public RowBuilder balanceWriter(RowBalanceWriter writer) {
        this.row.setBalanceWriter(writer);
        return this;
    }

    public RowBuilder printer(RowPrinter printer) {
        this.row.setRowPrinter(printer);
        return this;
    }
    public RowBuilder accounts(List<Account> accounts) {
        this.row.setAccounts(accounts);
        return this;
    }

    public ReportRow build() {
        return this.row;
    }

}
