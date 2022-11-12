import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;


class ParagraphWithListTest {

    @Test
    void addListItem() {
        ParagraphWithList pwl= new ParagraphWithList();
        int lengthBefore = pwl.unorderedListList.listItemList.size();

        pwl.addListItem("First item");

        int lengthAfter = pwl.unorderedListList.listItemList.size();

        assertEquals(lengthBefore+1, lengthAfter);
    }

    @Test
    void writeHTML() {
        ParagraphWithList pwl= new ParagraphWithList();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        pwl.writeHTML(ps);

        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertTrue(result.contains("<p>") && result.contains("</p>"));
        assertTrue(result.contains("<ul>") && result.contains("</ul>"));
    }
}