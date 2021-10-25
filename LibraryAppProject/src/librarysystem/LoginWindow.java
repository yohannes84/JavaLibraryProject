package librarysystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import business.LoginException;
import business.SystemController;
import dataaccess.Auth;


public class LoginWindow extends JFrame implements LibWindow, MessageableWindow{
    public static final LoginWindow INSTANCE = new LoginWindow();
	JFrame frame;

	AddMemberWindow1 bookClub;
	public void setBookClub(AddMemberWindow1 club) {
		bookClub = club;
	}
	
	private boolean isInitialized = false;
	
	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	private JPanel container;
	
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;
	
	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;
	
	String pathToImage;
	String pathToImage2;
	
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private JTextField messageBar = new JTextField();
	public void clear() {
		messageBar.setText("");
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	 private void setPathToImage() {
	    	String currDirectory = System.getProperty("user.dir");
	    	pathToImage = currDirectory+"\\src\\librarysystem\\Book2.png";
	    	pathToImage2 = currDirectory+"\\src\\librarysystem\\gr.png";
	    }

	
	/* This class is a singleton */
    private LoginWindow () {}
    
    public void init() {
    	
    	    setPathToImage();

    	    TitledBorder borderLogin = new TitledBorder("Login");
	    	borderLogin.setTitleJustification(TitledBorder.LEFT);
	    	borderLogin.setTitlePosition(TitledBorder.TOP);
	    	mainPanel = new JPanel();
	    	mainPanel.setBorder(borderLogin);
    		defineUpperHalf();
    		defineMiddleHalf();
    		defineLowerHalf();
    		BorderLayout bl = new BorderLayout();
    		bl.setVgap(30);
    		mainPanel.setLayout(bl);
    		mainPanel.setBackground(Color.LIGHT_GRAY);
    		
    		
    					
    		mainPanel.add(upperHalf, BorderLayout.NORTH);
    		mainPanel.add(middleHalf, BorderLayout.CENTER);
    		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
    		getContentPane().setBackground(Color.lightGray);
    		getContentPane().setBackground(getBackground());
    		getContentPane().add(mainPanel);
    		
    		setSize(800,600);
    		
    		isInitialized(true);
    	    pack();


    	
    }
    private void defineUpperHalf() {
    		
    		upperHalf = new JPanel();
    		upperHalf.setLayout(new BorderLayout());
    		defineTopPanel();
    		defineMiddlePanel();
    		defineLowerPanel();
    		upperHalf.add(topPanel, BorderLayout.NORTH);
    		upperHalf.add(middlePanel, BorderLayout.CENTER);
    		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
    		
    	}
    	private void defineMiddleHalf() {
    		middleHalf = new JPanel();
    		middleHalf.setLayout(new BorderLayout());
    		JSeparator s = new JSeparator();
    		s.setOrientation(SwingConstants.HORIZONTAL);
    		//middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
    		middleHalf.add(s, BorderLayout.SOUTH);
    		
    	}
    	private void defineLowerHalf() {

    		lowerHalf = new JPanel();
    		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
    		
    		JButton backButton = new JButton("<<Back to Main");
    		addBackButtonListener(backButton);
    		lowerHalf.add(backButton);
    		
    	}
    	private void defineTopPanel() {
    		topPanel = new JPanel();
    		ImageIcon image2 = new ImageIcon(pathToImage2);
    		
    		
    		JPanel intPanel = new JPanel(new BorderLayout());
    		intPanel.setBackground(new Color(185,17,128));
    		//intPanel.add(new JLabel(image2));	
    		intPanel.add(Box.createRigidArea(new Dimension(60,300)), BorderLayout.NORTH);
    		//JLabel loginLabel = new JLabel("Login");
    		//Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
    		//intPanel.add(loginLabel, BorderLayout.CENTER);
    		ImageIcon image = new ImageIcon(pathToImage);
    		topPanel.add(new JLabel(image));	
    		
    		topPanel.setBackground(Color.LIGHT_GRAY);
    		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    		topPanel.add(intPanel);
    		
    		
    	}
    	
    	
    	private void defineMiddlePanel() {
    		middlePanel=new JPanel();
    		middlePanel.setLayout(new GridLayout(2,1,10,5));
    		//middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		defineLeftTextPanel();
    		defineRightTextPanel();
    		//middlePanel.setBackground(Color.red);
    		middlePanel.add(leftTextPanel);
    		middlePanel.add(rightTextPanel);
    	}
    	private void defineLowerPanel() {
    		lowerPanel = new JPanel();
    		loginButton = new JButton("Login");
    		addLoginButtonListener(loginButton);
    		lowerPanel.add(loginButton);
    	}

    	private void defineLeftTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
    		topText.setLayout(new GridLayout(2,1));
//    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
//    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		
    		username = new JTextField();
    		label = new JLabel("Username");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		
    		topText.add(label);
    		topText.add(username);
    		
    		leftTextPanel = new JPanel();
    		leftTextPanel.setLayout(new GridLayout(0, 1,0,0));
    		leftTextPanel.add(topText,BorderLayout.NORTH);
    		//leftTextPanel.add(bottomText,BorderLayout.CENTER);
    	}
    	private void defineRightTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
//    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
//    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		topText.setLayout(new GridLayout(2,1));
    		//bottomText.setLayout(new GridLayout(0, 1,0,0));
    		
    		password = new JPasswordField(25);
    		label = new JLabel("Password");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		
    		topText.add(label);
    		topText.add(password);
    		
    		rightTextPanel = new JPanel();
    		//rightTextPanel.setBackground(Color.red);
    		rightTextPanel.setLayout(new GridLayout(0, 1,0,0));
    		//rightTextPanel.setLayout(new BorderLayout());
    		rightTextPanel.add(topText,BorderLayout.NORTH);
    		//rightTextPanel.add(bottomText,BorderLayout.CENTER);
    	}
    	
    	private void addBackButtonListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			LibrarySystem.hideAllWindows();
				if(frame!= null) {
					frame.setVisible(false);
				}
    			LibrarySystem.INSTANCE.setVisible(true);
    		});
    	}
    	
    	private void addLoginButtonListener(JButton butn) {
    		butn.addActionListener(evt -> {
				SystemController sc = new SystemController();
				boolean valid = true;
				String message = "";

				String user = username.getText().trim();
				String pwd = password.getText().trim();
				if(user.length() == 0 || pwd.length() == 0) {
					JOptionPane.showMessageDialog(butn, "Id and Password fields must be nonempty");
				}
				else {
					try {
						sc.login(user, pwd);
					} catch (LoginException ex) {
						message = ex.getMessage();
						valid = false;
					}
					if(!valid) {
						JOptionPane.showMessageDialog(butn, message);

					}
					else {
						frame = new AddMemberWindow1();
						frame.setTitle("Library System");
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						//frame.setSize(800,600);
						centerFrameOnDesktop(frame);
						frame.setVisible(true);
						LibrarySystem.hideAllWindows();
						displayInfo("Login successful");
						updateLeftPanel(sc.currentAuth);
						bookClub.repaint();
					}
				}
			});
    	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
		//f.setLocation(700, 100);
	}

	private void updateLeftPanel(Auth auth) {
		if(auth == Auth.LIBRARIAN) librarianItems();
		else if(auth == Auth.ADMIN) adminItems();
		else if(auth == Auth.BOTH) bothItems();

	}
	private void librarianItems() {
		ListItem[] librarianItems  = bookClub.getLibrarianItems();
		updateList(librarianItems);
	}
	private void adminItems() {
		ListItem[] adminItems = bookClub.getAdminItems();
		updateList(adminItems);
	}
	private void bothItems() {
		//ListItem[] bothItems = bookClub.getBothItems();
		updateList(null);
	}

	private void updateList(ListItem[] items) {
		JList<ListItem> linkList = bookClub.getLinkList();
		DefaultListModel<ListItem> model = (DefaultListModel)linkList.getModel();
		int size = model.getSize();
		if(items != null) {
			List<Integer> indices = new ArrayList<>();
			for(ListItem item : items) {
				int index = model.indexOf(item);
				indices.add(index);
				ListItem next = (ListItem)model.get(index);
				next.setHighlight(true);

			}
			for(int i = 0; i <size; ++i) {
				if(!indices.contains(i)) {
					ListItem next = (ListItem)model.get(i);
					next.setHighlight(false);
				}
			}
		} else {
			for(int i = 0; i <size; ++i) {
				ListItem next = (ListItem)model.get(i);
				next.setHighlight(true);
			}

		}
	}

		@Override
	public void updateData() {
		//do nothing
		}
	
        
    
}