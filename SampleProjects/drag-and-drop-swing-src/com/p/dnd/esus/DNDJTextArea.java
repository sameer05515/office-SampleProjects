package com.p.dnd.esus;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
 
import java.io.*;
import java.io.IOException;
  
import javax.swing.JTextArea;
  
public class DNDJTextArea extends JTextArea implements DropTargetListener
{
   DropTarget dropTarget = null;
   
   public DNDJTextArea() {
      // create a drop target
      dropTarget = new DropTarget(this, this);
   }
  
   public void dragEnter(DropTargetDragEvent event) {
      event.acceptDrag(DnDConstants.ACTION_MOVE);
   }
  
   public void drop (DropTargetDropEvent event) {
      try {
         // get the object that is being transferred
         Transferable transferable = event.getTransferable();
        
         // DNDJTextArea accepts only Strings
         if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            event.acceptDrop(DnDConstants.ACTION_MOVE);
  
            String filename = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            setText(readFile(filename));
 
            event.getDropTargetContext().dropComplete(true);
         }
         else {
            event.rejectDrop();
         }
      }
      catch (UnsupportedFlavorException e) {
         setText(""+e);
         event.rejectDrop();
      }
      catch (Exception e) {
         setText(""+e);
         event.rejectDrop();
      }
   }
  
   public void dragExit (DropTargetEvent event) { }
   public void dragOver (DropTargetDragEvent event) { }
   public void dropActionChanged (DropTargetDragEvent event) { }
  
   public String readFile(String filename) throws Exception {
      String LINEEND = System.getProperties().getProperty("line.separator");     
      StringBuffer sb = new StringBuffer();
      BufferedReader br = new BufferedReader(new FileReader(filename));
  
      String line;
      while ((line = br.readLine()) != null) {
         sb.append(line + LINEEND);
      }        
  
      return sb.toString();
   }
}