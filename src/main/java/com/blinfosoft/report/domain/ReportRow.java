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

    Optional<Double> getAmount();
    
    void setAmount(Double amount);

    Optional<List<Account>> getAccounts();

    void setReport(Report report);
    
    Report getReport();
    
    boolean isMatchingHeaderRow(ReportRow otherRow);

}
