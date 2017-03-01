package com.p.dnd.esus;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.io.*;
  
import javax.swing.*;
  
public class DNDJList extends JList implements DragSourceListener, DragGestureListener   
{
   DragSource dragSource = null;
  
   public DNDJList(ListModel lm) {
      super(lm);
  
      // create a dragsource
      dragSource = new DragSource();
  
      // create a drag gesture recognizer
      dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
   }   
  
   public void dragGestureRecognized( DragGestureEvent event) { 
      String fileSelected = (String) getSelectedValue();
  
      if (fileSelected != null) {
         if (fileSelected.endsWith(".txt")) {
            // StringSelection implements Transferable, wraps the data into a transferable object
            StringSelection text = new StringSelection(fileSelected.toString());
         
            // start the dragging
            dragSource.startDrag(event, DragSource.DefaultMoveDrop, text, this);
         }
         else {
            SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                  JOptionPane.showMessageDialog(SwingUtilities.getRootPane(DNDJList.this),
                                                "Only .txt files can be dragged!", "Error",
                                                JOptionPane.ERROR_MESSAGE);
               }
            });
         }
      } else {
         System.out.println( "nothing was selected");  
      }
   }
  
   public void dragDropEnd (DragSourceDropEvent event) { }
   public void dragEnter (DragSourceDragEvent event) { }
   public void dragExit (DragSourceEvent event) { }
   public void dragOver (DragSourceDragEvent event) { }
   public void dropActionChanged ( DragSourceDragEvent event) { }
}