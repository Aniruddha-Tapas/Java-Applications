package downloading.a.web.page.using.socket.pkgclass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class OpenURLSocket {

    public static void main(String[] args) {
        JFrame frame = new SimpleFrame();
        frame.show();
    }

}

class SimpleFrame extends JFrame implements ActionListener {

    private BufferedReader in;
    private PrintWriter out;
    private JTextField urltext;
    private JTextArea text;
    private JLabel urlLbl;
    private JEditorPane edit;

    public SimpleFrame() {
        setTitle("URL Download Demo");
        setSize(200, 300);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

        urlLbl = new JLabel("Please enter a URL to download");
        getContentPane().add(urlLbl);

        urltext = new JTextField(20);
        urltext.setText("http://");
        getContentPane().add(urltext);

        text = new JTextArea(10, 50);
        getContentPane().add(text);

        JScrollPane p1 = new JScrollPane(text);
        getContentPane().add(p1);

        JButton downButton = new JButton("Download URL");
        downButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(downButton);

        getContentPane().add(downButton);
    }

    public void actionPerformed(ActionEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                downloadURL();
            }
        }
        );
    }

    public void downloadURL() {
        try {
            Socket s = new Socket(urltext.getText(), 80);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            boolean more = true;

            while (more) {
                String line = in.readLine();
                if (line == null) {
                    more = false;
                } else {
                    text.setText(text.getText() + line);
                }
            }
        } catch (IOException e) {
            text.setText("Error: " + e);
        }
    }
}
