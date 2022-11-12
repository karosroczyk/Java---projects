import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String title){
        this.title = title;
    }
    void setTitle(String titel){
        this.title = title;
    }
    Section addParagraph(String content){
        paragraphs.add(new Paragraph(content));
        return this;
    }
    Section addParagraph(Paragraph paragraph){
        paragraphs.add(paragraph);
        return this;
    }
    void writeHTML(PrintStream stream){
        stream.printf("<section>\n\t<h2>" + this.title + "</h2>\n");

        for(Paragraph paragraph : paragraphs)
            paragraph.writeHTML(stream);

        stream.printf("</section>\n\n");
    }
}
