
import com.blinfosoft.report.account.Account;
import com.blinfosoft.report.account.AccountDataStore;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.support.RowFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.core.io.ClassPathResource;
import se.blinfo.box.core.convert.SieToBoxConverter;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author jel
 */
public class RowFactoryTest {

    static XmlNode templateRoot;
    static XmlNode boxRoot;
    AccountDataStore mockedAccountDataStore;
    
    public RowFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {

        //Setup
        Reader reader = new InputStreamReader(new ClassPathResource("Income Statement Template.xml").getInputStream(), "UTF-8");
        templateRoot = new DocumentToXmlNodeParser(reader).parse();

        XmlDocument parse = new SieToBoxConverter(new ClassPathResource("corporate accounting sie.se").getInputStream()).parse();
        boxRoot = parse.getRoot();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockedAccountDataStore = Mockito.mock(AccountDataStore.class);
        when(mockedAccountDataStore.getAccountByNumber(Matchers.anyInt())).then(new Answer<Optional<Account>>() {
            @Override
            public Optional<Account> answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                return Optional.of(new Account((Integer)arguments[0]));
            }
        });
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        RowFactory rowFactory = new RowFactory(mockedAccountDataStore);
        
        XmlNode rowR0202= templateRoot.getChildren("Row").stream()
                .filter(xml -> xml.hasChildNamed("id"))
                .filter(xml -> xml.getChild("id").getText().equals("R02-02"))
                .findFirst()
                .get();
        
        ReportRow reportRow = rowFactory.create(rowR0202);
        assertEquals(true, reportRow.getAccounts().isPresent());
        List<Account> accounts = reportRow.getAccounts().get();
        assertEquals(new Account(4900), accounts.get(0));
        assertEquals(new Account(4999), accounts.get(accounts.size()-1));
        assertEquals(true, accounts.contains(new Account(4932)));
        assertEquals(true, accounts.contains(new Account(4932)));
        assertEquals(true, accounts.contains(new Account(4940)));
        assertEquals(true, accounts.contains(new Account(4959)));
        assertEquals(true, accounts.contains(new Account(4970)));
        assertEquals(true, accounts.contains(new Account(4979)));
        assertEquals(true, accounts.contains(new Account(4990)));
        assertEquals(true, accounts.contains(new Account(4999)));
        
        
        assertEquals(false, accounts.contains(new Account(4931)));
    }
}
