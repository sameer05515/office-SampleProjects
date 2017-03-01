//package com.p.html.editor;
//// HTMLEditor.java
//// An extension of StyledEditor that adds HTML-related features.
////
//import javax.swing.*;
//import javax.swing.text.*;
//import javax.swing.text.html.*;
//import java.awt.event.*;
//import java.awt.*;
//import java.io.*;
//
//public class HTMLEditor extends StyledEditor {
//
//  private final static String COPY_HTML =
//    "<p>&copy; 2003, O'Reilly &amp; Associates</p>";
//
//  public static void main(String[] args) {
//    HTMLEditor editor = new HTMLEditor();
//    editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    editor.setVisible(true);
//  }
//
//  // Override to create a JEditorPane with the HTMLEditorKit in place
//  protected JTextComponent createTextComponent() {
//    JEditorPane jep = new JEditorPane();
//    jep.setEditorKit(new HTMLEditorKit());
//    return jep;
//  }
//
//  // Add icons & friendly names for font actions
//  protected void makeActionsPretty() {
//    super.makeActionsPretty();
//
//    Action a;
//    // HTML Actions
//    a = getTextComponent().getActionMap().get("InsertHR");
//    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/hr.gif"));
//    a.putValue(Action.NAME, "HR");
//
//    // Custom HTML Actions, need to store these in the map...
//    // First, an easy use of the InsertHTMLTextAction class
//    a = new HTMLEditorKit.InsertHTMLTextAction("InsertCopyright", COPY_HTML,
//					       HTML.Tag.BODY, HTML.Tag.P);
//    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/copyright.gif"));
//    a.putValue(Action.NAME, "Teletype Text");
//    getTextComponent().getActionMap().put("add-copyright", a);
//
//    // And some of our own Tags that require a little more work.
//    // See the TagAction inner class below.
//    a = new TagAction(HTML.Tag.A, "URL", HTML.Attribute.HREF);
//    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/link.gif"));
//    a.putValue(Action.NAME, "Anchor Link");
//    getTextComponent().getActionMap().put("anchor-link", a);
//
//    a = new TagAction(HTML.Tag.A, "Name", HTML.Attribute.NAME);
//    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/anchor.gif"));
//    a.putValue(Action.NAME, "Anchor Name");
//    getTextComponent().getActionMap().put("anchor-name", a);
//
//    a = new ImageAction();
//    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/picture.gif"));
//    a.putValue(Action.NAME, "Image");
//    getTextComponent().getActionMap().put("InsertIMG", a);
//  }
//
//  // Add HTML actions to the toolbar
//  protected JToolBar createToolBar() {
//    JToolBar bar = super.createToolBar();
//    bar.addSeparator();
//
//    bar.add(getTextComponent().getActionMap().get("add-copyright")).setText("");
//    bar.add(getTextComponent().getActionMap().get("InsertHR")).setText("");
//    bar.add(getTextComponent().getActionMap().get("InsertIMG")).setText("");
//    bar.add(getTextComponent().getActionMap().get("anchor-link")).setText("");
//    bar.add(getTextComponent().getActionMap().get("anchor-name")).setText("");
//    return bar;
//  }
//
//  // Add a preview action to the menu
//  protected JMenuBar createMenuBar() {
//    JMenuBar menubar = super.createMenuBar();
//
//    JMenu view = new JMenu("View");
//    JMenuItem preview = new JMenuItem("Preview");
//    view.add(preview);
//    preview.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {
//	JEditorPane editor = (JEditorPane)getTextComponent();
//	JEditorPane display = new JEditorPane();
//	display.setEditable(false);
//        display.setEditorKit(new HTMLEditorKit());
//        JScrollPane sp = new JScrollPane(display);
//        sp.setPreferredSize(new Dimension(500,500));
//	try {
//	  //display.setPage(currentFile.toURL());
//          display.setDocument(editor.getDocument());
//	}
//	catch (Exception e) {
//	  display.setText("Failed to preview file.\n" +
//			  "Make sure file has been saved.");
//	}
//	JOptionPane.showMessageDialog(null, sp, "Preview",
//				      JOptionPane.PLAIN_MESSAGE);
//      }
//    });
//    menubar.add(view);
//
//    return menubar;
//  }
//
//  public class TagAction extends StyledEditorKit.StyledTextAction {
//    private HTML.Tag tag;
//    private HTML.Attribute tagAttr;
//    private String tagName;
//
//    public TagAction(HTML.Tag t, String s, HTML.Attribute a) {
//      super(s);
//      tag = t;
//      tagName = s;
//      tagAttr = a;
//    }
//
//    public void actionPerformed(ActionEvent e) {
//      JEditorPane editor = getEditor(e);
//      if (editor != null) {
//	String value = JOptionPane.showInputDialog(HTMLEditor.this, 
//						   "Enter " + tagName +":");
//	StyledEditorKit kit = getStyledEditorKit(editor);
//	MutableAttributeSet attr = kit.getInputAttributes();
//	boolean anchor = attr.isDefined(tag);
//	if (anchor) {
//	  attr.removeAttribute(tag);
//	}
//	else {
//	  SimpleAttributeSet as = new SimpleAttributeSet();
//	  as.addAttribute(tagAttr, value);
//	  attr.addAttribute(tag, as);
//	}
//	setCharacterAttributes(editor, attr, false);
//      }
//    }
//  }
//
//  public class ImageAction extends StyledEditorKit.StyledTextAction {
//    public ImageAction() {
//      super("InsertIMG");
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//      JEditorPane editor = getEditor(ae);
//      if (editor == null) {
//        // no editor in focus...just ignore this for now
//        return;
//      }
//
//      HTMLEditorKit kit = (HTMLEditorKit)editor.getEditorKit();
//      HTMLDocument doc = (HTMLDocument)editor.getDocument();
//      String value = JOptionPane.showInputDialog(HTMLEditor.this, 
//						"Image file:");
//      try {
//	kit.insertHTML(doc, editor.getCaretPosition(),
//		       "<img src=\"" + value + "\">", 0, 0,
//		       HTML.Tag.IMG);
//      }
//      catch (Exception e) {
//        JOptionPane.showMessageDialog(HTMLEditor.this,
//        "Image Not Loaded", "ERROR", JOptionPane.ERROR_MESSAGE);
//	e.printStackTrace();
//      }
//    }
//  }
//}