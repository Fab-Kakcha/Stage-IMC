package view;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

import org.apache.log4j.Logger;

//public class EnregistrerProjetUI extends JFrame{
public class EnregistrerProjetUI extends JDialog{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EnregistrerProjetUI.class);
	
	// Variables declaration - do not modify                     
    /*private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;

	private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;*/
    // End of variables declaration   
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    
    private GroupeParametreUI grpeParamUI = null;
    private ParametreUI paramUI = null;
    private ModeleUI modeleUI = null;
    
    
    public EnregistrerProjetUI (){
    	
    	//frame = this;
    	initComponents();
    	//nouveau();
    }
       
    public void addJButton1Listener(ActionListener action){
    	
    	jButton1.setActionCommand("nouveauGroupeDeParametre");
    	jButton1.addActionListener(action);
    }
    
    public void addJButton2Listener(ActionListener action){
    	
    	jButton2.setActionCommand("nouveauModele");
    	jButton2.addActionListener(action);
    }
    
    public void addJButton3Listener(ActionListener action){
    	
    	jButton3.setActionCommand("enregistrerProjet");
    	jButton3.addActionListener(action);
    }
    
    public void addJButton4Listener(ActionListener action){
    	
    	jButton4.setActionCommand("annulerProjet");
    	jButton4.addActionListener(action);
    }
    
    //private void nouveau(){initComponents1
    private void initComponents(){	
    	grpeParamUI = new GroupeParametreUI();
    	modeleUI = new ModeleUI();
    	paramUI= new ParametreUI();
    	
    	 jPanel1 = new javax.swing.JPanel();
         jLabel1 = new javax.swing.JLabel();
         jTextField1 = new javax.swing.JTextField();
         jLabel2 = new javax.swing.JLabel();
         jTextField2 = new javax.swing.JTextField();
         jLabel3 = new javax.swing.JLabel();
         jTextField3 = new javax.swing.JTextField();
         jLabel4 = new javax.swing.JLabel();
         jLabel5 = new javax.swing.JLabel();
         jLabel6 = new javax.swing.JLabel();
         jButton1 = new javax.swing.JButton();
         jButton2 = new javax.swing.JButton();
         jButton3 = new javax.swing.JButton();
         jButton4 = new javax.swing.JButton();
         jLabel7 = new javax.swing.JLabel();
         jLabel8 = new javax.swing.JLabel();
         
         setModal (true);
         setAlwaysOnTop (true);
         setModalityType(ModalityType.APPLICATION_MODAL);
         
         this.addComponentListener(new ComponentAdapter(){     	
         	public void componentShown(ComponentEvent ce){        		
         		jTextField1.requestFocusInWindow();
         	}
         });
         
         //frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         this.setTitle("Nouveau projet");//frame
         
         jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Projet", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 14))); // NOI18N

         jLabel1.setText("nom");

         jLabel2.setText("version");

         jLabel3.setText("code");

         jLabel4.setForeground(new java.awt.Color(255, 0, 0));
         jLabel4.setText("*");

         jLabel5.setForeground(new java.awt.Color(255, 0, 0));
         jLabel5.setText("*");

         jLabel6.setForeground(new java.awt.Color(255, 0, 0));
         jLabel6.setText("*");

         javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
         jPanel1.setLayout(jPanel1Layout);
         jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jLabel1)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                 .addComponent(jLabel2)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(jLabel5)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                 .addComponent(jLabel3)
                 .addGap(4, 4, 4)
                 .addComponent(jLabel6)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addContainerGap())
         );
         jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel1)
                     .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jLabel2)
                     .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jLabel3)
                     .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jLabel4)
                     .addComponent(jLabel5)
                     .addComponent(jLabel6))
                 .addContainerGap(23, Short.MAX_VALUE))
         );

         jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3});

         jButton1.setText("Groupe de paramètres");

         jButton2.setText("Modèle");

         jButton3.setText("Enregistrer");

         jButton4.setText("Annuler");

         jLabel7.setForeground(new java.awt.Color(255, 0, 0));
         jLabel7.setText("*");

         jLabel8.setText("champs obligatoires");

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());//frame
         this.getContentPane().setLayout(layout);//frame
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 0, Short.MAX_VALUE))
                     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                         .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                         .addComponent(jLabel7)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(jLabel8)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jButton4)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(jButton3)))
                 .addContainerGap())
         );

         layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

         layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4});

         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jButton2)
                     .addComponent(jButton1))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jButton3)
                     .addComponent(jButton4)
                     .addComponent(jLabel8)
                     .addComponent(jLabel7))
                 .addGap(22, 22, 22))
         );

         layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

         layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jButton4});           
         
         this.pack(); //frame
    }
 
   public void setVisible(){     	
    	setVisible(true);	//frame
	   //dialog.setVisible(true);
    }
        
    public void cache(){
    	setVisible(false);//frame
    }
    
    public String getProjetNom(){
    	return jTextField1.getText();
    }
    
    public String getProjetVersion(){
    	return jTextField2.getText();
    }
    
    public String getProjetCode(){
    	return jTextField3.getText();
    }
    
    public boolean verificationTextField(){
    	
    	if(getProjetNom().isEmpty())
    		return false;
    	else if(getProjetVersion().isEmpty())
    		return false;
    	else if(getProjetCode().isEmpty())
    		return false;
    	else 
    		return true;
    } 
    
    public void editerJTextField(boolean bool){
    	
    	jTextField1.setEditable(bool);
    	jTextField2.setEditable(bool);
    	jTextField3.setEditable(bool);
    }
    
    public void editerNomProjet(){   	
    	jTextField1.setEditable(true);
    }
      
    public void effacerJTextField(){
    	
    	jTextField1.setText(null);
    	jTextField2.setText(null);
    	jTextField3.setText(null);
    }
    
	public void erreurDialogBox(String msg){		
		JOptionPane.showMessageDialog(this, msg, "Erreur", JOptionPane.ERROR_MESSAGE);//frame
	}
	
	public void infoDialogBox(String msg){		
		JOptionPane.showMessageDialog(this, msg);//frame
	}
	
	public int yesOrNoDialogBox(String msg){
		
		Object[] option = {"Oui", "Non"};
		
		int n = JOptionPane.showOptionDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, option, option[0]);
		
		return n;
	}
	
    public GroupeParametreUI getGrpeParamUI() {
		return grpeParamUI;
	}

	public ParametreUI getParamUI() {
		return paramUI;
	}

	public ModeleUI getModeleUI() {
		return modeleUI;
	}
}
