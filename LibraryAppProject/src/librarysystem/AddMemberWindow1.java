package librarysystem;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;



@SuppressWarnings("serial")
public class AddMemberWindow1 extends JFrame implements MessageableWindow {

    String[] links;
    JList<ListItem> linkList;
    JPanel cards;
    public static JTextArea statusBar = new JTextArea("Welcome!");

    AddMemberPanel ap;
    CheckoutPanel chp;
    EditMemberPanel editPanel;
    LoginWindow lp;
    AddCopyPanel addCopy;
    AddBook addBook;
    //boolean startup = true;
    Menu menu;

    //list items
    ListItem menuItem = new ListItem("Menu", true);
    ListItem loginListItem = new ListItem("Login", false);
    ListItem addMemberListItems = new ListItem("Add New Member", false);
    ListItem addCopyOfBookListItems = new ListItem("Add Copy of Existing Book", false);
    ListItem checkoutBookListItems = new ListItem("Checkout Book", false);
    ListItem addBookListItems = new ListItem("Add Book", false);
    ListItem editListitem = new ListItem("Edit Member", true);


    ListItem[] adminItems = {menuItem, addMemberListItems,addCopyOfBookListItems ,editListitem,addBookListItems};
    ListItem[] librarianItems = {menuItem, checkoutBookListItems};
    ListItem[] bothItems = {menuItem, addMemberListItems, addCopyOfBookListItems, checkoutBookListItems,editListitem,addBookListItems};

    public ListItem[] getLibrarianItems() {
        return librarianItems;
    }
    public ListItem[] getAdminItems() {
        return adminItems ;
    }
    public ListItem[] getBothItems() { return bothItems;}

    public JList<ListItem> getLinkList() {
        return linkList;
    }


    public AddMemberWindow1() {
        Util.adjustLabelFont(statusBar, Util.DARK_BLUE, true);
        //setSize(600, 450);
        setSize(800, 630);

        createLinkLabels();
        createMainPanels();
        CardLayout cl = (CardLayout)(cards.getLayout());
        linkList.addListSelectionListener(event ->
        {
            String value = linkList.getSelectedValue().getItemName();
            boolean allowed = linkList.getSelectedValue().highlight();

            //System.out.println("selected = " + value);
            //cl = (CardLayout)(cards.getLayout());
            statusBar.setText("");
            if(!allowed) {
                value = addMemberListItems.getItemName();
                linkList.setSelectedIndex(0);
            }
            if(value.equals("Checkout Book")) chp.updateData();
            cl.show(cards,value);


        });


        // set up split panes

        JSplitPane innerPane
                = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        innerPane.setDividerLocation(180);
        JSplitPane outerPane
                = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, statusBar);
        outerPane.setDividerLocation(560);//350 default
        add(outerPane, BorderLayout.CENTER);
        LoginWindow.INSTANCE.setBookClub(this);

    }

    public JTextArea getStatusBar() {
        return statusBar;
    }

    public void createLinkLabels() {
        DefaultListModel<ListItem> model = new DefaultListModel<>();
        model.addElement(menuItem);
        //model.addElement(loginListItem);
        model.addElement(addMemberListItems);
        model.addElement(addCopyOfBookListItems);
        model.addElement(checkoutBookListItems);
        model.addElement(addBookListItems);
        model.addElement(editListitem);

        linkList = new JList<ListItem>(model);
        linkList.setCellRenderer(new DefaultListCellRenderer() {

            @SuppressWarnings("rawtypes")
            @Override
            public Component getListCellRendererComponent(JList list,
                                                          Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list,
                        value, index, isSelected, cellHasFocus);
                if (value instanceof ListItem) {
                    ListItem nextItem = (ListItem) value;
                    setText(nextItem.getItemName());
                    if (nextItem.highlight()) {
                        setForeground(Util.LINK_AVAILABLE);
                    } else {
                        setForeground(Util.LINK_NOT_AVAILABLE);
                    }
                    if (isSelected) {
                        setForeground(Color.BLACK);
                        setBackground(new Color(236,243,245));
                        //setBackground(Color.WHITE);
                    }
                }
                return c;
            }

        });
    }

    public void createMainPanels() {
        //add member
        ap = new AddMemberPanel();
        JPanel addMemberPanel = ap.getMainPanel();

        //lp =  LoginWindow.INSTANCE;
        //JPanel addLoginPanel = lp.getMainPanel();


        //Checkout Book
        chp = new CheckoutPanel();
        JPanel checkoutPanel = chp.getMainPanel();

        //EditMemberPanel

        editPanel = new EditMemberPanel();
        JPanel editMemberPanel = editPanel.getMainPanel();


        //Add a copy of Book
        addCopy = new AddCopyPanel();
        JPanel copyPanel = addCopy.getMainPanel();
        
         //add a book
        
        addBook =  new AddBook();
        JPanel addbookPanel = addBook.getMainPanel();

        //menu Item
        menu = new Menu();
        JPanel menuPanel = menu.getMainPanel();


        cards = new JPanel(new CardLayout());
        cards.add(menuPanel, menuItem.getItemName());
        //cards.add(addLoginPanel, loginListItem.getItemName());
        cards.add(addMemberPanel, addMemberListItems.getItemName());
        cards.add(copyPanel, addCopyOfBookListItems.getItemName());
        cards.add(checkoutPanel, checkoutBookListItems.getItemName());
        cards.add(addbookPanel, addBookListItems.getItemName());
        cards.add(editMemberPanel , editListitem.getItemName());

    }
    @Override
    public void updateData() {
        // nothing to do

    }
}
