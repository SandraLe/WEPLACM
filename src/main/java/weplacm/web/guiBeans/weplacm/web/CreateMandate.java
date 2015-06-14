package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;

import weplacm.ejb.CustomerServiceBean;
import weplacm.entity.Customer;
import weplacm.ejb.MandateServiceBean;
import weplacm.entity.Mandate;
import de.wwu.pi.acse_group10.framework.web.Util;


@ManagedBean
public class CreateMandate {
	
	private Mandate mandate = new Mandate();

	private String errorMessage;
	
	@EJB
	private MandateServiceBean mandateService;
	@EJB
	private CustomerServiceBean customerService;

	public Mandate getMandate() {
		return mandate;
	}
	
public Collection<Customer> getAllCustomers() {
	return customerService.getAllCustomers();
}
	
	public String persist(){
		// Action
		try{
			mandate = mandateService.create(getMandate());
		}
		catch(EJBException e){
			errorMessage = "Mandate not created: " + Util.getCausingMessage(e);
		}
		

		if(isError())
			return null;
		else
			return "/mandate/details.xhtml?faces-redirect=true&id="
			+ mandate.getId();
	}
	
	public boolean isError() {
		return errorMessage != null;
	}
	
	public String getErrorMessage() {
		return errorMessage != null ? errorMessage : "";
	}
}
		
