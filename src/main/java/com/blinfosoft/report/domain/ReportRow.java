package com.blinfosoft.report.domain;

import com.blinfosoft.report.account.Account;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jel
 */
public interface ReportRow {

    Integer getLineNumber();

    void setLineNumber(Integer number);

    RowType getType();

    String getLabel();

    Optional<Double> getBalance();

    Optional<List<Account>> getAccounts();

    void setReport(Report report);
    
    boolean isMatchingHeaderRow(ReportRow otherRow);

}
