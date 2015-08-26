/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;



public class FileTree extends JPanel {
  /** Construct a FileTree */
  public FileTree(File dir) {
    setLayout(new BorderLayout());



 //menu bar working





    // Make a tree list with all the nodes, and make it a JTree
    JTree tree = new JTree(addNodes(null, dir));

    JTree tree1 = new JTree(addNodes(null, dir));
    // Add a listener
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
            .getPath().getLastPathComponent();
        System.out.println("You selected " + node);
      }
    });

    // Lastly, put the JTree into a JScrollPane.
   JScrollPane scrollpane = new JScrollPane();
//JScrollPane scrollpane1 = new JScrollPane();
    scrollpane.getViewport().add(tree);
    JScrollPane scrollPane1 =new JScrollPane();
    scrollPane1.getViewport().add(tree1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(scrollpane))
                .addContainerGap(21, Short.MAX_VALUE))
        );
  //scrollpane1.getViewport().add(tree1);
    //add(BorderLayout.EAST, scrollpane);
  JPanel panel = new JPanel( new BorderLayout() );
  //add( scrollpane, BorderLayout.WEST );
  //add( scrollPane1, BorderLayout.EAST );
 // add( scrollpane1, BorderLayout.EAST );


  }

 
  DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
    String curPath = dir.toString();
    DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
    if (curTop != null) { // should only be null at root
      curTop.add(curDir);
    }
    Vector ol = new Vector();
    String[] tmp = dir.list();
    for (int i = 0; i < tmp.length; i++)
      ol.addElement(tmp[i]);
    Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
    File f;
    Vector files = new Vector();
    // Make two passes, one for Dirs and one for Files. This is #1.
    for (int i = 0; i < ol.size(); i++) {
      String thisObject = (String) ol.elementAt(i);
      String newPath;
      if (curPath.equals("c:\\"))
        newPath = thisObject;
      else
        newPath = curPath + File.separator + thisObject;
      if ((f = new File(newPath)).isDirectory())
        addNodes(curDir, f);
      else
        files.addElement(thisObject);
    }
    // Pass two: for files.
    for (int fnum = 0; fnum < files.size(); fnum++)
      curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
    return curDir;
  }

 /* public Dimension getMinimumSize() {
    return new Dimension(200, 400);
  }

  public Dimension getPreferredSize() {
    return new Dimension(200, 400);
  }*/

  /** Main: make a Frame, add a FileTree */
  public static void main(String[] av) {

    JFrame frame = new JFrame("FileTree");

    frame.setForeground(Color.black);
    frame.setBackground(Color.lightGray);





    Container cp = frame.getContentPane();

    if (av.length == 0) {
      cp.add(new FileTree(new File(".")));
    } else {
      cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
      for (int i = 0; i < av.length; i++)
        cp.add(new FileTree(new File(av[i])));
    }


    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
