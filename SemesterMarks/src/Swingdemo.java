


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.*;


 

 class IVSem {
	 IVSem(){
       final JFrame jfrm =new JFrame("Student IV Sem Marks");
        jfrm.setLayout(new BorderLayout());
        jfrm.setSize(500,600);
        jfrm.setVisible(true);
        jfrm.setDefaultCloseOperation(jfrm.EXIT_ON_CLOSE);
        
        
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JButton jb =new JButton("SAVE");
        jfrm.add(jp1);
        jfrm.add(jp2);
        
        
        jp1.setLayout(new GridLayout(11,2));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jfrm.add(jp1,BorderLayout.CENTER);
        jfrm.add(jp2,BorderLayout.PAGE_END);
        jp2.add(jb);
        
        JLabel lb1 = new JLabel("Department");
        JLabel lb2 = new JLabel("Student Name");
        JLabel lb3 = new JLabel("Roll No.");
        JLabel lb4 = new JLabel("Maths");
        JLabel lb5 = new JLabel("OOP");
        JLabel lb6 = new JLabel("OS");
        JLabel lb7 = new JLabel("TFCS");
        JLabel lb8 = new JLabel("TC");
        JLabel lb9 = new JLabel("CW prac");
        JLabel lb10 = new JLabel("OOP prac");
        JLabel lb11 = new JLabel("OS prac");
        
        
        
        //JLabel lb9 = new JLabel("Total Marks");
   
                
        final JComboBox jcom;
        String s[]={"CS","IT","EC","CIVIL","MECH","EN","EP","IND","OTHER"};
        jcom = new JComboBox(s);
        jcom.setSize(10,2);
         
        final JTextField t1 = new JTextField(50);
        t1.setSize(10, 2);
        final JTextField t2 = new JTextField(70);
        t2.setSize(7, 2);
        final JTextField t3 = new JTextField(20);
        t3.setSize(5, 2);
        final JTextField t4 = new JTextField(15);
        t4.setSize(5, 2);
        final JTextField t5 = new JTextField(15);
        t5.setSize(5, 2);
        final JTextField t6 = new JTextField(20);
        t6.setSize(5, 2);
        final JTextField t7 = new JTextField(20);
        t7.setSize(5, 2);
        final JTextField t8 = new JTextField(20);
        t8.setSize(5, 2);
        final JTextField t9 = new JTextField(20);
        t9.setSize(5, 2);
        final JTextField t10 = new JTextField(20);
        t10.setSize(5, 2);
        
        jp1.add(lb1);
        jp1.add(jcom);
        
        jp1.add(lb2);
        jp1.add(t1);
        
        jp1.add(lb3);
        jp1.add(t2);
        
        
        jp1.add(lb4);
        jp1.add(t3);
        
        jp1.add(lb5);
        jp1.add(t4);
        
        jp1.add(lb6);
        jp1.add(t5);
        
        jp1.add(lb7);
        jp1.add(t6);
        
        jp1.add(lb8);
        jp1.add(t7);
        
        jp1.add(lb9);
        jp1.add(t8);
        
        jp1.add(lb10);
        jp1.add(t9);
        
        jp1.add(lb11);
        jp1.add(t10);
        
   
   jb.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        
                    double maths = 26+ Double.parseDouble(t3.getText());
                    double oop   = 27+ Double.parseDouble(t4.getText());
                    double os    = 25+ Double.parseDouble(t5.getText());
                    double tfcs  = 22+ Double.parseDouble(t6.getText());
                    double tc    = 25+ Double.parseDouble(t7.getText());
                    double cwp   = 21+ Double.parseDouble(t8.getText());
                    double oopp  = 21+ Double.parseDouble(t9.getText());
                    double osp   = 21+ Double.parseDouble(t10.getText());
                    double totavg = (oop + os + maths + tfcs + tc)/5;
                    double practotavg = (oopp + osp + cwp)/3;
                    
        JOptionPane.showMessageDialog(jfrm, "Department: "+jcom.getSelectedItem()+"\nStudent Name: "+t1.getText()+"\nRoll No.: "+t2.getText()+ "\nExpected Total Marks in the End Semester: "+"\nMATHS: "+maths+"/100"+"\nOOP: "+oop+"/100"+ "\nOS: "+os+"/100"+"\nTFCS: "+tfcs+"/100"+"\nTC: "+tc+"/100"+"\nTotal Average Marks: "+totavg+"\nInternal Practical Average Marks : "+practotavg+ "\n\nSo Study! ".toUpperCase());
    }
   
});
    }
 }
   

 public class Swingdemo {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable(){
           public void run(){
               new IVSem();
           }
       });
   } 
}

