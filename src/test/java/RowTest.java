import com.blinfosoft.report.domain.Row;
import com.blinfosoft.report.domain.RowType;
import java.util.Collections;
import java.util.Optional;
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
public class RowTest {

    public RowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void matchingHeaderRows() {
        Row row1 = new Row(RowType.SR3, "Foo", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        Row row2 = new Row(RowType.RR3, "Bar", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        assertEquals(true, row1.isMatchingHeaderRow(row2));
        assertEquals(true, row2.isMatchingHeaderRow(row1));
        Row row3 = new Row(RowType.SR1, "Foo", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        Row row4 = new Row(RowType.RR1, "Bar", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        assertEquals(true, row3.isMatchingHeaderRow(row4));
        assertEquals(true, row4.isMatchingHeaderRow(row3));
    }

    @Test
    public void nonMatchingHeaderRows() {
        Row row1 = new Row(RowType.SR2, "Foo", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        Row row2 = new Row(RowType.RR3, "Bar", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        assertEquals(false, row1.isMatchingHeaderRow(row2));
        assertEquals(false, row2.isMatchingHeaderRow(row1));
        Row row3 = new Row(RowType.SR3, "Foo", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        Row row4 = new Row(RowType.RR1, "Bar", (row, report) -> Optional.empty(), (r) -> "", Collections.EMPTY_LIST);
        assertEquals(false, row3.isMatchingHeaderRow(row4));
        assertEquals(false, row4.isMatchingHeaderRow(row3));
    }
}
