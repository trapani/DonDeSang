package test;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Infirmiere;
import pojo.Medecin;
import pojo.Stock;

public class AjoutDB {
	
	public static void main(String[] args) {
		Session session = null;
		session = HibernateUtil.instance().openSession();
		Transaction tx = null;
		
		
		try{
			tx = session.beginTransaction();
			
			
			/*Medecin med = new Medecin();
			med.setNom("Dupont");
			med.setPrenom("Franck");
			session.persist(med);
			
			Medecin med2 = new Medecin();
			med2.setNom("Laplace");
			med2.setPrenom("Benoît");
			session.persist(med2);
			
			Medecin med3 = new Medecin();
			med3.setNom("Durand");
			med3.setPrenom("Giovanni");
			session.persist(med3);
			*/
			
			/*Infirmiere inf = new Infirmiere();
			inf.setNom("Delarue");
			inf.setPrenom("Martine");
			session.persist(inf);
			
			Infirmiere inf2 = new Infirmiere();
			inf2.setNom("Baudouin");
			inf2.setPrenom("Nicole");
			session.persist(inf2);
			
			Infirmiere inf3 = new Infirmiere();
			inf3.setNom("Duchateau");
			inf3.setPrenom("Pamela");
			session.persist(inf3);*/
			
			//Stock stock = new Stock();
			
			
			tx.commit();
			JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succés");
			
		}catch(HibernateException he){
			if (tx != null) tx.rollback(); 
			he.printStackTrace(); 
		}finally{
			session.close();
		}
	}

}
