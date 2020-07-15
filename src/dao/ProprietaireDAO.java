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

import model.Proprietaire;

@PersistenceContext
public class ProprietaireDAO {
	
	static EntityManager getEntityManager(){
		EntityManagerFactory emf = 
				  Persistence.createEntityManagerFactory("per");
		return emf.createEntityManager();
	}
	//CRUD sur les propri�taires
	public int addOrUpdateProprietaire (Proprietaire p){
		EntityManager em = ProprietaireDAO.getEntityManager();
			try {
				if(em.find(Proprietaire.class, p.getId())==null){
					em.getTransaction().begin();
					em.persist(p);
					em.getTransaction().commit();
				}else{
					em.getTransaction().begin();
					em.merge(p);
					em.getTransaction().commit();
				}
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
	}
	public int deleteProprietaire (int idProp){
		EntityManager em = ProprietaireDAO.getEntityManager();
		if(em.find(Proprietaire.class, idProp)!=null){
			try {
				em.getTransaction().begin();
				em.remove(em.find(Proprietaire.class, idProp));
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

	public List<Proprietaire> getProprietaires(){
		EntityManager em = ProprietaireDAO.getEntityManager();
		List<Proprietaire> liste= em.createNamedQuery("Proprietaire.findAll").getResultList();
		em.close();
		return liste;
	}
	public void addOrSaveProprietaire(Proprietaire proprietaire) {
		// TODO Auto-generated method stub
		
	}
}
