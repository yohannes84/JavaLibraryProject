package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AllBookTitles implements MessageableWindow {
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private TextArea textArea;
	

	
	public AllBookTitles() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
	}
	
	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel titlesLabel = new JLabel("View Titles");
		Util.adjustLabelFont(titlesLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(titlesLabel);
	}
	
	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		middlePanel.setLayout(fl);
		textArea = new TextArea(8, 20);
		updateData();
		middlePanel.add(textArea);
		
	}
	
	public void updateData() {
		//populate
		String[] temp = {"abc", "def"};
		List<String> titles = Arrays.asList(temp);//Data.bookTitles ;
		Collections.sort(titles);
		StringBuilder sb = new StringBuilder();
		for(String s: titles) {
			sb.append(s + "\n");
		}
		textArea.setText(sb.toString());
		mainPanel.repaint();
	}
	
}
