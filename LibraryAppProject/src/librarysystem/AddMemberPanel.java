package librarysystem;

import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMemberPanel implements MessageableWindow {
//	AddMemberWindow1 bookClub;
//	public void setBookClub(AddMemberWindow1 club) {
//		bookClub = club;
//	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel outerMiddle;
	private JPanel lowerHalf;
	
	
	private JTextField memberFirstNameField;
	private JTextField memberLastNameField;
	private JTextField memberIdField;
	private JTextField memberPhoneField;
	private JTextField cityField;
	private JTextField streetField;
	private JTextField stateField;
	private JTextField zipField;

	public void clearData() {
		memberFirstNameField.setText("");
		memberLastNameField.setText("");
		memberIdField.setText("");
		memberPhoneField.setText("");
		cityField.setText("");
		stateField.setText("");
		stateField.setText("");
		zipField.setText("");

	}
	public AddMemberPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		defineLowerHalf();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(outerMiddle, BorderLayout.CENTER);
		mainPanel.add(lowerHalf, BorderLayout.SOUTH);

	}
	//Testing BACK BUTTON
	private void defineLowerHalf() {

		lowerHalf = new JPanel();
		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton backButton = new JButton("<= Back to Main");
		//addBackButtonListener(backButton);
		lowerHalf.add(backButton);

	}
	
	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel AddBookLabel = new JLabel("Add New Member");
		Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(AddBookLabel);
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
		
		JLabel memberFirstNameLabel = new JLabel("First Name");
		JLabel memberLastNameLabel = new JLabel("Last Name");
		JLabel memberIdLabel = new JLabel("Member ID");
		JLabel memberPhoneLabel = new JLabel("Phone Number");
		JLabel cityLabel = new JLabel("City");
		JLabel streetLabel = new JLabel("Street");
		JLabel stateLabel = new JLabel("State");
		JLabel zipLabel = new JLabel("Zip");
		
		memberFirstNameField = new JTextField(10);
		memberLastNameField = new JTextField(10);
		memberIdField = new JTextField(10);
		memberPhoneField = new JTextField(10);
		streetField = new JTextField(10);
		cityField = new JTextField(10);
		stateField = new JTextField(10);
		zipField = new JTextField(10);
		
		leftPanel.add(memberFirstNameLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(memberLastNameLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(memberIdLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(memberPhoneLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(streetLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(cityLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(stateLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
		leftPanel.add(zipLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,12)));

		//member id, first name, last name, street, city, state, zip, telephone number
		rightPanel.add(memberFirstNameField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(memberLastNameField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(memberIdField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(memberPhoneField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(streetField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(cityField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(stateField);
		rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
		rightPanel.add(zipField);


		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		outerMiddle.add(middlePanel, BorderLayout.NORTH);
		
		//add button at bottom
		JButton addBookButton = new JButton("Add New Member");
		attachAddBookButtonListener(addBookButton);
		JPanel addBookButtonPanel = new JPanel();
		addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addBookButtonPanel.add(addBookButton);
		outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);
		
	}
	private void attachAddBookButtonListener(JButton butn) {
		SystemController sc = new SystemController();
		butn.addActionListener(evt -> {
			String fname = memberFirstNameField.getText();
			String lname = memberLastNameField.getText();
			String memberId = memberIdField.getText();

			String street = streetField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zip = zipField.getText();
			String tel = memberPhoneField.getText();

			if(memberId.isEmpty() || fname.isEmpty() || lname.isEmpty() || tel.isEmpty() || street.isEmpty() ||
					city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
				JOptionPane.showMessageDialog(butn,"Please Fill all Fields");

			} else {
				boolean status = sc.addLibraryMember(memberId, fname, lname, tel,street, city,
						state,  zip);
				if(status) {
					displayInfo("Member " + fname + " with member ID " + memberId + " has been added " +
							"to the member lists!");
				} else {
					displayInfo("Operation is not successful try again");
				}
			}


		
	    });
	}
	public void updateData() {
		// nothing to do
		
	}
}
