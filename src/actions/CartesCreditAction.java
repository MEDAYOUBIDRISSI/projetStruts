package actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import dao.CartesCreditDAO;
import dao.ProprietaireDAO;
import model.Cartecredit;
import model.Proprietaire;

@Namespace("/credit")
@Results({
	@Result(name="success",location="/pages/credits.jsp"),
	@Result(name="input",location="/pages/credits.jsp"),
	@Result(name="none",location="/pages/credits.jsp")
	})
public class CartesCreditAction extends ActionSupport implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numCarte;
	private ProprietaireDAO propDAO= new ProprietaireDAO();
	private List<Proprietaire> listeProprietaires = propDAO.getProprietaires();
	private CartesCreditDAO creditDAO = new CartesCreditDAO();
	private String propSelected;
	private List<Cartecredit> listeCartesProprietaires ;
	private List<Cartecredit> listCarteCredit;
	
	
	public ProprietaireDAO getPropDAO() {
		return propDAO;
	}
	public void setPropDAO(ProprietaireDAO propDAO) {
		this.propDAO = propDAO;
	}
	public CartesCreditDAO getCreditDAO() {
		return creditDAO;
	}
	public void setCreditDAO(CartesCreditDAO creditDAO) {
		this.creditDAO = creditDAO;
	}
	
	public List<Cartecredit> getListeCartesProprietaires() {
		return listeCartesProprietaires;
	}
	public void setListeCartesProprietaires(List<Cartecredit> listeCartesProprietaires) {
		this.listeCartesProprietaires = listeCartesProprietaires;
	}
	public List<Cartecredit> getListCarteCredit() {
		return listCarteCredit;
	}
	public void setListCarteCredit(List<Cartecredit> listCarteCredit) {
		this.listCarteCredit = listCarteCredit;
	}
	public String getPropSelected() {
		return propSelected;
	}
	public void setPropSelected(String propSelected) {
		this.propSelected = propSelected;
	}
	public List<Proprietaire> getListeProprietaires() {
		return listeProprietaires;
	}
	public void setListeProprietaires(List<Proprietaire> listeProprietaires) {
		this.listeProprietaires = listeProprietaires;
	}
	@Action(value="index")
	public String execute(){
		return NONE;
	}
	@Action(value="addCredit")
	public String addCredit(){
		//add
		System.out.println(propSelected + " - " + numCarte );
		Cartecredit c = new Cartecredit(numCarte);
		creditDAO.addCarteCredit(c, Integer.parseInt(propSelected));
		//System.out.println(c);
		//charger la listes cartes et prop
		//System.out.println(creditDAO.getCarteCredits());
		listeCartesProprietaires = creditDAO.getListeCartesProprietaires();
		propSelected="";
		numCarte="";
		return SUCCESS;
	}
	@Action(value="deleteCarte")
	public String deleteProp(){
		System.out.println("delete !!!");
		creditDAO.deleteCarteCredit(numCarte);
		listeCartesProprietaires = creditDAO.getListeCartesProprietaires();
		numCarte="";
		return SUCCESS;
		
	}
	public CartesCreditAction() {
		super();
	}
	@RequiredStringValidator(message="Ce champ est obligatoire !")
	public String getNumCarte() {
		return numCarte;
	}
	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}
	@Override
	public void prepare() throws Exception {
		// Charger la liste des proprietaires
		listeProprietaires = propDAO.getProprietaires();
		System.out.println(listeProprietaires);
		//charger la listes cartes et prop
		listCarteCredit = creditDAO.getCarteCredits();
		//Afficher
		/*for(Object[] o :listeCartesProprietaires){
			System.out.println(o[0]+"-"+o[1]);
		}*/
		
	}
	
	
}
