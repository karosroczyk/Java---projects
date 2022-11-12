import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();
    public Document(String title){
        this.title = title;
    }
    void setTitle(String title){
        this.title = title;
    }
    void setPhoto(String url){
        photo = new Photo(url);
    }
    Section addSection(String title){
        Section newSection = new Section(title);
        sections.add(newSection);
        return newSection;
    }
    void writeHTML(PrintStream stream){
        String title = "<!DOCTYPE html>\n<html>\n<body>\n\n<h1>"
                + this.title
                + "</h1>\n\n";

        stream.printf(title);

        photo.writeHTML(stream);

        for(Section section : sections)
            section.writeHTML(stream);

        stream.printf("</body>\n</html>");
        stream.flush();
    }
}
