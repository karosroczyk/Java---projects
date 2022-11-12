import java.io.PrintStream;
import java.util.*;

public class UnorderedList {
    //String content;
    List<ListItem> listItemList = new ArrayList<>();
    void writeHTML(PrintStream stream) {
        stream.printf("\t<ul>\n");

        for (ListItem listItem : listItemList)
            listItem.writeHTML(stream);

        stream.printf("\t</ul>\n");
    }
}
