package librarysystem;

import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class AddCopyPanel implements MessageableWindow {
    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField isbnField;


    public void clearData() {
        isbnField.setText("");

    }
    public AddCopyPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineOuterMiddle();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddCopyPanel = new JLabel("Add Copy Of Existing Book");
        Util.adjustLabelFont(AddCopyPanel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(AddCopyPanel);
    }

    public void defineOuterMiddle() {
        outerMiddle = new JPanel();
        outerMiddle.setLayout(new BorderLayout());

        //set up left and right panels
        JPanel middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        middlePanel.setLayout(fl);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


        JLabel isbnLabel = new JLabel("ISBN");
        isbnField = new JTextField(10);

        leftPanel.add(isbnLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));

        rightPanel.add(isbnField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));

        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addCopyBookButton = new JButton("Add Copy");
        attachAddBookButtonListener(addCopyBookButton);
        JPanel addCopyBookButtonPanel = new JPanel();
        addCopyBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addCopyBookButtonPanel.add(addCopyBookButton);
        outerMiddle.add(addCopyBookButtonPanel, BorderLayout.CENTER);

    }
    private void attachAddBookButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            SystemController sc = new SystemController();

            String isbn = isbnField.getText();
            boolean copyAdded = sc.addCopyOfExistingBook(isbn);

            if(copyAdded) {
                JOptionPane.showMessageDialog(butn, "A copy of ISBN " + isbn + " is Added");
            } else {
                JOptionPane.showMessageDialog(butn, "A copy of Book is not Added Check " +
                        "ISBN Number");
            }

        });
    }
    public void updateData() {
        // nothing to do

    }
}


