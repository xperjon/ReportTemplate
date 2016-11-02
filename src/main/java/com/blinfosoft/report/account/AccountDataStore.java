package com.blinfosoft.report.account;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author jel
 */
public class AccountDataStore {

    private final XmlNode boxRoot;

    public AccountDataStore(String fileName) throws FileNotFoundException {
        this(new DocumentToXmlNodeParser(new FileInputStream(fileName)).parse());
    }

    public AccountDataStore(XmlNode boxRoot) {
        this.boxRoot = boxRoot;
    }

    public Optional<Account> getAccountByNumber(Integer number) {
        List<XmlNode> accounts = boxRoot.getXmlNode("accounts").getChildren("account");
        return accounts.stream()
                .filter(a -> a.getAttribute("number").equals("" + number))
                .findFirst()
                .map(a -> new Account(number, getBalance(a)));
    }

    private Double getBalance(XmlNode accountXml) {
        Optional<String> closingBalance = Optional.ofNullable(accountXml.getAttribute("closing-balance"));
        Optional<String> result = Optional.ofNullable(accountXml.getAttribute("result"));
        return closingBalance
                .map(cb -> Double.parseDouble(cb))
                .orElse(result.map(r -> Double.parseDouble(r))
                        .orElse(null));
    }

}
