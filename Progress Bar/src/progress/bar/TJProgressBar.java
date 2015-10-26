/*
 * <Applet code=TJProgressBar width=400 height=100>
 * </Applet>
 */

package progress.bar;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class TJProgressBar extends JApplet {
    Container container = null;
    JButton startButton, stopButton;
    JTextField inputTextField, outputTextField;
    JProgressBar pBar = null;
    Timer timer = null;

    static int sum = 0;
    static int counter = 0;

    public void init() {
        // 1. Get the handle on the content pane and
        //    assign the grid layout.
        container = this.getContentPane();
        container.setLayout(new GridLayout(3,1));

        // 2. Add a horizontal box to the container.
        Box hbox1 = Box.createHorizontalBox();
        container.add(hbox1);

        // 3. Add labels and input and output text fields
        //    to the horizontal box.
        hbox1.add(Box.createHorizontalGlue());
        JLabel label1 = new JLabel("Sum of first ", JLabel.LEFT);
        label1.setFont(new Font("Dialog", Font.PLAIN, 15));
        hbox1.add(label1);

        inputTextField = new JTextField("100", 4);
        hbox1.add(inputTextField);

        JLabel label2 = new JLabel(" numbers is ", JLabel.LEFT);
        label2.setFont(new Font("Dialog", Font.PLAIN, 15));
        hbox1.add(label2);

        outputTextField = new JTextField(10);
        hbox1.add(outputTextField);
        hbox1.add(Box.createHorizontalGlue());

        // 4. Add another horizontal box to the container.
        Box hbox2 = Box.createHorizontalBox();
        container.add(hbox2);

        // 5. Add Start and Stop buttons to the container.
        startButton = new JButton("Start");
        startButton.addActionListener(new ButtonListener());
        hbox2.add(Box.createHorizontalGlue());
        hbox2.add(startButton);
        hbox2.add(Box.createHorizontalGlue());
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ButtonListener());
        hbox2.add(Box.createHorizontalGlue());
        hbox2.add(stopButton);
        hbox2.add(Box.createHorizontalGlue());

        // 6. Create and add a progress bar to the remaining
        //    display area.
        pBar = new JProgressBar();
        pBar.setStringPainted(true);
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        pBar.setBorder(border);
        pBar.setBackground(Color.white);
        pBar.setForeground(Color.blue);
        pBar.setMinimum(0);

        pBar.setMaximum(Integer.parseInt(inputTextField.getText()));
        container.add(pBar);

        // 7. Create a timer object.
        timer = new Timer(0, new TimerListener());
    }

    // 8. Timer listener that computes the sum of natural numbers,
    //    indicates the computation progress, and displays the result.
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Integer.parseInt(inputTextField.getText())> 0){
            counter++;
            sum = sum+counter;
            pBar.setValue(counter);
            outputTextField.setText(Integer.toString(sum));
            }
            else {
                outputTextField.setText("0");
            }

            if (counter >= Integer.parseInt(inputTextField.getText()))
               timer.stop();
        }
    }

    // 9. Button listener that actually starts or stops the
    //    process.
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.getText() == "Start") {
               outputTextField.setText("");
               if (inputTextField.getText() != " ") {
                    pBar.setMaximum(Integer.parseInt(
                               inputTextField.getText()));
                sum = 0;
                counter = 0;
                timer.start();
               }
            }
            else if (button.getText() == "Stop") {
                timer.stop();
                outputTextField.setText("");
                sum = 0;
                counter = 0;
                pBar.setValue(0);
            }
        }
    }
}
