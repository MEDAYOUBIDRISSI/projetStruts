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

@Namespace("/transaction")
@Results({
	@Result(name="success",location="/pages/transaction.jsp"),
	@Result(name="input",location="/pages/transaction.jsp"),
	@Result(name="none",location="/pages/transaction.jsp")
	})
public class TransactionAction extends ActionSupport implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String datePaiement;
	private double montant;
	private String carteCredit;
	private String numFacture;
	private TransactionsDAO tarnsactionsDao = new TransactionsDAO();
	private FactureDAO facturesDao = new FactureDAO();
	private CartesCreditDAO cartesDao = new CartesCreditDAO();
	private List<Transaction> listTransactions;
	private List<Facture> listeNumFactures;
	private List<Cartecredit> listeCartes;
	
	
	public CartesCreditDAO getCartesDao() {
		return cartesDao;
	}
	public void setCartesDao(CartesCreditDAO cartesDao) {
		this.cartesDao = cartesDao;
	}
	public List<Cartecredit> getListeCartes() {
		return listeCartes;
	}
	public void setListeCartes(List<Cartecredit> listeCartes) {
		this.listeCartes = listeCartes;
	}
	public String getCarteCredit() {
		return carteCredit;
	}
	public void setCarteCredit(String carteCredit) {
		this.carteCredit = carteCredit;
	}
	public String getNumFacture() {
		return numFacture;
	}
	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}
	public List<Transaction> getListTransactions() {
		return listTransactions;
	}
	public void setListTransactions(List<Transaction> listTransactions) {
		this.listTransactions = listTransactions;
	}

	
	public List<Transaction> getListTranscations() {
		return listTransactions;
	}
	public void setListTranscations(List<Transaction> listTranscations) {
		this.listTransactions = listTranscations;
	}
	@Action(value="index")
	public String execute(){
		return NONE;
	}
	public TransactionsDAO getTarnsactionsDao() {
		return tarnsactionsDao;
	}
	public void setTarnsactionsDao(TransactionsDAO tarnsactionsDao) {
		this.tarnsactionsDao = tarnsactionsDao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Action(value="addTransaction")
	public String addCredit(){
		//add
		System.out.println("Transaction added");
		System.out.println( id + " - " + montant + " - "  + datePaiement + " - " + carteCredit + " - " + numFacture );
		tarnsactionsDao.addTransaction(new Transaction(id,datePaiement, montant), carteCredit, numFacture);
		listTransactions = tarnsactionsDao.getTransactions();
		return SUCCESS;
	}
	@Action(value="deleteTransaction")
	public String deleteProp(){
		tarnsactionsDao.deleteTransaction(id);
		listTransactions = tarnsactionsDao.getTransactions();
		return SUCCESS;
		
	}
	
	public FactureDAO getFacturesDao() {
		return facturesDao;
	}
	public void setFacturesDao(FactureDAO facturesDao) {
		this.facturesDao = facturesDao;
	}
	public List<Facture> getListeNumFactures() {
		return listeNumFactures;
	}
	public void setListeNumFactures(List<Facture> listeNumFactures) {
		this.listeNumFactures = listeNumFactures;
	}
	public TransactionAction() {
		super();
	}
	@Override
	public void prepare() throws Exception {
		listeNumFactures = facturesDao.getFacturations();
		listTransactions = tarnsactionsDao.getTransactions();
		listeCartes = cartesDao.getCarteCredits();
	}
	public String getDatePaiement() {
		return datePaiement;
	}
	public void setDatePaiement(String datePaiement) {
		this.datePaiement = datePaiement;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	public TransactionAction(String datePaiement, double montant) {
		super();
		this.datePaiement = datePaiement;
		this.montant = montant;
	}
	
	
}
