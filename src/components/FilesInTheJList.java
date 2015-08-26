/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author ASUS
 */
import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class FilesInTheJList {

    private static final int COLUMNS = 4;
    private Dimension size;

    public FilesInTheJList() {
        final JList list = new JList(new File("D:\\").listFiles()) {

            private static final long serialVersionUID = 1L;

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                if (size != null) {
                    return new Dimension(size);
                }
                return super.getPreferredScrollableViewportSize();
            }
        };
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(150);
        size = list.getPreferredScrollableViewportSize();
        size.width *= COLUMNS;
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new MyCellRenderer());
        list.setVisibleRowCount(0);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        JFrame f = new JFrame("Files In the JList");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(list));
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                FilesInTheJList fITJL = new FilesInTheJList();
            }
        });
    }

    private static class MyCellRenderer extends JLabel implements ListCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof File) {
                File file = (File) value;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setPreferredSize(new Dimension(250, 25));
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
            }
            return this;
        }
    }
}