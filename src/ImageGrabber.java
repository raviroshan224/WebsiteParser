import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gurkanyilmaz on 19/08/15.
 */
public class ImageGrabber extends JScrollPane {
    Elements images;
    Document doc;
    JPanel panel;

    ImageGrabber(Document doc){
        this.doc = doc;
        images= doc.select("img[src~(?i\\.(png|jpe?g|gif)]");
        panel = new JPanel(new GridLayout(images.size() , 1));
        grabImages();
        setViewportView(panel);
        setPreferredSize(new Dimension(350 , 350));

    }

    private void grabImages() {
        for (Element image : images) {
            String l = images.attr("src");
            if (l.length() > 0) {
                if (l.length() < 4)
                    l = doc.baseUri() + l.substring(1);
                else if (!l.substring(0, 4).equals("http")) ;
                JLabel label = new JLabel(l);
                panel.add(label);
            }
        }


    }
}
