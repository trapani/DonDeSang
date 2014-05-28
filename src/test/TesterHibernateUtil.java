package test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TesterHibernateUtil {

	public static void main(String[] args) {
		
		Session session = null;
		session = HibernateUtil.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
			
			
			tx.commit(); // Mettre physiquement les objets créés dans la base de données
			System.out.println("pas de problème de mapping");
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); // On lui dit de ne pas tenir compte des opérations effectuées
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}
		
	}

}
