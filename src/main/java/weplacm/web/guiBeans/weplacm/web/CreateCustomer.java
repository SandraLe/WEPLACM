package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;

import weplacm.ejb.CustomerServiceBean;
import weplacm.entity.Customer;
import de.wwu.pi.acse_group10.framework.web.Util;


@ManagedBean
public class CreateCustomer {
	
	private Customer customer = new Customer();

	private String errorMessage;
	
	@EJB
	private CustomerServiceBean customerService;

	public Customer getCustomer() {
		return customer;
	}
	
	
	public String persist(){
		// Action
		try{
			customer = customerService.create(getCustomer());
		}
		catch(EJBException e){
			errorMessage = "Customer not created: " + Util.getCausingMessage(e);
		}
		

		if(isError())
			return null;
		else
			return "/customer/details.xhtml?faces-redirect=true&id="
			+ customer.getId();
	}
	
	public boolean isError() {
		return errorMessage != null;
	}
	
	public String getErrorMessage() {
		return errorMessage != null ? errorMessage : "";
	}
}
		
