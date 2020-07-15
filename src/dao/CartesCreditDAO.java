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
import model.Proprietaire;


@PersistenceContext
public class CartesCreditDAO {
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
	
	
	public int deleteCarteCredit (String numCarte){
		EntityManager em = CartesCreditDAO.getEntityManager();
		if(em.find(Cartecredit.class, numCarte)!=null){
			try {
				em.getTransaction().begin();
				em.remove(em.find(Cartecredit.class, numCarte));
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
	
	
	public int addCarteCredit(Cartecredit cartecredit, int idProp) {
		EntityManager em1 = ProprietaireDAO.getEntityManager();
		EntityManager em2 = CartesCreditDAO.getEntityManager();
		try {
			if(em1.find(Proprietaire.class, idProp)!=null && em2.find(Cartecredit.class, cartecredit.getNumCarte())==null){
				em2.getTransaction().begin();
				cartecredit.setProprietaire(em1.find(Proprietaire.class, idProp));
				System.out.println("DAO : " + cartecredit.getNumCarte() + " - " + cartecredit.getProprietaire().getId());
				em2.persist(cartecredit);
				em2.getTransaction().commit();
			}else if(em1.find(Proprietaire.class, idProp)!=null){
				em2.getTransaction().begin();
				cartecredit.setProprietaire(em1.find(Proprietaire.class, idProp));
				em2.merge(cartecredit);
				em2.getTransaction().commit();
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
}
