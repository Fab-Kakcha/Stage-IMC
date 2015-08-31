package crud;

import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.log4j.Logger;

public class AgadirGenerateurUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AgadirGenerateurUI.class);
	
	// Variables declaration - do not modify                     
    private JButton jButton1;
    private JButton jButton10;
    private JButton jButton11;
    private JButton jButton12;
    private JButton jButton13;
    private JButton jButton14;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JTextField jTextField1;
    private JTextField jTextField10;
    private JTextField jTextField11;
    private JTextField jTextField12;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
    private JTextField jTextField9;
    // End of variables declaration      
    
	private JFileChooser fileChooser;
    
    
    public AgadirGenerateurUI(){
    	
    	initComponents();
    }
    
	 @SuppressWarnings("unchecked")
    private void initComponents(){
    	
			fileChooser = new JFileChooser();
		 
	        jPanel1 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jTextField2 = new javax.swing.JTextField();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        jTextField3 = new javax.swing.JTextField();
	        jTextField4 = new javax.swing.JTextField();
	        jTextField5 = new javax.swing.JTextField();
	        jTextField6 = new javax.swing.JTextField();
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jButton3 = new javax.swing.JButton();
	        jButton4 = new javax.swing.JButton();
	        jButton5 = new javax.swing.JButton();
	        jButton6 = new javax.swing.JButton();
	        jPanel2 = new javax.swing.JPanel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel10 = new javax.swing.JLabel();
	        jTextField7 = new javax.swing.JTextField();
	        jTextField8 = new javax.swing.JTextField();
	        jTextField9 = new javax.swing.JTextField();
	        jTextField10 = new javax.swing.JTextField();
	        jButton7 = new javax.swing.JButton();
	        jButton8 = new javax.swing.JButton();
	        jButton9 = new javax.swing.JButton();
	        jButton10 = new javax.swing.JButton();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jTextField11 = new javax.swing.JTextField();
	        jTextField12 = new javax.swing.JTextField();
	        jButton11 = new javax.swing.JButton();
	        jButton13 = new javax.swing.JButton();
	        jButton12 = new javax.swing.JButton();
	        jButton14 = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setTitle("Agadir - Générateur - Paramètres");

	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Les Boites de model", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans Condensed", 1, 14))); // NOI18N

	        jLabel1.setText("ModelBox Agadir");

	        jLabel2.setText("ModelBox Contact");

	        jLabel3.setText("ModelBox Referentiel Contact");

	        jLabel4.setText("ModelBox Agadir Role");

	        jLabel5.setText("ModelBox Mesure");

	        jLabel6.setText("ModelBox");

	        jButton1.setText("Choisir un fichier");

	        jButton2.setText("Choisir un fichier");

	        jButton3.setText("Choisir un fichier");

	        jButton4.setText("Choisir un fichier");

	        jButton5.setText("Choisir un fichier");

	        jButton6.setText("Choisir un fichier");

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2)
	                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
	                            .addComponent(jTextField2))
	                        .addGap(40, 40, 40))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel6)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jTextField6))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jLabel4)
	                                    .addComponent(jLabel5))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jTextField5)
	                                    .addComponent(jTextField4)))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addGap(0, 0, Short.MAX_VALUE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(jButton6)
	                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                .addComponent(jButton1)
	                                .addComponent(jButton2))
	                            .addGap(15, 15, 15)))
	                    .addComponent(jButton3)
	                    .addComponent(jButton4)
	                    .addComponent(jButton5))
	                .addGap(21, 21, 21))
	        );

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5});

	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton2))
	                .addGap(12, 12, 12)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton3))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel4)
	                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton4))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel5)
	                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton5))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton6))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Les librairies de model", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 14))); // NOI18N

	        jLabel7.setText("Librairie Model");

	        jLabel8.setText("Librairie XML");

	        jLabel9.setText("Librairie Java");

	        jLabel10.setText("Librairie Couche Bean");

	        jButton7.setText("Choisir un fichier");

	        jButton8.setText("Choisir un fichier");

	        jButton9.setText("Choisir un fichier");

	        jButton10.setText("Choisir un fichier");

	        jLabel11.setText("Librairie Couche Persistance");

	        jLabel12.setText("Librairie Couche Présentation");

	        jButton11.setText("Choisir un fichier");

	        jButton13.setText("Choisir un fichier");

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel11)
	                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addGroup(jPanel2Layout.createSequentialGroup()
	                            .addComponent(jLabel12)
	                            .addGap(18, 18, 18)
	                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
	                            .addComponent(jButton11))
	                        .addGroup(jPanel2Layout.createSequentialGroup()
	                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGap(55, 55, 55)
	                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                        .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
	                                        .addComponent(jTextField8))
	                                    .addGap(50, 50, 50)
	                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                        .addComponent(jButton8)
	                                        .addComponent(jButton13)))
	                                .addGroup(jPanel2Layout.createSequentialGroup()
	                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addComponent(jTextField10)
	                                        .addComponent(jTextField9)
	                                        .addComponent(jTextField11))
	                                    .addGap(50, 50, 50)
	                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                        .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)))))))
	                .addContainerGap(42, Short.MAX_VALUE))
	        );

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel7, jLabel8, jLabel9});

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton7, jButton9});

	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton13))
	                .addGap(11, 11, 11)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel8)
	                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton8))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel9)
	                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton9))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jButton10))
	                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11)
	                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton7))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel12)
	                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton11))
	                .addContainerGap(30, Short.MAX_VALUE))
	        );

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel7, jLabel8, jLabel9});

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton7, jButton9});

	        jButton12.setText("Enregistrer");

	        jButton14.setText("Annuler");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(37, 37, 37)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addComponent(jButton14)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton12))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(23, 23, 23))
	        );

	        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton12, jButton14});

	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(63, 63, 63)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton12)
	                    .addComponent(jButton14))
	                .addContainerGap(17, Short.MAX_VALUE))
	        );

	        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton12, jButton14});

	        pack();
    	    }// </editor-fold>  
	 
	 
    public void addJButton1Listener(ActionListener action) {
    	jButton1.setActionCommand("jButton1");
    	jButton1.addActionListener(action);
    } 
    
    public void addJButton2Listener(ActionListener action) {  
    	jButton2.setActionCommand("jButton2");
    	jButton2.addActionListener(action);
    } 
    
    public void addJButton3Listener(ActionListener action) {     	
    	jButton3.setActionCommand("jButton3");
    	jButton3.addActionListener(action);
    } 
    
    public void addJButton4Listener(ActionListener action) {     	
    	jButton4.setActionCommand("jButton4");
    	jButton4.addActionListener(action);
    } 
    
    public void addJButton5Listener(ActionListener action) {     	
    	jButton5.setActionCommand("jButton5");
    	jButton5.addActionListener(action);
    } 
    
    public void addJButton6Listener(ActionListener action) {     	
    	jButton6.setActionCommand("jButton6");
    	jButton6.addActionListener(action);
    } 
    
    public void addJButton7Listener(ActionListener action) {
    	jButton7.setActionCommand("jButton7");
    	jButton7.addActionListener(action);
    } 
    
    public void addJButton8Listener(ActionListener action) { 
    	jButton8.setActionCommand("jButton8");
    	jButton8.addActionListener(action);
    } 
    
    public void addJButton9Listener(ActionListener action) {    
    	jButton9.setActionCommand("jButton9");
    	jButton9.addActionListener(action);
    } 
    
    public void addJButton10Listener(ActionListener action) {    
    	jButton10.setActionCommand("jButton10");
    	jButton10.addActionListener(action);
    } 
    
    public void addJButton11Listener(ActionListener action) { 
    	jButton11.setActionCommand("jButton11");
    	jButton11.addActionListener(action);
    } 
    
    public void addJButton13Listener(ActionListener action) { 
    	jButton13.setActionCommand("jButton13");
    	jButton13.addActionListener(action);
    }
    
	public JFileChooser getFileChooser(){		
		return fileChooser;
	}
	
	public void setTextField(String jButton, String text) {

		switch (jButton) {

			case "jButton1":
				jTextField1.setText(text);
				break;
			case "jButton2":
				jTextField2.setText(text);
				break;
			case "jButton3":
				jTextField3.setText(text);
				break;
			case "jButton4":
				jTextField4.setText(text);
				break;
			case "jButton5":
				jTextField5.setText(text);
				break;
			case "jButton6":
				jTextField6.setText(text);
				break;
			case "jButton7":
				jTextField7.setText(text);
				break;
			case "jButton8":
				jTextField8.setText(text);
				break;
			case "jButton9":
				jTextField9.setText(text);
				break;
			case "jButton10":
				jTextField10.setText(text);
				break;
			case "jButton11":
				jTextField11.setText(text);
				break;
			case "jButton13":
				jTextField12.setText(text);
				break;
			default:
				logger.info("Unknow JTextField " + jButton);
				break;
		}

	}
    
}

