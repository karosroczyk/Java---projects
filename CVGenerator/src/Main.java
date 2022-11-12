import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Jan Kowalski - CV");
        cv.setPhoto("smile.jpg");
        cv.addSection("Wyksztalcenie")
                .addParagraph("2000-2005 Przedszkole im. Królowej Jadwigii w Krakowie")
                .addParagraph("2006-2012 SP7 im. Jana Pawla w Wieliczce");
        cv.addSection("Umiejetnosci")
                .addParagraph(
                        new ParagraphWithList().setContent("Wiedza na poziomie zaawansowanym:")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );

        try {
            cv.writeHTML(new PrintStream("cv.html", "ISO-8859-2"));
        }
        catch(Exception e){System.out.println(e);}
    }
}