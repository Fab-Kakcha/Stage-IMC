package swingworker;

import java.sql.Connection;
import javax.swing.SwingWorker;

import model.Projet;
import bdd.BDDProjet;

public class WorkerCreationBDDProjet extends SwingWorker<Connection, Connection>{

	Projet p;
	
	public WorkerCreationBDDProjet(Projet p){
		
		this.p = p;
	}

	@Override
	protected Connection doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		BDDProjet bddProjet = new BDDProjet();
		bddProjet.creer(p.getChemin());
		
		//publish(bddProjet.getConn());
				
		return bddProjet.getConn();
	}
	
	
}
