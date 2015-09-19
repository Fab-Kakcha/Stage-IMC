package view;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

public class AgadirGenerateurUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AgadirGenerateurUI.class);
	
	 // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration              
    
	private JFileChooser fileChooser;
    private String btn1 = "OK";
    private String btn2 = "Annuler";
    
    public AgadirGenerateurUI(){
    	
    	initComponents();
    }
    
	 private void initComponents(){
    	
			fileChooser = new JFileChooser();
			FileFilter fileFilter = new FileNameExtensionFilter("Fichier XML", "xml");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(fileFilter);
			
		  //--------------------------------------------------------------------------------------------------------
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
	        jLabel13 = new javax.swing.JLabel();
	        jLabel14 = new javax.swing.JLabel();
	        jLabel15 = new javax.swing.JLabel();
	        jLabel16 = new javax.swing.JLabel();
	        jLabel17 = new javax.swing.JLabel();
	        jLabel18 = new javax.swing.JLabel();
	        jPanel2 = new javax.swing.JPanel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel10 = new javax.swing.JLabel();
	        jTextField8 = new javax.swing.JTextField();
	        jTextField9 = new javax.swing.JTextField();
	        jTextField10 = new javax.swing.JTextField();
	        jButton8 = new javax.swing.JButton();
	        jButton9 = new javax.swing.JButton();
	        jButton10 = new javax.swing.JButton();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jTextField7 = new javax.swing.JTextField();
	        jButton7 = new javax.swing.JButton();
	        jTextField11 = new javax.swing.JTextField();
	        jButton11 = new javax.swing.JButton();
	        jButton12 = new javax.swing.JButton();
	        jTextField12 = new javax.swing.JTextField();
	        jLabel21 = new javax.swing.JLabel();
	        jLabel22 = new javax.swing.JLabel();
	        jLabel23 = new javax.swing.JLabel();
	        jLabel24 = new javax.swing.JLabel();
	        jLabel25 = new javax.swing.JLabel();
	        jLabel26 = new javax.swing.JLabel();
	        jButton13 = new javax.swing.JButton();
	        jButton14 = new javax.swing.JButton();
	        jLabel19 = new javax.swing.JLabel();
	        jLabel20 = new javax.swing.JLabel();

	        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Agadir - Générateur - Paramètres");

	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Les Boites de model", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 14))); // NOI18N

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

	        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel13.setText("*");

	        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel14.setText("*");

	        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel15.setText("*");

	        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel16.setText("*");

	        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel17.setText("*");

	        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel18.setText("*");

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel5)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
	                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(273, 285, Short.MAX_VALUE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel6)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel4)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                                        .addComponent(jLabel3)
	                                        .addGap(9, 9, 9))
	                                    .addGroup(jPanel1Layout.createSequentialGroup()
	                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                        .addGap(28, 28, 28)))
	                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField2)
	                            .addComponent(jTextField5)
	                            .addComponent(jTextField6)
	                            .addComponent(jTextField3)
	                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jTextField4))
	                        .addGap(53, 53, 53)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))))
	                .addGap(19, 19, 19))
	        );

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1)
	                    .addComponent(jLabel13)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGap(64, 64, 64)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jButton3)
	                            .addComponent(jLabel3)
	                            .addComponent(jLabel15))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel4)
	                            .addComponent(jLabel16)
	                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jButton4))
	                        .addGap(23, 23, 23)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel5)
	                            .addComponent(jLabel17)
	                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jButton5))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel18)
	                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jButton6)))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGap(15, 15, 15)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jButton2)
	                            .addComponent(jLabel14)
	                            .addComponent(jLabel2))))
	                .addContainerGap(62, Short.MAX_VALUE))
	        );

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

	        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton4, jButton5, jButton6});

	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Les librairies de model", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 14))); // NOI18N

	        jLabel7.setText("Librairie Model");

	        jLabel8.setText("Librairie XML");

	        jLabel9.setText("Librairie Java");

	        jLabel10.setText("Librairie Couche Bean");

	        jButton8.setText("Choisir un fichier");

	        jButton9.setText("Choisir un fichier");

	        jButton10.setText("Choisir un fichier");

	        jLabel11.setText("Librairie Couche Persistance");

	        jLabel12.setText("Librairie Couche Présentation");

	        jButton7.setText("Choisir un fichier");

	        jButton11.setText("Choisir un fichier");

	        jButton12.setText("Choisir un fichier");

	        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel21.setText("*");

	        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel22.setText("*");

	        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel23.setText("*");

	        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel24.setText("*");

	        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel25.setText("*");

	        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel26.setText("*");

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                .addComponent(jLabel8)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jLabel22))
	                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                .addComponent(jLabel7)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jLabel21)))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(jButton7))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                .addComponent(jLabel10)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jLabel24))
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                        .addComponent(jLabel11)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(jLabel25))
	                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                        .addComponent(jLabel12)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                .addGap(22, 22, 22)
	                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
	                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jTextField10)
	                                    .addComponent(jTextField11)
	                                    .addComponent(jTextField12))
	                                .addGap(4, 4, 4)))
	                        .addGap(35, 35, 35)
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jButton8)
	                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(31, 31, 31))
	        );

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton12, jButton7, jButton8, jButton9});

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel12});

	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton7)
	                    .addComponent(jLabel21))
	                .addGap(23, 23, 23)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel22)
	                    .addComponent(jLabel8)
	                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton8))
	                .addGap(22, 22, 22)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel9)
	                    .addComponent(jLabel23)
	                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton9))
	                .addGap(22, 22, 22)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel24)
	                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton10))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11)
	                    .addComponent(jLabel25)
	                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton11))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel12)
	                    .addComponent(jLabel26)
	                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(54, Short.MAX_VALUE))
	        );

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel7, jLabel8});

	        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton12});

	        jButton13.setText("Enregistrer");

	        jButton14.setText("Annuler");

	        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
	        jLabel19.setText("*");

	        jLabel20.setText("Champs obligatoires");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(18, 18, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel19)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(jButton14)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton13))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap())
	        );

	        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton13, jButton14});

	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(78, 78, 78)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(18, 18, 18)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jButton13)
	                            .addComponent(jButton14)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel19)
	                            .addComponent(jLabel20))))
	                .addContainerGap(22, Short.MAX_VALUE))
	        );

	        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton13, jButton14});

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
    
    public void addJButton12Listener(ActionListener action) {
    	jButton12.setActionCommand("jButton12");
    	jButton12.addActionListener(action);
    }
    
    public void addJButton13Listener(ActionListener action){
    	jButton13.setActionCommand("Enregistrer");
    	jButton13.addActionListener(action);
    }
    
    public void addJButton14Listener(ActionListener action){
    	jButton14.setActionCommand("Annuler");
    	jButton14.addActionListener(action);
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
			case "jButton12":
				jTextField12.setText(text);
				break;
			default:
				logger.error("Unknown JButton " + jButton);
				break;
		}
	}
	
	public String getTextField(String jButton) {

		switch (jButton) {

			case "jButton1":
				return jTextField1.getText();
			case "jButton2":
				return jTextField2.getText();
			case "jButton3":
				return jTextField3.getText();
			case "jButton4":
				return jTextField4.getText();
			case "jButton5":
				return jTextField5.getText();
			case "jButton6":
				return jTextField6.getText();
			case "jButton7":
				return jTextField7.getText();
			case "jButton8":
				return jTextField8.getText();
			case "jButton9":
				return jTextField9.getText();
			case "jButton10":
				return jTextField10.getText();
			case "jButton11":
				return jTextField11.getText();
			case "jButton12":
				return jTextField12.getText();
			default:
				logger.error("Unknow JTextField " + jButton);
				return null;
		}
	}
    
	public boolean verificationTextField(){
		
		if(getTextField("jButton1").isEmpty())
			return false;
		else if(getTextField("jButton2").isEmpty())
			return false;
		else if(getTextField("jButton3").isEmpty())
			return false;
		else if(getTextField("jButton4").isEmpty())
			return false;
		else if(getTextField("jButton5").isEmpty())
			return false;
		else if(getTextField("jButton6").isEmpty())
			return false;
		else if(getTextField("jButton7").isEmpty())
			return false;
		else if(getTextField("jButton8").isEmpty())
			return false;
		else if(getTextField("jButton9").isEmpty())
			return false;
		else if(getTextField("jButton10").isEmpty())
			return false;
		else if(getTextField("jButton11").isEmpty())
			return false;
		else if(getTextField("jButton12").isEmpty())
			return false;
		else return true;		
	}
	
	public void erreurDialogBox(String msg){
		
		JOptionPane.showMessageDialog(this, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public void infoDialogBox(String msg){
		
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public int yesOrNoDialogBox(String msg){
		
		Object[] option = {btn1, btn2};
		
		int n = JOptionPane.showOptionDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, option, option[0]);
		
		return n;
	}
}
