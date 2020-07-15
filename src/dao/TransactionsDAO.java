package dao;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.UserTransaction;

import org.apache.catalina.valves.CrawlerSessionManagerValve;

import model.Cartecredit;
import model.Facture;
import model.Proprietaire;
import model.Transaction;


@PersistenceContext
public class TransactionsDAO {
	private EntityManager em;
	private EntityTransaction trans;
	/*public CartesCreditDAO(){
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
	//CRUD sur les propri�taires
	public int addProprietaire (Proprietaire p){
		if(em.find(Proprietaire.class, p.getId())==null){
			try {
				trans.begin();
				em.persist(p);
				trans.commit();
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					trans.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return -1;
			}
		}else return -1;
	}

	
	
	public List<Cartecredit> getCarteCredits(){
		EntityManager em = ProprietaireDAO.getEntityManager();
		List<Cartecredit> liste= em.createNamedQuery("Cartecredit.findAll").getResultList();
		em.close();
		return liste;
	}
	
	public List<Proprietaire> getProprietaires(){
		EntityManager em = ProprietaireDAO.getEntityManager();
		List<Proprietaire> liste= em.createNamedQuery("Proprietaire.findAll").getResultList();
		em.close();
		return liste;
	}
	
	
	public List<Cartecredit> getListeCartesProprietaires() {
		EntityManager em = ProprietaireDAO.getEntityManager();
		List<Cartecredit> liste= em.createNamedQuery("Cartecredit.findAll").getResultList();
		em.close();
		return liste;
	}
	
	
	public int deleteTransaction (int id){
		EntityManager em = TransactionsDAO.getEntityManager();
		if(em.find(Transaction.class, id)!=null){
			try {
				em.getTransaction().begin();
				em.remove(em.find(Transaction.class, id));
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
	
	
	public int addTransaction(Transaction trans, String carteCredit,String numFacture) {
		EntityManager em1 = CartesCreditDAO.getEntityManager();
		EntityManager em2 = FactureDAO.getEntityManager();
		EntityManager em3 = TransactionsDAO.getEntityManager();
		
		try {
			if(em1.find(Cartecredit.class, carteCredit)!=null &&
					em2.find(Facture.class, numFacture)!=null 
					&& em3.find(Transaction.class, trans.getId())==null){
				System.out.println("dao if 1");
				System.out.println(trans.getId());
				em3.getTransaction().begin();
				trans.setFacture(em2.find(Facture.class, numFacture));
				trans.setCartecredit(em1.find(Cartecredit.class, carteCredit));
				em3.persist(trans);
				em3.getTransaction().commit();
			}else if(em1.find(Cartecredit.class, carteCredit)!=null &&
					em2.find(Facture.class, numFacture)!=null && em3.find(Transaction.class, trans.getId())!=null){
				em3.getTransaction().begin();
				trans.setFacture(em2.find(Facture.class, numFacture));
				trans.setCartecredit(em1.find(Cartecredit.class, carteCredit));
				em3.merge(trans);
				em3.getTransaction().commit();
				System.out.println("Modifier !!!");
			}
			em2.close();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				em2.getTransaction().rollback();
				em2.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		}
	}
	public List<Transaction> getTransactions(){
		EntityManager em = TransactionsDAO.getEntityManager();
		List<Transaction> liste= em.createNamedQuery("Transaction.findAll").getResultList();
		em.close();
		return liste;
	}
}
