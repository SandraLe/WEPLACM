package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import weplacm.ejb.CustomerServiceBean;
import weplacm.entity.Customer;
import de.wwu.pi.acse_group10.framework.web.Util;

@ManagedBean
@ViewScoped
public class CustomerDetails {

	@EJB
	private CustomerServiceBean customerService;

	private int id;
	private Customer customer;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		init();
	}
	
	public Customer getCustomer() {
		if (customer == null) {
			customer = customerService.getCustomerWithFetchedReferences(id);
		}
		return customer;
	}
	
	public void init() {
		customer = null;
	}

	public void ensureInitialized() {
		try {
			if (getCustomer() != null) {
				// Success
				return;
			}
		} catch (EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}
public String submitMasterDataChanges() {
	customer = customerService.updateMasterData(customer);
	return toPage();
}

	private String toPage() {
		return "/customer/details.xhtml?faces-redirect=true&id=" + id;
	}

	public String removeCustomer() {
		customerService.remove(id);
		return "/customer/list.xhtml";
	}
}
