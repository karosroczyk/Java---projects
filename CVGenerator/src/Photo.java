import java.io.PrintStream;

public class Photo {
    String url;

    Photo(String url){
        this.url = url;
    }
    void writeHTML(PrintStream stream){
        stream.printf("<img src=\"%s\" height=\"42\" width=\"42\"/>\n\n",url);
    }
}
