package weplacm.web;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import weplacm.ejb.CustomerServiceBean;

import weplacm.entity.Customer;

@ManagedBean
public class CustomerList {

	@EJB
	private CustomerServiceBean customerService;
	
	private Collection<Customer> customers;

	public Collection<Customer> getAll() {
		if (customers == null)
			customers = customerService.getAllCustomers();
		return customers;
	}
}
