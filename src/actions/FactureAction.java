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
import dao.FactureDAO;
import dao.ProprietaireDAO;
import dao.TransactionsDAO;
import model.Cartecredit;
import model.Facture;
import model.Proprietaire;
import model.Transaction;

@Namespace("/facture")
@Results({
	@Result(name="success",location="/pages/facture.jsp"),
	@Result(name="input",location="/pages/facture.jsp"),
	@Result(name="none",location="/pages/facture.jsp")
	})
public class FactureAction extends ActionSupport implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numFacture;
	public String getNumFacture() {
		return numFacture;
	}
	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}
	private String dateFacture;
	private double montant;
	private TransactionsDAO transactionDAO= new TransactionsDAO();
	private FactureDAO factureDAO = new FactureDAO();
	private List<Transaction> listTranscations;
	private List<Facture> listFactures;
	
	
	@Action(value="index")
	public String execute(){
		return NONE;
	}
	@Action(value="addFacture")
	public String addFacture(){
		//add
		System.out.println("Facture added");
		System.out.println(montant + " - "  + dateFacture + " - " + numFacture);
		Facture f = new Facture(numFacture, dateFacture, montant);
		System.out.println(f.getNumFacture());
		System.out.println(factureDAO.addFacture(f));
		listFactures = factureDAO.getFacturations();
		return SUCCESS;
	}
	public List<Facture> getListFactures() {
		return listFactures;
	}
	public void setListFactures(List<Facture> listFactures) {
		this.listFactures = listFactures;
	}
	@Action(value="deleteFacture")
	public String deleteFacture(){
		System.out.println("delete !!!");
		factureDAO.deleteFacture(numFacture);
		listFactures = factureDAO.getFacturations();
		return SUCCESS;
	}
	
	@Action(value="updateFacture")
	public String updateFacture(){
		listFactures = factureDAO.getFacturations();
		return SUCCESS;
	}
	public FactureAction() {
		super();
	}

	@Override
	public void prepare() throws Exception {
		//listTranscations = factureDAO.getTransactions();
		listFactures = factureDAO.getFacturations();
	}
	public FactureAction(String dateFacture, double montant, TransactionsDAO transactionDAO, FactureDAO factureDAO,
			List<Transaction> listTranscations) {
		super();
		this.dateFacture = dateFacture;
		this.montant = montant;
		this.transactionDAO = transactionDAO;
		this.factureDAO = factureDAO;
		this.listTranscations = listTranscations;
	}
	//@RequiredStringValidator(message="Ce champ est obligatoire !")
	public String getDateFacture() {
		return dateFacture;
	}
	public void setDateFacture(String dateFacture) {
		this.dateFacture = dateFacture;
	}
	//@RequiredStringValidator(message="Ce champ est obligatoire !")
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public TransactionsDAO getTransactionDAO() {
		return transactionDAO;
	}
	public void setTransactionDAO(TransactionsDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}
	public FactureDAO getFactureDAO() {
		return factureDAO;
	}
	public void setFactureDAO(FactureDAO factureDAO) {
		this.factureDAO = factureDAO;
	}
	public List<Transaction> getListTranscations() {
		return listTranscations;
	}
	public void setListTranscations(List<Transaction> listTranscations) {
		this.listTranscations = listTranscations;
	}
	
	
}
