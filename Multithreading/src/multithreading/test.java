package multithreading;

// Multithreading (odd or even)

import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

class test extends Frame implements ActionListener,Runnable
{
 int lower,upper;
 Label l1=new Label("ODD");
 Label l2=new Label("EVEN");
 List lb1=new List();
 List lb2=new List();
 Button b2=new Button("EXIT");
 test(int low,int up)
 {
  lower = low;
  upper = up;
  setLayout(new FlowLayout());
  setSize(700,700);
  setTitle("Thread Demo");
  setVisible(true);
  add(l1);add(lb1);add(l2);add(lb2);add(b2);
  b2.addActionListener(this);
  Thread t=new Thread(this);
  t.start();


 addWindowListener(
              new WindowAdapter()
               {
                 public void windowClosing(WindowEvent e)
                   { System.exit(0); }
               }
             );

 }
 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==b2)
    System.exit(0);
 }
 public void run()
 {
  try
  {
   if((lower % 2) != 0)
   {
   lower = lower + 1;
   }
   while(lower <= upper)
   {
    Thread.sleep(1000);
    lb2.add(String.valueOf(lower));
    lower += 2;
    Thread.sleep(500);
   }
  }catch(Exception e){}
 }

 public static void main(String args[])
 {
     
     //////Change is here
  int lower = 0,upper =0 ;
  //lower = Integer.parseInt(args[0]);
  // upper = Integer.parseInt(args[1]);
  test ob = new test(lower,upper);

  if((lower % 2) == 0)
  {
    lower = lower + 1;
  }

   try
    {
      while(lower <= upper)
      {
        Thread.sleep(1000);
        ob.lb1.add(String.valueOf(lower));
        lower = lower + 2;
        Thread.sleep(500);
      }
    }
    catch(Exception e){}
 }

}