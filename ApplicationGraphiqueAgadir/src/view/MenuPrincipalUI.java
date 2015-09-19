package view;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuPrincipalUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Initialisation des autres UIs
	private ConfigGenerateurProjetUI configGenerateurProjetUI;
	private CreerProjetUI creerProjetUI;
	
	
    // Variables declaration - do not modify                     
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jButton1;
    // End of variables declaration  
        
    public MenuPrincipalUI(){
    	
    	configGenerateurProjetUI = new ConfigGenerateurProjetUI();
    	creerProjetUI = new CreerProjetUI();
    	
    	initComponents();
    }
    
    private void initComponents() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){ 
        	
        	public void windowClosing(WindowEvent e){
        		        		
        		MenuPrincipalUI projetUI = (MenuPrincipalUI)e.getSource();
        		int n = yesOrNoDialogBox("Voulez vous vraiment quitter?");
        		if(n == 0){
        			
        			projetUI.dispose();
        			System.exit(0);
        		}       			
        	}
        });
    	
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        setTitle("Application graphique du générateur de code");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Projet(s) existant(s) en BDD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N

      /*javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );*/

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Paramètre");
        
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setText("Nouveau projet");

        jMenu1.add(jMenuItem4);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Paramètre projet");

        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Quitter");
 
        jMenu1.add(jMenuItem3);
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>              
	
    public void creationDynamiqueDeLabelEtBouton(ArrayList<String> nomProjet){
    	
    	int nbreProjet = nomProjet.size();
    	
    	ArrayList<JButton> listBouton = new ArrayList<JButton>();
    	ArrayList<JLabel> listLabel = new ArrayList<JLabel>();
    	
    	String ouvrir = "Ouvrir"; 
    	JButton btn;
    	JLabel lbl;
    	
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
    	GroupLayout.SequentialGroup groupSequentialGroup = jPanel1Layout.createSequentialGroup();
    	GroupLayout.SequentialGroup groupSequentialGroup2 = jPanel1Layout.createSequentialGroup();
    	
    	GroupLayout.ParallelGroup groupParallel = jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    	GroupLayout.ParallelGroup groupParallel2 = jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    	
    	groupParallel.addGap(0, 348, Short.MAX_VALUE);
    	groupParallel2.addGap(0, 348, Short.MAX_VALUE);
    	
    	groupSequentialGroup.addContainerGap();
    	
    	groupParallel.addGroup(groupSequentialGroup);
    	groupParallel2.addGroup(groupSequentialGroup2);
    	
    	//Créer un label et un bouton et l'ajouter directement sur l'application graphique
    	for(int i = 0 ; i < nbreProjet ; i++){
    		
    		btn = new JButton(ouvrir);
    		lbl = new JLabel(nomProjet.get(i));
 
    		groupSequentialGroup.addComponent(lbl);
    		groupSequentialGroup.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
    		groupSequentialGroup.addComponent(btn);
    		    		
    		groupParallel2.addGap(18, 18, 18);
    		groupParallel2.addComponent(btn);
    		groupParallel2.addComponent(lbl);
    		    		
    		//groupSequentialGroup2.addContainerGap().addGroup(groupParallel2);
    		
    	}
    	
    	groupParallel.addGap(24, 24, 24);

    	jPanel1Layout.setHorizontalGroup(groupParallel);
    	jPanel1Layout.setVerticalGroup(groupParallel2);
    	
    }
	
    public void addJMenuItem1Listener(ActionListener action){
    	
    	jMenuItem1.setActionCommand("Paramètre");
    	jMenuItem1.addActionListener(action);
    }
    
    public void addJMenuItem2Listener(ActionListener action){
    	
    	jMenuItem2.setActionCommand("Paramètre projet");
    	jMenuItem2.addActionListener(action);
    }
    
    public void addJMenuItem3Listener(ActionListener action){
    	
    	jMenuItem3.setActionCommand("Quitter");
    	jMenuItem3.addActionListener(action);
    }

	public void addJMenuItem4Listener(ActionListener action){
    	
    	jMenuItem4.setActionCommand("Nouveau projet");
    	jMenuItem4.addActionListener(action);
    }
    
    public ConfigGenerateurProjetUI getConfigGenerateurProjetUI() {
		return configGenerateurProjetUI;
	}
	
    public CreerProjetUI getCreerProjetUI() {
		return creerProjetUI;
	}
    
	public int yesOrNoDialogBox(String msg){
		
		Object[] option = {"Oui", "Non"};
		
		int n = JOptionPane.showOptionDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, option, option[0]);
		
		return n;
	}	
}
