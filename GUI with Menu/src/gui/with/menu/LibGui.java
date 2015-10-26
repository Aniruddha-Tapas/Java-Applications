package gui.with.menu;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LibGui extends JFrame {

    private JMenuBar menu;
    private JMenu m1, m2, m3;
    private JMenuItem AddNew, Search, Display, Delete, Exit, Modify, Help;
    private JButton btnAddNew, btnSearch, btnDelete, btnDisplay, btnModify, btnExit;
    private JPanel pMain, pSouth, pNorth, pCenter;
    private JTextArea tac;
    private JLabel lbllogo;

    public LibGui() {
        //menu bar and menu item initialization
        menu = new JMenuBar();
        m1 = new JMenu("Options");
        m2 = new JMenu("Programs");
        m3 = new JMenu("Help");

        AddNew = new JMenuItem("AddNew");

        Search = new JMenuItem("Search");

        Display = new JMenuItem("Display");
        Delete = new JMenuItem("Delete");
        Modify = new JMenuItem("Modify");
        Exit = new JMenuItem("Exit");
        Help = new JMenuItem("Help");

        //text area initialization
        tac = new JTextArea(2, 3);

        tac.setText("For: Birgang Institute of Technology"+" "+"Birganj,Parsa ");

	  tac.setForeground(Color.red);
        tac.setEditable(false);

        //button intialization
        btnAddNew = new JButton("ADDNEW");

        btnAddNew.setToolTipText("Add new Details");

        btnSearch = new JButton("SEARCH");
        btnSearch.setToolTipText("Search particular student");

        btnDelete = new JButton("DELETE");
        btnDelete.setToolTipText("Delete particular student");

        btnDisplay = new JButton("DISPLAY");
        btnDisplay.setToolTipText("Display particular student");

        btnModify = new JButton("MODIFY");
        btnModify.setToolTipText("Modifies particular student");

        btnExit = new JButton("EXIT");
        btnExit.setToolTipText("Out of Program");

	  //initialization panel
        pMain = new JPanel();
        pNorth = new JPanel();
        pSouth = new JPanel();
        pCenter = new JPanel();

        lbllogo = new JLabel(new ImageIcon("//G:/MFCfinish.gif"), JLabel.CENTER);

	   //add menuitem to menu
        m1.add(AddNew);
        m1.add(Search);
        m1.add(Display);
        m1.add(Delete);
        m1.add(Modify);

        m2.add(Exit);
        m3.add(Help);

        menu.add(m1);
        menu.add(m2);
        menu.add(m3);

        pMain.add(btnAddNew);
        pMain.add(btnSearch);
        pMain.add(btnDelete);
        pMain.add(btnDisplay);
        pMain.add(btnModify);
        pMain.add(btnExit);

        pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
        pMain.setBorder(BorderFactory.createTitledBorder("OPTIONS"));
        pMain.setLayout(new GridLayout(6, 1));
        pMain.setBackground(Color.white);

        pCenter.setBackground(Color.red);
        pCenter.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
        pCenter.setLayout(new GridLayout(2, 1));
        pCenter.add(lbllogo);
        pCenter.add(tac);

        pNorth.setBackground(Color.white);

        pNorth.add(menu);

        this.getContentPane().add(pMain, "West");
        this.getContentPane().add(pCenter, "Center");
        this.getContentPane().add(pNorth, "North");

        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocation(150, 150);
        this.setTitle("MENU");
        this.show();
    }

    public static void main(String[] args) {
        LibGui lg = new LibGui();
    }
}

/*Title	GUI with menu
Author	aseem
Author Email	aseemshrestha [at] hotmail.com
Description	Design of simple GUI with menu itemsCode :*/