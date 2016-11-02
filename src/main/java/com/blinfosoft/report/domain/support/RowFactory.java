package com.blinfosoft.report.domain.support;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.account.AccountDataStore;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.Row;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.domain.strategy.impl.DefaultRowBalanceWriter;
import com.blinfosoft.report.domain.strategy.impl.DefaultRowPrinter;
import com.blinfosoft.report.domain.strategy.impl.DetailRowBalanceWriter;
import com.blinfosoft.report.domain.strategy.impl.DetailRowPrinter;
import com.blinfosoft.report.domain.strategy.impl.SummaryRowBalanceWriter;
import com.blinfosoft.report.domain.strategy.impl.SummaryRowPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author jel
 */
public class RowFactory {

    final AccountDataStore accountStore;

    public RowFactory(AccountDataStore accountStore) {
        this.accountStore = accountStore;
    }

    public ReportRow create(XmlNode row) {
        RowType type = RowType.get(row.hasChildNamed("type") ? row.getChild("type").getText() : "");
        String label = row.hasChildNamed("label") ? row.getChild("label").getText() : "MISSING LABEL";
        switch (type) {
            case D:
                return new RowBuilder()
                        .type(type)
                        .label(label)
                        .balanceWriter(new DetailRowBalanceWriter())
                        .printer(new DetailRowPrinter())
                        .accounts(getAccountsFromXmlTemplate(row))
                        .build();
            case SR1:
            case SR2:
            case SR3:
                return new RowBuilder()
                        .type(type)
                        .label(label)
                        .balanceWriter(new SummaryRowBalanceWriter())
                        .printer(new SummaryRowPrinter())
                        .build();
            default:
                return new RowBuilder()
                        .type(type)
                        .label(label)
                        .build();
        }
    }

    private List<Account> getAccountsFromXmlTemplate(XmlNode row) {

        XmlNode accountsXml = row.hasChildNamed("accounts") ? row.getChild("accounts") : null;
        Optional<XmlNode> accountsNode = Optional.ofNullable(accountsXml);

        Optional<List<XmlNode>> accountRange = accountsNode.map(a -> a.getChildren("account-range"));

        List<Account> result = new ArrayList<>();

        accountRange.ifPresent((l) -> {
            l.stream().forEach((XmlNode xml) -> {
                String start = xml.getAttribute("start");
                String end = xml.getAttribute("end");
                List<Account> collect = IntStream.rangeClosed(Integer.parseInt(start), Integer.parseInt(end))
                        .mapToObj(number -> accountStore.getAccountByNumber(number))
                        .filter(a -> a.isPresent())
                        .map(a -> a.get())
                        .collect(Collectors.toList());
                result.addAll(collect);
            });
        });

        return result;
    }
}
