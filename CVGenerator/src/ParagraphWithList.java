import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{

    UnorderedList unorderedListList = new UnorderedList();
    ParagraphWithList setContent(String content) {
        this.content = content;
        return this;
    }
    ParagraphWithList addListItem(String content){
        unorderedListList.listItemList.add(new ListItem(content));
        return this;
    }
    void writeHTML(PrintStream stream){
        super.writeHTML(stream);
        unorderedListList.writeHTML(stream);
    }
}
