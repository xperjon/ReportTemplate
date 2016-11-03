
import com.blinfosoft.report.domain.IncomeStatement;
import com.blinfosoft.report.domain.ReportRow;
import com.blinfosoft.report.domain.RowType;
import com.blinfosoft.report.domain.support.RowBuilder;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jel
 */
public class IncomeStatementTest {

    ReportRow r1;
    ReportRow r2;
    ReportRow r3;
    ReportRow r4;
    ReportRow r5;
    ReportRow r6;
    ReportRow r7;

    IncomeStatement incomeStatement;

    public IncomeStatementTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        r1 = new RowBuilder().type(RowType.RR1).build();
        r2 = new RowBuilder().type(RowType.RR2).build();
        r3 = new RowBuilder().type(RowType.D).build();
        r4 = new RowBuilder().type(RowType.D).build();
        r5 = new RowBuilder().type(RowType.D).build();
        r6 = new RowBuilder().type(RowType.SR2).build();
        r7 = new RowBuilder().type(RowType.SR1).build();
        List<ReportRow> rows = Arrays.asList(r1, r2, r3, r4, r5, r6, r7);
        incomeStatement = new IncomeStatement(rows);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindMatchingHeaderRow() {
        ReportRow rr1 = incomeStatement.findMatchingHeaderRow(r6);
        assertEquals(r2.getLineNumber(), rr1.getLineNumber());
    }
    @Test
    public void testNonFindMatchingHeaderRow() {
        ReportRow rr1 = incomeStatement.findMatchingHeaderRow(r6);
        assertNotEquals(r1.getLineNumber(), rr1.getLineNumber());
    }
    
    @Test
    public void testGetRowsInBetween() {
        List<ReportRow> rowsInBetween = incomeStatement.getRowsInBetween(1, 5, RowType.D);
        assertEquals(3, rowsInBetween.size());
    }
}
