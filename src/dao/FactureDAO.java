package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.Cartecredit;
import model.Facture;
import model.Proprietaire;
import model.Transaction;


@PersistenceContext
public class FactureDAO {
	private EntityManager em;
	private EntityTransaction trans;
	/*public FactureDAO(){
		EntityManagerFactory emf = 
				  Persistence.createEntityManagerFactory("test1");
		EntityManager em = emf.createEntityManager();
		trans = em.getTransaction();
	}*/
	
	static EntityManager getEntityManager(){
		EntityManagerFactory emff = 
				  Persistence.createEntityManagerFactory("per");
		return emff.createEntityManager();
	}
	
	public int addFacture(Facture facture) {
		EntityManager em1 = FactureDAO.getEntityManager();
		try {
			if(em1.find(Facture.class, facture.getNumFacture())==null){
				em1.getTransaction().begin();
				em1.persist(facture);
				em1.getTransaction().commit();
			}else{
				em1.getTransaction().begin();
				em1.merge(facture);
				em1.getTransaction().commit();
				System.out.println("Modifier !!!");
			}
			em1.close();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				em1.getTransaction().rollback();
				em1.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		}
	}
	public int deleteFacture (String numFacture){
		EntityManager em = FactureDAO.getEntityManager();
		if(em.find(Facture.class, numFacture)!=null){
			try {
				em.getTransaction().begin();
				em.remove(em.find(Facture.class, numFacture));
				em.getTransaction().commit();
				em.close();
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					em.getTransaction().rollback();
					em.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return -1;
			}
		}else return -1;
	}
	public List<Transaction> getTransactions(){
		EntityManager em = TransactionsDAO.getEntityManager();
		List<Transaction> liste= em.createNamedQuery("Transaction.findAll").getResultList();
		em.close();
		return liste;
	}
	
	public List<Facture> getFacturations(){
		EntityManager em = TransactionsDAO.getEntityManager();
		List<Facture> liste= em.createNamedQuery("Facture.findAll").getResultList();
		em.close();
		return liste;
	}

	
}
