package com.blinfosoft.report.domain.strategy.impl;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.domain.Report;
import com.blinfosoft.report.domain.ReportRow;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.blinfosoft.report.util.NullSummingCollector;
import com.blinfosoft.report.domain.strategy.RowAmountWriter;

/**
 *
 * @author jel
 */
public class DetailRowAmountWriter implements RowAmountWriter {

    @Override
    public Optional<Double> writeAmount(ReportRow row, Report report) {
        List<Account> accounts = row.getAccounts();
        return accounts.stream()
                .map(a -> a.getBalance())
                .collect(NullSummingCollector.sum());
    }
}
