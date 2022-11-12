import java.io.PrintStream;
public class ListItem {
    String content;

    ListItem(String content){
        this.content = content;
    }

    void writeHTML(PrintStream stream){
        stream.printf("\t\t<li>" + this.content + "</li>\n");
    }
}
