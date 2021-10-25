package librarysystem;

import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class EditMemberPanel implements MessageableWindow {
        public JPanel getMainPanel() {
            return mainPanel;
        }
        private JPanel mainPanel;


        public EditMemberPanel() {
            mainPanel = new JPanel();
            JPanel frame = new JPanel();
            String[] columnTitles = { "MemberId","First Name", "Last Name", "Street", "city", "state","zip","phonenumber" };
            SystemController systemController = new SystemController();
            Object[][] dataEntries = systemController.getMemberData();

            EditableTableModel model = new EditableTableModel(columnTitles, dataEntries);
            JTable table = new JTable(model);
            table.createDefaultColumnsFromModel();
            frame.add(new JScrollPane(table));
            frame.setSize(270, 200);
            frame.setVisible(true);
            JButton button = new JButton("Save Changes");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    systemController.saveUpdatedMembers(model.dataEntries);
                }
            });
            mainPanel.add(frame);
            mainPanel.add(button);
        }



        @Override
        public void updateData() {

        }
    }
