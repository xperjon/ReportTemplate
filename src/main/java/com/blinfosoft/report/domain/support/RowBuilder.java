package com.blinfosoft.report.domain.support;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.Row;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.domain.strategy.RowPrinter;
import com.blinfosoft.report.domain.strategy.impl.DefaultRowAmountWriter;
import com.blinfosoft.report.domain.strategy.impl.DefaultRowPrinter;
import java.util.List;
import java.util.Optional;
import com.blinfosoft.report.domain.strategy.RowAmountWriter;
import java.util.Collections;

/**
 *
 * @author jel
 */
public class RowBuilder {

    private final Row row;

    public RowBuilder() {
        this.row = new Row(RowType.BLANK, "", new DefaultRowAmountWriter(), new DefaultRowPrinter(), Collections.EMPTY_LIST);
    }

    public RowBuilder type(RowType type) {
        this.row.setType(type);
        return this;
    }

    public RowBuilder label(String label) {
        this.row.setLabel(label);
        return this;
    }

    public RowBuilder balanceWriter(RowAmountWriter writer) {
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
