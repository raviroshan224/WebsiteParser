import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gurkanyilmaz on 20/08/15.
 */
public class SwingLink extends JLabel {
    String text;
    URI uri;

    public SwingLink(String textIn , String uriIn){
        super();
        try {
            uri = new URI(uriIn);
        } catch (URISyntaxException e) {

        }
        text = textIn;
        setText(text , true);
        setToolTipText(uri.toString());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 open(uri);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setText(text , false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setText(text, true);
            }
        });

    }

    private void open(URI uri) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(uri);
            } catch (IOException e) {

            }
        }
    }

    private void setText(String inText, boolean underLine) {
        String link = underLine ? "<u>"+text+"</u>" : text;
        setText("<html><span style=\"color:red;\">" + link + "</span></html>");
    }
}
