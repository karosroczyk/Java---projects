import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @org.junit.jupiter.api.Test
    void writeHTML() {
        String imageUrl = "jan-kowalski.png";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        new Photo(imageUrl).writeHTML(ps);
        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("src="));
        assertTrue(result.contains(imageUrl));
    }
}