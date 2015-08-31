package mvc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Controller {

	private View view;
	private Model model;
	private File file;
	
	
	public Controller(View view, Model model) {
		
		this.model = model;
		this.view = view;
		
		view.addMultiplyListener(new MultiplyAction());
		view.addClearListener(new ClearAction());
		view.addOpenFileListener(new OpenFileAction());
		view.addSaveFileListener(new SaveFileAction());
		
	}
	
	
	class MultiplyAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			String userInput = view.getUserInput();
			System.out.println(Integer.parseInt(userInput) * 3);
			view.setTotal(userInput);
		}		
	}
	
	class ClearAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			view.reset();
		}
		
	}
	
	class OpenFileAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			JFileChooser fc =  view.getFileChooser();
			int returnVal = fc.showOpenDialog(fc);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
								
				try {
					
					file = fc.getSelectedFile();
					InputStream inputStream = new FileInputStream(file);
					
					byte[] buf = new byte[1024];
					int bytes_read;
					
					do{
						
						bytes_read = inputStream.read(buf);
					}while(bytes_read != -1);
									
					view.setTextArea(new String(buf));					
					inputStream.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
				
				
			}else if(returnVal == JFileChooser.ERROR_OPTION){
				
				
			}
						
		}
		
	}
	
	class SaveFileAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			JFileChooser fc =  view.getFileChooser();
			int returnVal = fc.showSaveDialog(fc);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				
				try {
					
					file = fc.getSelectedFile();
					InputStream inputStream = new FileInputStream(file);
					
					/*byte[] buf = new byte[1024];
					int bytes_read;
					
					do{
						
						bytes_read = inputStream.read(buf);
					}while(bytes_read != -1);
									
					view.setTextArea(new String(buf));					
					inputStream.close();*/
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
				
				
			}else if(returnVal == JFileChooser.ERROR_OPTION){
				
				
			}
				
			
		}
		
	}
}
