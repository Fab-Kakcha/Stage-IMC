package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;


public class View extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String INITIAL_VAlUE = "0";
	private JTextField userInput, result;
	private JButton mulButton, clearButton, openButton, saveButton;
	private JTextArea textArea;
	private JFileChooser fileChooser;
		
	private Model model;
	
	
	public View(Model model){
		
		super("Simple MVC - Example");
		
		this.model = model;
		fileChooser = new JFileChooser();
		
		JPanel pannelPanne = new JPanel();
		pannelPanne.setLayout(new BoxLayout(pannelPanne, BoxLayout.PAGE_AXIS));

		pannelPanne.add(createMultiButtonsAndTextFields());		
		pannelPanne.add(createFileButtons());
												
		this.setContentPane(pannelPanne);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 550, 400);
		
	}
	
	
	protected JComponent createFileButtons(){
		
		JPanel btnPanel = new JPanel(new FlowLayout());
		btnPanel.setBorder(new TitledBorder(null, "open and save file", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		openButton = new JButton("Open a File...");
		saveButton = new JButton("Save a File...");
		
		btnPanel.add(openButton);
		btnPanel.add(saveButton);
		
		textArea = new JTextArea(5,15);
		textArea.setEditable(false);
		JScrollPane scrollPanne = new JScrollPane(textArea);
		
		btnPanel.add(scrollPanne);
		
		return btnPanel;
	}
	
	protected JComponent createMultiButtonsAndTextFields(){
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		userInput = new JTextField(20);
		result = new JTextField(20);
		result.setEditable(false);
		
		mulButton = new JButton("Multiply");
		clearButton = new JButton("Clear");
		
		btnPanel.add(new JLabel("Input"));
		btnPanel.add(userInput);
		btnPanel.add(mulButton);
		btnPanel.add(new JLabel("Total"));
		btnPanel.add(result);
		btnPanel.add(clearButton);
		
		return btnPanel;
	}
	
	
	public void addMultiplyListener(ActionListener action){
		
		mulButton.addActionListener(action);
	}
	
	public void addClearListener(ActionListener action){
		
		clearButton.addActionListener(action);
	}
	
	public void addOpenFileListener(ActionListener action){
		
		openButton.addActionListener(action);
	}
	
	public void addSaveFileListener(ActionListener action){
		
		saveButton.addActionListener(action);
	}
	
	public void reset(){
		
		userInput.setText(INITIAL_VAlUE);
		result.setText(INITIAL_VAlUE);
	}
	
	public String getUserInput(){
		
		return userInput.getText();
	}
	
	public void setTotal(String total){
		
		result.setText(total);
	}
	
	public JFileChooser getFileChooser(){
		
		return fileChooser;
	}
	
	public void setTextArea(String text){
		
		textArea.setText(text);
	}
	
}
