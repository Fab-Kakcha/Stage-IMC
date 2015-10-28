package view;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import javax.swing.*;

/**
 * Il s'agit de l'interface principale de l'application graphique.
 * @author fkakcha
 *
 */

public class MenuPrincipalUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Initialisation des autres UIs
	private ConfigGenerateurUI configGenerateurProjetUI;
	private CreerProjetUI creerProjetUI;
	private ConfigProjetUI configProjetUI;
	
	private HashMap<String, JButton> listBoutonOuvrir = new HashMap<String, JButton>();
	private HashMap<String, JButton> listBoutonSupprimer = new HashMap<String, JButton>();
	//private ArrayList<JLabel> listLabel = new ArrayList<JLabel>();	
	
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
    //private javax.swing.JLabel jLabel1;
    //private javax.swing.JButton jButton1;
    // End of variables declaration  
        
    public MenuPrincipalUI(){
    	
    	configGenerateurProjetUI = new ConfigGenerateurUI();
    	creerProjetUI = new CreerProjetUI();
    	configProjetUI = new ConfigProjetUI();
    	
    	initComponents();
    	desactiverJMenuItemParametreProjet();
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

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addContainerGap())
         );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
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
	
    /**
     * Création dynamique de Label et Bouton pour afficher des projets en BDD. Une fois des projets affichés, des Boutons permettront de les ouvrir pour 
     * configurer des paramètres, ou bien de les supprimer de l'UI et la BDD.
     * @param listNomProjet
     */
    public void creationDynamiqueDeLabelEtBouton(ArrayList<String> listNomProjet){
    	
    	jPanel1.removeAll();
    	jPanel1.updateUI();
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
    	
    	GroupLayout.SequentialGroup groupSequentialGroup = jPanel1Layout.createSequentialGroup();
    	GroupLayout.SequentialGroup groupSequentialGroup2 = jPanel1Layout.createSequentialGroup();
    	GroupLayout.SequentialGroup groupSequentialGroupComponent;
    	
    	GroupLayout.ParallelGroup groupParallel = jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    	GroupLayout.ParallelGroup groupParallel2 = jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    	GroupLayout.ParallelGroup groupParallelGroupComponent;
    	
    	JButton btn, btn1;
    	JLabel lbl;
    	
    	if(!listNomProjet.isEmpty()){
    		
        	int nbreProjet = listNomProjet.size();        	
        	String nomProjet;
        	
        	groupParallel.addGap(0, 348, Short.MAX_VALUE);
        	groupParallel2.addGap(0, 348, Short.MAX_VALUE);
        	
        	groupSequentialGroup.addContainerGap();
        	groupSequentialGroup2.addContainerGap();        	
        	
        	//Créer un label et un bouton et l'ajouter directement sur l'application graphique
        	for(int i = 0 ; i < nbreProjet ; i++){
        		
        		nomProjet = listNomProjet.get(i);
        		
        		btn = new JButton("Ouvrir");
        		btn1 = new JButton("Supprimer");
        		
        		lbl = new JLabel(nomProjet);       		
        		
                jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn, btn1});
        		
        		listBoutonOuvrir.put(nomProjet, btn);
        		listBoutonSupprimer.put(nomProjet, btn1);
        		
        		groupSequentialGroupComponent = jPanel1Layout.createSequentialGroup();
        		groupSequentialGroupComponent.addComponent(lbl);
        		groupSequentialGroupComponent.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        		groupSequentialGroupComponent.addComponent(btn);
        		groupSequentialGroupComponent.addComponent(btn1);//
        		
        		groupParallel.addGroup(groupSequentialGroupComponent);
        		    		
        		groupParallelGroupComponent = jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        		
        		groupParallelGroupComponent.addComponent(btn1);//
        		groupParallelGroupComponent.addComponent(btn);
        		groupParallelGroupComponent.addComponent(lbl);
        		    		
        		groupSequentialGroup2.addGroup(groupParallelGroupComponent);
        		groupSequentialGroup2.addGap(18, 18, 18);
        		
        	} 
        	
        	groupSequentialGroup.addContainerGap().addGroup(groupParallel);
        	GroupLayout.ParallelGroup groupParallel3 = jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        	groupParallel3.addGroup(groupSequentialGroup);
        	groupParallel3.addGap(24, 24, 24);
        	
        	groupParallel2.addGroup(groupSequentialGroup2);
        	
        	jPanel1Layout.setHorizontalGroup(groupParallel3);
        	jPanel1Layout.setVerticalGroup(groupParallel2);      	      	    	    		
    	
    	}else{
    		
    		lbl = new JLabel("Aucun projet en BDD");
    		
    		lbl.setFont(new java.awt.Font("DejaVu Sans", 0, 14));
    		groupSequentialGroup.addComponent(lbl);
    		groupParallel.addGroup(groupSequentialGroup);
    		    		    		
    		groupSequentialGroup2.addContainerGap();
    		groupSequentialGroup2.addComponent(lbl);
    		groupParallel2.addGroup(groupSequentialGroup2);
    		
        	jPanel1Layout.setHorizontalGroup(groupParallel);
        	jPanel1Layout.setVerticalGroup(groupParallel2); 
    	}    	
    }
    
    /**
     * Ajoute un listener sur tous les boutons d'ouverture de projet crées dynamiquement
     * @param action
     */
    public void addListBoutonOuvrirListener(ActionListener action){
    	
    	JButton btn;
    	String nom;
    	Set<String> listNomProjet = listBoutonOuvrir.keySet();
    	Iterator<String> ite = listNomProjet.iterator();
    	
    	while(ite.hasNext()){
    		
    		nom = ite.next();
    		btn = listBoutonOuvrir.get(nom);
    		addBoutonListener(btn, action, nom);
    	}  	
    }
    
    /**
     * Ajoute un listener sur tous les boutons de suppression de projet crées dynamiquement
     * @param action
     */
    public void addListBoutonSupprimerListener(ActionListener action){
    	
    	JButton btn;
    	String nom;
    	Set<String> jButtons = listBoutonSupprimer.keySet();
    	Iterator<String> ite = jButtons.iterator();
    	
    	while(ite.hasNext()){
    		
    		nom = ite.next();
    		btn = listBoutonSupprimer.get(nom);
    		addBoutonListener(btn, action, nom);
    	}  	
    }
    
    private void addBoutonListener(JButton btn, ActionListener action, String nom){
    	
    	btn.setActionCommand(nom);
    	btn.addActionListener(action);
    }
    
    public void addJMenuItem1Listener(ActionListener action){
    	
    	jMenuItem1.setActionCommand("Paramètre generateur");
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
    
    public ConfigGenerateurUI getConfigGenerateurProjetUI() {
		return configGenerateurProjetUI;
	}
	
    public CreerProjetUI getCreerProjetUI(){
		return creerProjetUI;
	}
    
    public ConfigProjetUI getConfigProjetUI() {
  		return configProjetUI;
  	}
    
    public void desactiverJMenuItemParametreProjet(){
    	jMenuItem2.setEnabled(false);
    }
    
    public void activerJMenuItemParametreProjet(){
    	jMenuItem2.setEnabled(true);
    }
    
	public void infoDialogBox(String msg){	
		JOptionPane.showMessageDialog(this, msg);
	}
    
	public int yesOrNoDialogBox(String msg){
		
		Object[] option = {"Oui", "Non"};	
		int n = JOptionPane.showOptionDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, option, option[0]);
		
		return n;
	}	
}
