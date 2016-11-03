package com.blinfosoft.report;

import com.blinfosoft.report.domain.IncomeStatement;
import com.blinfosoft.report.account.AccountDataStore;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.support.RowFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
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

    public void start() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //Setup
        Reader reader = new InputStreamReader(new ClassPathResource("Income Statement Template.xml").getInputStream(), "UTF-8");
        XmlNode templateRoot = new DocumentToXmlNodeParser(reader).parse();
        
        XmlDocument parse = new SieToBoxConverter(new ClassPathResource("corporate accounting sie.se").getInputStream()).parse();
        AccountDataStore accounts = new AccountDataStore(parse.getRoot());
        RowFactory rowFactory = new RowFactory(accounts);

        //Create Rows
        List<ReportRow> rows = templateRoot.getChildren("Row").stream()
                .map(rowXml -> rowFactory.create(rowXml))
                .collect(Collectors.toList());

        //Create income statement
        IncomeStatement incomeStatement = new IncomeStatement(rows);
        System.out.println(incomeStatement);
    }
}
