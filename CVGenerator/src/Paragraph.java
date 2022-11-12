import java.io.PrintStream;

public class Paragraph {
    String content;
    Paragraph(){
        this.content = "";}
    Paragraph(String content){
        this.content = content;
    }
    void writeHTML(PrintStream stream){
        stream.printf("\t<p>" + this.content + "</p>\n");
    }
}
