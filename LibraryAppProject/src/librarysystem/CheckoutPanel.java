package librarysystem;

import business.CheckoutEntries;
import business.SystemController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutPanel implements MessageableWindow {
    
	public JPanel getMainPanel() {
        return mainPanel;
    }
    
    private final String[] DEFAULT_COLUMN_HEADERS 
	   = {"Book Title", "Checkout Date","Due Date"};
    
    private JTable table;
	private JScrollPane scrollPane;
	private CustomTableModel model;
    
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;
    private JPanel tablePanel;
    private JPanel tableLabel;
    private JPanel tableViewPanel;
    
    private static final int SCREEN_WIDTH = 500;
	private static final int SCREEN_HEIGHT = 200;
    private static final int TABLE_WIDTH = (int) (0.75 * SCREEN_WIDTH);
    private static final int DEFAULT_TABLE_HEIGHT = (int) (0.75 * SCREEN_HEIGHT);
    private final float [] COL_WIDTH_PROPORTIONS = {0.40f, 0.35f, 0.35f};


    private JTextField memberIdField;
    private JTextField isbnField;
    
    private JFrame  frame;


    public void clearData() {
        memberIdField.setText("");
        isbnField.setText("");

    }
    
    
    
    public CheckoutPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineOuterMiddle();
        defineTableLabel();
        defineTablePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
        //mainPanel.add(tableLabel);
        //mainPanel.add(tableLabel, BorderLayout.SOUTH);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel CheckoutPanel = new JLabel("Checkout Book");
        Util.adjustLabelFont(CheckoutPanel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(CheckoutPanel);
    }
    
    public void defineTableLabel() {
        tableLabel = new JPanel();
        JLabel CheckoutEntryLabel = new JLabel("Checkout History for Member");
        Util.adjustLabelFont(CheckoutEntryLabel, Util.DARK_BLUE, true);
        tableLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tableLabel.add(CheckoutEntryLabel);
    }
    
    
    public void defineTablePanel()
    {
    	
    	tablePanel = new JPanel();
    	createTableAndTablePane();
    	tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	tablePanel.add(tableViewPanel);
    	
    	
    }

    public void defineOuterMiddle() {
        outerMiddle = new JPanel();
        outerMiddle.setLayout(new BorderLayout());

        //set up left and right panels
        JPanel middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 20, 20);
        middlePanel.setLayout(fl);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel memberIdLabel = new JLabel("Member ID");
        JLabel isbnLabel = new JLabel("ISBN");


        memberIdField = new JTextField(10);
        isbnField = new JTextField(10);


        leftPanel.add(memberIdLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(isbnLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));


        rightPanel.add(memberIdField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(isbnField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));



        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addBookButton = new JButton("Checkout Book");
        attachAddBookButtonListener(addBookButton);
        JPanel addBookButtonPanel = new JPanel();
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addBookButtonPanel.add(addBookButton);
        outerMiddle.add(addBookButtonPanel, BorderLayout.SOUTH);

    }
    
    private void createTableAndTablePane() {
		updateModel(); 
		tableViewPanel = new JPanel();
		tableViewPanel.setBorder(BorderFactory.createTitledBorder(
	      BorderFactory.createEtchedBorder(), "Member Checkout Entry", TitledBorder.LEFT,
	      TitledBorder.TOP));
		table = new JTable(model);
		createCustomColumns(table, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		scrollPane.getViewport().add(table);
		tableViewPanel.add(scrollPane);
	}
    
    private void createCustomColumns(JTable table, int width, float[] proportions,String[] headers) {
  		table.setAutoCreateColumnsFromModel(false);
          int num = headers.length;
          for(int i = 0; i < num; ++i) {
              TableColumn column = new TableColumn(i);
              column.setHeaderValue(headers[i]);
              column.setMinWidth(Math.round(proportions[i]*width));
              table.addColumn(column);
          }
  	}
    
    private void setValues(CustomTableModel model) {
    	
    	SystemController sc = new SystemController();
		List<CheckoutEntries> checkOList = sc.getMemberCheckoutEntry(memberIdField.getText());
		List<String[]> data = new ArrayList<>(checkOList.size());
		StringBuilder strbul=new StringBuilder();
		
		for (CheckoutEntries ce : checkOList) {
			
			System.out.println(ce);
			
			String[]row = {ce.getBookCopy().getBook().getTitle(), ce.getCheckoutDate().toString(), ce.getDueDate().toString()};
			data.add(row);
			
		
		}
			

		model.setTableValues(data);
		
	}
    
    public void updateModel(List<String[]> list){
		if(model == null) {
			model = new CustomTableModel();
		}
		model.setTableValues(list);
	}
	
	private void updateModel() {
		List<String[]> theData = new ArrayList<String[]>();
		updateModel(theData);
	}
  	
    private void attachAddBookButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            SystemController sc = new SystemController();
            String memberId = memberIdField.getText();
            String isbn = isbnField.getText();

            boolean recordStatus = sc.checkoutBook(memberId , isbn);
            if(recordStatus) {
                int input = JOptionPane.showOptionDialog(frame, "Checkout Successfully!","Checkout",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                if(input == JOptionPane.OK_OPTION)
                {
                	setValues(model);
        			table.updateUI();	
                }
                

            } else {
                JOptionPane.showMessageDialog(butn, "Wrong Member ID or Book is not Available");
            }

        });
    }
    public void updateData() {
        // nothing to do

    }
}
