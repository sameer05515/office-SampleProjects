package com.p.dnd.esus;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
  
public class Main extends JFrame
{
   private DNDJList list;
   private DNDJTextArea textarea;
  
   public static void main(String []args) {
      Main frame = new Main();
      frame.show();
   }
  
   public Main() {
      getContentPane().setLayout(new BorderLayout());
      list = new DNDJList(new DefaultListModel());
      getContentPane().add(BorderLayout.EAST, new JScrollPane(list));
      textarea = new DNDJTextArea();
      getContentPane().add(BorderLayout.CENTER, new JScrollPane(textarea));
  
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
  
      fillUpList("C:/Users/796412/Desktop/test-projects");
  
      setSize(700, 300);
   }
 
   /**
    *  Fills up the JList with the entries in the specified directory
    */
   private void fillUpList(String directory) {
      File dir = new File(directory);
      File []files = dir.listFiles();
  
      DefaultListModel dlm = (DefaultListModel) list.getModel();
      for (int i=0; i<files.length; i++) {
         dlm.addElement(directory + files[i].getName());
      }
   }
}