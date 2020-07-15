package actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import dao.CartesCreditDAO;
import dao.ProprietaireDAO;
import model.Cartecredit;
import model.Proprietaire;

@Namespace("/prop")
@Results({
	@Result(name="none",location="/pages/proprietaires.jsp"),
	@Result(name="input",location="/pages/proprietaires.jsp"),
	@Result(name="success",location="/pages/proprietaires.jsp")
	})
public class ProprietairesAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	private String idProp;
	private String nomPrenom;
	private ProprietaireDAO propDAO= new ProprietaireDAO();
	private CartesCreditDAO carteDAO= new CartesCreditDAO();
	private List<Proprietaire> listeProprietaires;
	
	public ProprietairesAction() {
		super();
	}
	@Action(value="index")
	public String execute(){
		return NONE;
	}
	@Action(value="addOrSaveProp")
	public String addOrSaveProp(){
		propDAO.addOrUpdateProprietaire(new Proprietaire(Integer.parseInt(idProp),nomPrenom));
		System.out.println(propDAO.getProprietaires());
		listeProprietaires=propDAO.getProprietaires();
		return SUCCESS;
		
	}
	@Action(value="addProp")
	public String addProp(){
		//propDAO.addProprietaire(new Proprietaire(Integer.parseInt(idProp),nomPrenom));
		System.out.println(propDAO.getProprietaires());
		listeProprietaires=propDAO.getProprietaires();
		return SUCCESS;
		
	}
	@Action(value="deleteProp")
	public String deleteProp(){
		System.out.println("delete !!!");
		propDAO.deleteProprietaire(Integer.parseInt(idProp));
		listeProprietaires=propDAO.getProprietaires();
		return SUCCESS;
		
	}
	/*@Action(value="updateProp")
	public String updateProp(){
		propDAO.updateProprietaire(new Proprietaire(Integer.parseInt(idProp),nomPrenom));
		listeProprietaires=propDAO.getProprietaires();
		return SUCCESS;
		
	}*/
	public List<Proprietaire> getListeProprietaires() {
		return listeProprietaires;
	}
	public void setListeProprietaires(List<Proprietaire> listeProprietaires) {
		this.listeProprietaires = listeProprietaires;
	}
	@RequiredStringValidator(message="Ce champ ${getText(fieldName)} est obligatoire !")
	@StringLengthFieldValidator(
			message="longueur de l'entier ${getText(fieldName)} doit être entre ${minLength} et ${maxLength}",
	minLength="1",maxLength="4")
	
	public String getIdProp() {
		return idProp;
	}
	public void setIdProp(String idProp) {
		this.idProp = idProp;
	}
	@RequiredStringValidator(message="Ce champ ${getText(fieldName)}  est obligatoire !")
	@RegexFieldValidator(message="Ce champ ${getText(fieldName)} doit respecté ....", regex="[A-Z][a-zA-Z ]+")
		public String getNomPrenom() {
		return nomPrenom;
	}
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		//Chargement de la liste des proprietaires !!!
		listeProprietaires=propDAO.getProprietaires();
		
	}
	//validation coté serv
	/*public void validate() {
	      if (idProp == null || idProp.trim().equals("")) {
	         addFieldError("idProp","Saisie idProp est obligatoire !");
	      }
	   }*/

	
}
