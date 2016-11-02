package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.blinfosoft.report.domain.strategy.RowBalanceWriter;
import com.blinfosoft.report.util.NullSummingCollector;

/**
 *
 * @author jel
 */
public class DetailRowBalanceWriter implements RowBalanceWriter {

    @Override
    public Optional<Double> writeBalance(ReportRow row, Report report) {
        List<Account> accounts = row.getAccounts().orElse(Collections.EMPTY_LIST);
        return accounts.stream()
                .map(a -> a.getBalance())
                .collect(NullSummingCollector.sum());
    }
}
