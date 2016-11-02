package com.blinfosoft.report;

import com.blinfosoft.report.domain.IncomeStatement;
import com.blinfosoft.report.account.AccountDataStore;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.support.RowFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import se.blinfo.box.core.convert.SieToBoxConverter;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author jel
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        new Main().start();
    }

    AccountDataStore accounts;
    RowFactory rowFactory;

    public void start() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Reader reader = new InputStreamReader(new FileInputStream("C:\\Users\\jel\\Documents\\AnnualReport\\Income Statement Template.xml"), "UTF-8");
        XmlNode templateRoot = new DocumentToXmlNodeParser(reader).parse();

        XmlDocument parse = new SieToBoxConverter(new FileInputStream(new File("C:\\Users\\jel\\Documents\\SIE\\corporate accounting sie.SE"))).parse();

        accounts = new AccountDataStore(parse.getRoot());

        rowFactory = new RowFactory(accounts);

        List<ReportRow> rows = templateRoot.getChildren("Row").stream()
                .map(rowXml -> rowFactory.create(rowXml))
                .collect(Collectors.toList());

        IncomeStatement incomeStatement = new IncomeStatement(rows);
        System.out.println(incomeStatement);
    }
}
