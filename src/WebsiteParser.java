import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by gurkanyilmaz on 19/08/15.
 */
public class WebsiteParser extends JTabbedPane {
    Document doc;
    JScrollPane sPane;

    WebsiteParser(){
        try {
            doc = Jsoup.connect("http://ciceksepeti.com").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLinks();
        addTab("Links" , sPane);
    }

    public void getLinks(){
        Elements links = doc.getElementsByTag("a");
        JPanel linkpanel = new JPanel();
        linkpanel.setLayout(new GridLayout(links.size(),1 ));
        for(Element link : links) {
            String l = link.attr("href");
            if(l.length() > 0){
                if(l.length() < 4)
                    l = doc.baseUri()+l.substring(1);
                else if(!l.substring(0,4).equals("http"));
                JLabel label = new JLabel(l);
                linkpanel.add(label);

            }
        }
        sPane = new JScrollPane(linkpanel);
        sPane.setPreferredSize(new Dimension(350, 350));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Website Parser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WebsiteParser websiteParser = new WebsiteParser();
        frame.add(websiteParser);
        frame.setVisible(true);
        frame.setSize(600,600);
    }


}
