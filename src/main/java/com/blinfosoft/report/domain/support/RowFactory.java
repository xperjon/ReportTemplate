package com.blinfosoft.report.domain.support;

import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.account.AccountDataStore;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.domain.strategy.impl.DetailRowAmountWriter;
import com.blinfosoft.report.domain.strategy.impl.DetailRowPrinter;
import com.blinfosoft.report.domain.strategy.impl.SummaryRowAmountWriter;
import com.blinfosoft.report.domain.strategy.impl.SummaryRowPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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
                        .balanceWriter(new DetailRowAmountWriter())
                        .printer(new DetailRowPrinter())
                        .accounts(getAccountsFromXmlTemplate(row))
                        .build();
            case SR1:
            case SR2:
            case SR3:
                return new RowBuilder()
                        .type(type)
                        .label(label)
                        .balanceWriter(new SummaryRowAmountWriter())
                        .printer(new SummaryRowPrinter())
                        .build();
            default:
                return new RowBuilder()
                        .type(type)
                        .label(label)
                        .build();
        }
    }

    private Stream<Account> getAccountStream(XmlNode row) {
        String start = row.getAttribute("start");
        String end = row.getAttribute("end");
        return IntStream.rangeClosed(Integer.parseInt(start), Integer.parseInt(end))
                .mapToObj(number -> accountStore.getAccountByNumber(number))
                .filter(a -> a.isPresent())
                .map(a -> a.get());
    }

    public List<Account> getAccountsFromXmlTemplate(XmlNode row) {
        Optional<XmlNode> accountsXml = row.hasChildNamed("accounts") ? Optional.of(row.getChild("accounts")) : Optional.empty();

        Function<XmlNode,Stream<XmlNode>> toStream = xml -> Stream.of(xml);
        Supplier<? extends Stream<XmlNode>> emptyStream = () -> Stream.empty();
        Function<XmlNode,Stream<XmlNode>> toAccountRangeStream = xml -> xml.getChildren("account-range").stream();
        
        return accountsXml
                .map(toStream)
                .orElseGet(emptyStream)
                .flatMap(toAccountRangeStream)
                .flatMap(this::getAccountStream)
                .collect(Collectors.toList());
    }
}
