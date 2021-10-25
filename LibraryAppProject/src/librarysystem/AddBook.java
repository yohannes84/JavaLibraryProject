package librarysystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.Author;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddBook extends JFrame {
	

	
	
	private ArrayList<Author> authors = new ArrayList<>();
	private ArrayList<Address> address = new ArrayList<>();

	private JPanel contentPane;
	private JTextField txtxIsbn;
	private JTextField txtBtitle;
	private JTextField txtLength;
	private JTextField texStreet;
	private JTextField txtCity;
	private JTextField txtZip;
	private JTextField txtFName;
	private JTextField txtLName;
	private JTextField textTelephone;
	private JTextArea txtBio;
	private JComboBox cmbState;
	private JButton btnAddAuthor;
	private JButton btnAddBook;
	
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1; 
	
	private String authorState;
	
	
	AddMemberWindow1 bookClub;
	public void setBookClub(AddMemberWindow1 club) {
		bookClub = club;
	}
	
	//private JComboBox cmbState;
	
	JFrame frame;
	List<JTextField> tfList ;
	List<JTextField> tfList2;
	List<JTextField> tfList3;
	
	

	/**
	 * Launch the application.
	 */
	

	public JPanel getMainPanel() {
        return contentPane;
    }
    
	
	/**
	 * Create the frame.
	 */
	
	public AddBook() {
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddBook.class.getResource("/librarysystem/book-logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 593, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		defineTopPanel();
		defineAuthorAttributues();
		defineAddressAttributes();
		getListTextFieldsBook();
		getListTextFieldsAuthor();
		getListTextFieldsBook1();
		
	
	}
	
	public void defineTopPanel() {
		

		JLabel lblNewLabel = new JLabel("ISBN:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(96, 82, 83, 44);
		contentPane.add(lblNewLabel);
		
		txtxIsbn = new JTextField();
		txtxIsbn.setBounds(233, 82, 168, 25);
		txtxIsbn.setColumns(10);
		contentPane.add(txtxIsbn);
		
		
		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(96, 113, 119, 32);
		contentPane.add(lblNewLabel_1);
		
		txtBtitle = new JTextField();
		txtBtitle.setBounds(233, 113, 168, 25);
		txtBtitle.setColumns(10);
		contentPane.add(txtBtitle);
		
		
		JLabel lblNewLabel_2 = new JLabel("Max Checkout Length");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(80, 148, 135, 32);
		contentPane.add(lblNewLabel_2);
		
		txtLength = new JTextField();
		txtLength.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				
		            String value = txtLength.getText();
		            int l = value.length();
		            if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
		            	txtLength.setEditable(true);    
		            } 
		            else {
		            	txtLength.setEditable(false);
		               JOptionPane.showMessageDialog(frame,"Enter only numeric values");
		               txtLength.setEditable(true);
		               
		            }
		         }
		      });
		    
		txtLength.setBounds(233, 150, 168, 25);
		txtLength.setColumns(10);
		contentPane.add(txtLength);
		
		
		btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(232, 192, 169, 38);
		addBookActionListener(btnAddBook);
		contentPane.add(btnAddBook);
	}
	
	public void defineAddressAttributes()
	{

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Address", null, panel_1, null);
		panel_1.setLayout(null);
		
		texStreet = new JTextField();
		texStreet.setBounds(209, 29, 220, 28);
		panel_1.add(texStreet);
		texStreet.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setBounds(209, 68, 220, 28);
		panel_1.add(txtCity);
		txtCity.setColumns(10);
		
		txtZip = new JTextField();
		txtZip.setBounds(203, 155, 226, 41);
		panel_1.add(txtZip);
		txtZip.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Street");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(40, 39, 129, 28);
		panel_1.add(lblNewLabel_3);
		
		cmbState = new JComboBox();
		cmbState.setModel(new DefaultComboBoxModel(new String[] {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"}));
		cmbState.setBounds(206, 107, 223, 37);
		panel_1.add(cmbState);
		
		JLabel lblNewLabel_4 = new JLabel("City");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(93, 66, 76, 28);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(95, 109, 74, 28);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Zip");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(79, 159, 90, 28);
		panel_1.add(lblNewLabel_7);
	}
	
	public void defineAuthorAttributues()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 241, 561, 266);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Authors", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("First Name");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(21, 11, 76, 28);
		panel.add(lblNewLabel_3_1);
		
		txtFName = new JTextField();
		txtFName.setColumns(10);
		txtFName.setBounds(121, 11, 220, 28);
		panel.add(txtFName);
		
		JLabel lblNewLabel_4_1 = new JLabel("Last Name");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(21, 50, 76, 28);
		panel.add(lblNewLabel_4_1);
		
		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setBounds(121, 50, 220, 37);
		panel.add(txtLName);
		
		JLabel lblNewLabel_6_1 = new JLabel("Telephone");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6_1.setBounds(23, 100, 74, 28);
		panel.add(lblNewLabel_6_1);
		
		textTelephone = new JTextField();
		textTelephone.setBounds(118, 106, 223, 29);
		panel.add(textTelephone);
		textTelephone.setColumns(10);
		
		JLabel lblNewLabel_7_1 = new JLabel("Biography");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7_1.setBounds(21, 146, 76, 28);
		panel.add(lblNewLabel_7_1);
		
		txtBio = new JTextArea();
		txtBio.setBounds(107, 146, 334, 79);
		panel.add(txtBio);
		
		
		btnAddAuthor = new JButton("Add Author");
		
		btnAddAuthor.setBounds(374, 70, 137, 49);
		addAuthorActionListener(btnAddAuthor);
		
		panel.add(btnAddAuthor);
		
		JLabel lblNewLabel_5 = new JLabel("Add Book");
		lblNewLabel_5.setIcon(new ImageIcon(AddBook.class.getResource("/librarysystem/add_book.png")));
		lblNewLabel_5.setBounds(10, 11, 135, 60);
		contentPane.add(lblNewLabel_5);
		
	}

	public void getListTextFieldsBook1()
	{
		
		tfList3 = new ArrayList<JTextField>();
		
		
		tfList3.add(txtxIsbn);
		tfList3.add(txtLength);
		tfList3.add(txtBtitle);
		
	}
	
	public void getListTextFieldsBook()
	{
		
		tfList = new ArrayList<JTextField>();
		
		tfList.add(txtFName);
		tfList.add(txtLName);
		tfList.add(texStreet);
		tfList.add(txtCity);
		tfList.add(txtZip);
		tfList.add(txtxIsbn);
		tfList.add(txtLength);
		tfList.add(txtBtitle);
		tfList.add(textTelephone);
	}
	
	public void getListTextFieldsAuthor()
	{
		
		tfList2 = new ArrayList<JTextField>();
		
		tfList2.add(txtFName);
		tfList2.add(txtLName);
		tfList2.add(texStreet);
		tfList2.add(txtCity);
		tfList2.add(txtZip);
		tfList2.add(textTelephone);
		//tfList2.add(txtBio);
		
		
	}
	
	public void addAuthorActionListener(JButton butn) {
		butn.addActionListener(evt-> {
			
			String fname  = txtFName.getText();
			String lname  = txtLName.getText();
			String title =  textTelephone.getText();
			String bio =  txtBio.getText();

			String street = texStreet.getText();
			String city =  txtCity.getText();
			String state =(String)cmbState.getSelectedItem();
			String zip =  txtZip.getText();

			Address addrss =  new Address(street,city,state,zip);

			Author author = new Author(fname, lname, title, addrss, bio);

			int isEmpty =  allFieldsEmpty(tfList2);

			if (isEmpty < tfList2.size() || cmbState.getSelectedIndex() == -1 || txtBio.getText().isEmpty())

				JOptionPane.showMessageDialog(frame,"One or More of Author or Address fields must be Empty", "Field Check", JOptionPane.INFORMATION_MESSAGE); 
			else
			{

				if (!authors.contains(author)) {
					authors.add(author);
				}
				
				
				authorState = "added";

				JOptionPane.showMessageDialog(frame,"Author Added Sucessfully", "Success Message", JOptionPane.INFORMATION_MESSAGE);  

				int reply = JOptionPane.showConfirmDialog(null, "Would you Like to add another Author for the same Book ?", title, JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					
					btnAddBook.setEnabled(false);

					for (JTextField tf : tfList2) {
						tf.setText("");
					}
					
					txtBio.setText("");
					cmbState.setSelectedIndex(-1);

				} else {
					
					authorState = "";
					btnAddBook.setEnabled(true);
					
					for (JTextField tf : tfList2) {
						tf.setText("");
					}
					
					txtBio.setText("");
					cmbState.setSelectedIndex(-1);

					
				}
			}

		});
	}
	
	public void addBookActionListener(JButton butn) 
		{
			butn.addActionListener(evt-> {
				
		
		SystemController sc = new SystemController();
		
		if(txtxIsbn.getText().isEmpty()  || txtLength.getText().isEmpty()|| txtBtitle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(butn, "All Book fields must be non empty");
		}
		else
		{
			
			btnAddBook.setEnabled(false);
			String lsbn  = txtxIsbn.getText();
			int mlength  = Integer.parseInt(txtLength.getText());
			String btitle =  txtBtitle.getText();
		    int isEmpty =  allFieldsEmpty(tfList);
		    
		    
		
			
			if (authors.isEmpty())
			{
				
				if (isEmpty < tfList.size() || cmbState.getSelectedIndex() == -1 || txtBio.getText().isEmpty())
					JOptionPane.showMessageDialog(frame,"One or More of Author Fields must be Non Empty", "Field Check", JOptionPane.INFORMATION_MESSAGE); 
			}
			
			else
			{
		
				Boolean isAdded = sc.addNewBook(lsbn, btitle,mlength, authors);

				if (isAdded)
					JOptionPane.showMessageDialog(frame,"Book Added Sucessfully", "Success Message", JOptionPane.INFORMATION_MESSAGE);  

				int reply = JOptionPane.showConfirmDialog(null, "Would you Like to add another Book", "Add Book", JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {

					for (JTextField tf : tfList) {
						tf.setText("");
					}
					
					txtBio.setText("");
					cmbState.setSelectedIndex(-1);
					authors.clear();
					btnAddBook.setEnabled(true);
					

				} else {
					
					for (JTextField tf : tfList) {
						tf.setText("");
					}
				
					txtBio.setText("");
					cmbState.setSelectedIndex(0);
					authors.clear();
					btnAddBook.setEnabled(true);
					
				}
			}
		}
        
			});
		
	}
	
	public int  allFieldsEmpty(List<JTextField> tfCheck) {
		
	    int cntr = 0;
        for (JTextField textbox : tfCheck ) {
            if (! textbox.getText().trim().isEmpty() ) {
                  cntr++;
                
            }
        }
        
       return cntr;
         
    }

}
