package student.mgm;

import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class menu extends Frame implements
        WindowListener, ActionListener {

    MenuBar mb;
    MenuItem student, rollnowise, namewise, allresult;
    public static menu m;
    rollnowise rw;
    namewise n;
    student st;
    int x, y, d;

    public menu() {
        super("Menu");
        addWindowListener(this);
        x = y = 700;
        d = 10;
        setSize(x, y);
        setBackground(Color.orange);
        addMenu();
        show();
    }

    public static void main(String args[]) {
        m = new menu();
    }

    void addMenu() {
        MenuBar mb = new MenuBar();
        Menu register = new Menu("REGISTER");
        Menu inquery = new Menu("INQUERY");
        register.add("STUDENT");
        register.add("EXIT");
        inquery.add("ROLLNOWISE");
        inquery.add("NAMEWISE");

        mb.add(register);
        mb.add(inquery);

        setMenuBar(mb);

        register.addActionListener(this);
        inquery.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        String arg = ae.getActionCommand();
        if (ae.getSource() instanceof Menu) {
            if (arg.equals("EXIT")) {
                System.exit(0);
            }
        }
        if (ae.getSource() instanceof Menu) {
            if ("STUDENT".equals(arg)) {
                st = new student();
                st.show();
            }
        }
        if (ae.getSource() instanceof Menu) {
            if ("ROLLNOWISE".equals(arg)) {
                rw = new rollnowise();
                rw.show();
            }
        }
        if (ae.getSource() instanceof Menu) {
            if ("NAMEWISE".equals(arg)) {
                n = new namewise();
                n.show();
            }
        }
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        while (x > 0 && y > 0) {
            setSize(x, y);
            x = x - d;
            y = y - d;
            show();
        }
        System.out.println("Closing...");
        dispose();
        System.exit(0);

    }
}

//class for name wise report
class namewise extends Frame implements WindowListener, ActionListener {

    public static namewise nw;
    Label l1 = new Label("NAME", Label.LEFT);
    Label l2 = new Label("ROLLNO", Label.LEFT);
    Label l3 = new Label("COLG", Label.LEFT);
    Label l4 = new Label("SUB1", Label.LEFT);
    Label l5 = new Label("SUB2", Label.LEFT);
    Label l6 = new Label("SUB3", Label.LEFT);
    Label l7 = new Label("SUB4", Label.LEFT);
    Label l8 = new Label("SUB5", Label.LEFT);
    TextField tf_entername = new TextField(20);
    Button but_entername = new Button("FIND");
    Button ok = new Button("OK");
    Graphics g;
    String sqlstr;
    Statement st;
    GridLayout gl = new GridLayout(1, 2);
    GridLayout cl = new GridLayout(1, 5);

    Font font18 = new Font("VinetaBT", Font.BOLD | Font.ITALIC, 18);

    int x, y, d;
    Dialog dlg;
    Label msg;

    public namewise() {
        super("NAMEWISE");
        addWindowListener(this);
        setLayout(new GridLayout(12, 1));
        setBackground(Color.orange);
        setForeground(Color.black);
        addMenu();
        x = 550;

        y = 450;
        d = 100;
        setSize(x, y);
        show();
    }

    void addMenu() {
        Panel p4 = new Panel();
        Label l11 = new Label("ENTERNAME");

        p4.add(l11);
        p4.add(tf_entername);
        p4.add(but_entername);
        add(p4);

        but_entername.addActionListener(this);
        ok.addActionListener(this);

   //Dialog for confirmation
        dlg = new Dialog(this, "Inventory Management System", false);
        dlg.setLayout(new GridLayout(2, 1));
        dlg.setSize(100, 100);
        dlg.setLocation(200, 100);
        ok.setSize(50, 50);
        msg = new Label("NAME NOT FOUND");
        dlg.add(msg);
        dlg.add(ok);

    }

    public void actionPerformed(ActionEvent e) {
        Panel p1 = new Panel();
        l1.setFont(font18);
        l2.setFont(font18);
        p1.setLayout(gl);
        p1.add(l1);
        p1.add(l2);
        g = getGraphics();
        g.drawLine(40, 0, 40, 0);

        Panel p2 = new Panel();
        l3.setFont(font18);
        p2.add(l3);
        p2.setLayout(gl);

        Panel p3 = new Panel();
        l4.setFont(font18);
        l5.setFont(font18);
        l6.setFont(font18);
        l7.setFont(font18);
        l8.setFont(font18);

        p3.add(l4);
        p3.add(l5);
        p3.add(l6);
        p3.add(l7);
        p3.add(l8);
        p3.setLayout(cl);

        String arg = e.getActionCommand();
        if (e.getSource() instanceof Button) {
            if ("FIND".equals(arg)) {
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con = DriverManager.getConnection("jdbc:odbc:stu", "", "");
                    sqlstr = "select * from  stu1 where NAME='"
                            + tf_entername.getText() + "'";
                    st = con.createStatement();
                    ResultSet rs;
                    rs = st.executeQuery(sqlstr);

                    while (rs.next()) {
                        Panel a1 = new Panel();
                        l1 = new Label("", Label.LEFT);
                        l2 = new Label("", Label.LEFT);
                        l1.setFont(font18);
                        l2.setFont(font18);
                        a1.setLayout(gl);

                        Panel a2 = new Panel();
                        l3 = new Label("", Label.LEFT);
                        l3.setFont(font18);
                        a2.setLayout(gl);

                        Panel a3 = new Panel();
                        l4 = new Label("", Label.LEFT);
                        l5 = new Label("", Label.LEFT);
                        l6 = new Label("", Label.LEFT);
                        l7 = new Label("", Label.LEFT);
                        l8 = new Label("", Label.LEFT);
                        l4.setFont(font18);

                        l5.setFont(font18);

                        l6.setFont(font18);

                        l7.setFont(font18);

                        l8.setFont(font18);
                        a3.setLayout(cl);

                        l1.setText(rs.getString("NAME"));
                        l2.setText("" + rs.getInt("ROLLNO"));
                        l3.setText(rs.getString("COLG"));
                        l4.setText("" + rs.getInt("SUB1"));
                        l5.setText("" + rs.getInt("SUB2"));
                        l6.setText("" + rs.getInt("SUB3"));
                        l7.setText("" + rs.getInt("SUB4"));
                        l8.setText("" + rs.getInt("SUB5"));

                        a1.add(l1);
                        a1.add(l2);

                        a2.add(l3);

                        a3.add(l4);
                        a3.add(l5);
                        a3.add(l6);
                        a3.add(l7);
                        a3.add(l8);

                        add(p1);
                        add(a1);

                        add(p2);
                        add(a2);

                        add(p3);
                        add(a3);
                        show();
                    }
                } catch (ClassNotFoundException se) {
                    tf_entername.setText("Error : " + se.toString());

                } catch (SQLException se) {
                    tf_entername.setText("Error : " + se.toString());

                }
            }
        }
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        while (x > 0 && y > 0) {
            setSize(x, y);
            x = x - d;
            y = y - d;
            show();
        }
        dispose();

    }
}

//class for rollnowise report
class rollnowise extends Frame implements
        WindowListener, ActionListener {

    public static rollnowise rw;
    Label l1 = new Label("NAME", Label.LEFT);
    Label l2 = new Label("ROLLNO", Label.LEFT);
    Label l3 = new Label("COLG", Label.LEFT);
    Label l4 = new Label("SUB1", Label.LEFT);
    Label l5 = new Label("SUB2", Label.LEFT);
    Label l6 = new Label("SUB3", Label.LEFT);
    Label l7 = new Label("SUB4", Label.LEFT);
    Label l8 = new Label("SUB5", Label.LEFT);
    TextField tf_entername = new TextField(20);
    Button but_entername = new Button("FIND");
    String sqlstr;
    Statement st;
    GridLayout gl = new GridLayout(1, 2);
    GridLayout cl = new GridLayout(1, 5);

    Font font18 = new Font("VinetaBT", Font.BOLD | Font.ITALIC, 18);

    int x, y, d;

    public rollnowise() {
        super("ROLLNOWISE");
        addWindowListener(this);
        setLayout(new GridLayout(12, 1));
        setBackground(Color.orange);
        setForeground(Color.black);
        addMenu();
        x = 550;
        y = 450;
        d = 100;
        setSize(x, y);
        show();
    }

    void addMenu() {
        Panel p4 = new Panel();
        Label l11 = new Label("ENTERROLLNO");

        p4.add(l11);
        p4.add(tf_entername);
        p4.add(but_entername);
        add(p4);

        but_entername.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Panel p1 = new Panel();
        l1.setFont(font18);
        l2.setFont(font18);
        p1.setLayout(gl);

        p1.add(l1);
        p1.add(l2);
        l3.setFont(font18);
        Panel p2 = new Panel();
        p2.add(l3);
        p2.setLayout(gl);

        Panel p3 = new Panel();

        l4.setFont(font18);

        l5.setFont(font18);

        l6.setFont(font18);

        l7.setFont(font18);

        l8.setFont(font18);
        p3.add(l4);
        p3.add(l5);
        p3.add(l6);
        p3.add(l7);
        p3.add(l8);
        p3.setLayout(cl);

        /* Panel p4=new Panel();
         Label l11=new Label("ENTERROLLNO");

         p4.add(l11);
         p4.add(tf_entername);
         p4.add(but_entername);
         add(p4);
         add(p1);
         add(p2);
         add(p3);
         */
        String arg = e.getActionCommand();
        if (e.getSource() instanceof Button) {
            if ("FIND".equals(arg)) {
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con = DriverManager.getConnection("jdbc:odbc:stu", "", "");
                    sqlstr = "select * from  stu1 where ROLLNO="
                            + tf_entername.getText() + "";
                    st = con.createStatement();
                    ResultSet rs;
                    rs = st.executeQuery(sqlstr);

                    while (rs.next()) {
                        Panel a1 = new Panel();
                        l1 = new Label("", Label.LEFT);
                        l2 = new Label("", Label.LEFT);
                        l1.setFont(font18);
                        l2.setFont(font18);
                        a1.setLayout(gl);

                        Panel a2 = new Panel();
                        l3 = new Label("", Label.LEFT);
                        l3.setFont(font18);
                        a2.setLayout(gl);

                        Panel a3 = new Panel();
                        l4 = new Label("", Label.LEFT);
                        l5 = new Label("", Label.LEFT);
                        l6 = new Label("", Label.LEFT);
                        l7 = new Label("", Label.LEFT);
                        l8 = new Label("", Label.LEFT);
                        l4.setFont(font18);

                        l5.setFont(font18);

                        l6.setFont(font18);

                        l7.setFont(font18);

                        l8.setFont(font18);
                        a3.setLayout(cl);

                        l1.setText(rs.getString("NAME"));
                        l2.setText("" + rs.getInt("ROLLNO"));
                        l3.setText(rs.getString("COLG"));
                        l4.setText("" + rs.getInt("SUB1"));
                        l5.setText("" + rs.getInt("SUB2"));
                        l6.setText("" + rs.getInt("SUB3"));
                        l7.setText("" + rs.getInt("SUB4"));
                        l8.setText("" + rs.getInt("SUB5"));

                        a1.add(l1);
                        a1.add(l2);

                        a2.add(l3);

                        a3.add(l4);
                        a3.add(l5);
                        a3.add(l6);
                        a3.add(l7);
                        a3.add(l8);

                        add(p1);
                        add(a1);

                        add(p2);
                        add(a2);

                        add(p3);
                        add(a3);
                        show();
                    }
                } catch (ClassNotFoundException se) {

                    tf_entername.setText("Error : " + se.toString());
                } catch (SQLException se) {
                    tf_entername.setText("Error : " + se.toString());
                }
            }
        }
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        while (x > 0 && y > 0) {
            setSize(x, y);
            x = x - d;
            y = y - d;
            show();
        }
        dispose();

    }
}

//class which help in storing records in the database
class student extends Frame implements ActionListener, WindowListener {

    public static student st;
    TextField tf_name = new TextField(20);
    TextField tf_rollno = new TextField(20);
    TextField tf_colg = new TextField(20);
    TextField tf_marks = new TextField(20);
    TextField tf_sub1 = new TextField(4);
    TextField tf_sub2 = new TextField(4);
    TextField tf_sub3 = new TextField(4);
    TextField tf_sub4 = new TextField(4);
    TextField tf_sub5 = new TextField(4);

    Label l2 = new Label("ROLLNO");
    Label l1 = new Label("NAME");
    Label l3 = new Label("MARKS");
    Label l4 = new Label("COLG");
    Label l5 = new Label("MARK SHEET");
    Label l6 = new Label("SUB1");
    Label l7 = new Label("SUB2");
    Label l8 = new Label("SUB3");
    Label l9 = new Label("SUB4");
    Label l10 = new Label("SUB5");
    Button but_add = new Button("ADD");
    Button but_edit = new Button("EDIT");
    Button but_find = new Button("FIND");
    Button but_delete = new Button("DELETE");
    Button but_cancel = new Button("CANCEL");
    Button ok = new Button("OK");
    Dialog dlg;
    Label msg;
    int x, y, d;

    public student() {
        super("palce");
        addWindowListener(this);
        setLayout(new GridLayout(6, 1));
        setBackground(Color.yellow);
        setVisible(true);
        addmenu();
        x = 550;
        y = 450;
        d = 12;
        setSize(x, y);
        show();
    }

    void addmenu() {
//GridLayout gl=new GridLayout();
        Panel p1 = new Panel();
        p1.add(l1);
        p1.add(tf_name);

        p1.add(l2);
        p1.add(tf_rollno);

        Panel p2 = new Panel();
        p2.add(l5);
        Panel p3 = new Panel();
        p3.add(but_add);
        p3.add(but_find);
        p3.add(but_cancel);
        p3.add(but_edit);
        p3.add(but_delete);

        Panel p4 = new Panel();
//p4.add(l3);
        p4.add(l6);
        p4.add(l7);
        p4.add(l8);
        p4.add(l9);
        p4.add(l10);

        Panel p8 = new Panel();
        p8.add(tf_sub1);
        p8.add(tf_sub2);
        p8.add(tf_sub3);
        p8.add(tf_sub4);
        p8.add(tf_sub5);

        Panel p5 = new Panel();
        p5.add(l4);
        p5.add(tf_colg);

        add(p2);
        add(p1);
        add(p5);
        add(p4);
        add(p8);
        add(p3);
        but_add.addActionListener(this);
        but_cancel.addActionListener(this);
        but_find.addActionListener(this);
        but_delete.addActionListener(this);
        but_edit.addActionListener(this);
        ok.addActionListener(this);
//Dialog for confirmation

        dlg = new Dialog(this, "Inventory Management System", false);
        dlg.setLayout(new GridLayout(2, 1));
        dlg.setSize(100, 100);
        dlg.setLocation(200, 100);
        ok.setSize(50, 50);
        msg = new Label("Record Updated");
        dlg.add(msg);
        dlg.add(ok);

    }

    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();
//ADDBUTTON
        if (e.getSource() instanceof Button) {
            if ("ADD".equals(arg)) {
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con = DriverManager.getConnection("jdbc:odbc:stu", "", "");
                    Statement st;

                    String sqlStr;
                    sqlStr = "insert into stu1(NAME,ROLLNO,COLG,SUB1,SUB2,SUB3,SUB4,SUB5)values('" + tf_name.getText() + "'," + tf_rollno.getText() + ",'" + tf_colg.getText() + "'," + tf_sub1.getText() + "," + tf_sub2.getText() + "," + tf_sub3.getText() + "," + tf_sub4.getText() + "," + tf_sub5.getText() + ")";
                    st = con.createStatement();
                    st.executeUpdate(sqlStr);
                } catch (ClassNotFoundException se) {
                    // tf_name.setText("Error : " + se.toString());
                    msg.setText("ERROR");
                    dlg.show();
                } catch (SQLException se) {
                    //  tf_name.setText("Error : " + se.toString());
                    msg.setText("ENTER TEXTFIELD");
                    dlg.show();
                }
            }
        }

//OK button
        if (e.getSource() instanceof Button) {
            if ("OK".equals(arg)) {
                dlg.dispose();
            }
        }
        //CANCEL
        if (e.getSource() instanceof Button) {
            if ("CANCEL".equals(arg)) {
                tf_name.setText("");
                tf_rollno.setText("");
                tf_colg.setText("");
                tf_sub1.setText("");
                tf_sub2.setText("");
                tf_sub3.setText("");
                tf_sub4.setText("");
                tf_sub5.setText("");
            }
        }
        //FIND
        if (e.getSource() instanceof Button) {
            if ("FIND".equals(arg)) {
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con = DriverManager.getConnection("jdbc:odbc:stu", "", "");
                    Statement st;
                    String sqlstr;
                    sqlstr = "select * from stu1 where ROLLNO =" + tf_rollno.getText() + "";
                    st = con.createStatement();
                    ResultSet rs;
                    rs = st.executeQuery(sqlstr);
                    rs.next();
                    tf_name.setText("" + rs.getString("NAME"));
                    tf_colg.setText("" + rs.getString("COLG"));
                    tf_sub1.setText("" + rs.getInt("SUB1"));
                    tf_sub2.setText("" + rs.getInt("SUB2"));
                    tf_sub3.setText("" + rs.getInt("SUB3"));
                    tf_sub4.setText("" + rs.getInt("SUB4"));
                    tf_sub5.setText("" + rs.getInt("SUB5"));
                } catch (ClassNotFoundException se) {
                    msg.setText("RECORD NOT FOUND");
                    dlg.show();

//  tf_name.setText("Error : " + se.toString());
                } catch (SQLException se) {
                    msg.setText("RECORD NOT FOUND");
                    dlg.show();
                    //tf_name.setText("Error : " + se.toString());
                }
            }
        }
        //DELETE
        if (e.getSource() instanceof Button) {
            if ("DELETE".equals(arg)) {
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con = DriverManager.getConnection("jdbc:odbc:stu", "", "");
                    Statement st;
                    String sqlstr;
                    sqlstr = "delete * from stu1 where ROLLNO=" + tf_rollno.getText() + "";
                    st = con.createStatement();
                    st.executeUpdate(sqlstr);
                    tf_name.setText("");
                    tf_colg.setText("");
                    tf_sub1.setText("");
                    tf_sub2.setText("");
                    tf_sub3.setText("");
                    tf_sub4.setText("");
                    tf_sub5.setText("");

                    tf_rollno.setText("");
                    msg.setText("RECORD DELETED");
                    dlg.show();

                } catch (ClassNotFoundException se) {
                    tf_name.setText("Error : " + se.toString());
                } catch (SQLException se) {
                    tf_name.setText("Error : " + se.toString());
                }
            }
        }

//EDIT
        if (e.getSource() instanceof Button) {
            if ("EDIT".equals(arg)) {
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con = DriverManager.getConnection("jdbc:odbc:stu", "", "");
                    Statement st;
                    String sqlstr;
                    sqlstr = "update stu1 set NAME='" + tf_name.getText() + "',SUB1=" + tf_sub1.getText() + ",SUB2=" + tf_sub2.getText() + ",SUB3=" + tf_sub3.getText() + ",SUB4=" + tf_sub4.getText() + ",SUB5=" + tf_sub5.getText() + ",COLG='" + tf_colg.getText() + "' where ROLLNO=" + tf_rollno.getText();
                    st = con.createStatement();
                    st.executeUpdate(sqlstr);
                    msg.setText("RECORD UPDATED");
                    dlg.show();
                } catch (ClassNotFoundException se) {

                    tf_name.setText("Error : " + se.toString());
                } catch (SQLException se) {

                    tf_name.setText("Error : " + se.toString());
                }
            }
        }
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        while (x > 0 && y > 0) {
            setSize(x, y);
            x = x - d;
            y = y - d;
            show();
        }
        dispose();
    }

}

