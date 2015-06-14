package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;

import weplacm.ejb.CustomerServiceBean;
import weplacm.entity.Customer;
import weplacm.ejb.DeliveryOrderServiceBean;
import weplacm.entity.DeliveryOrder;
import de.wwu.pi.acse_group10.framework.web.Util;


@ManagedBean
public class CreateDeliveryOrder {
	
	private DeliveryOrder deliveryOrder = new DeliveryOrder();

	private String errorMessage;
	
	@EJB
	private DeliveryOrderServiceBean deliveryOrderService;
	@EJB
	private CustomerServiceBean customerService;

	public DeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}
	
public Collection<Customer> getAllCustomers() {
	return customerService.getAllCustomers();
}
	
	public String persist(){
		// Action
		try{
			deliveryOrder = deliveryOrderService.create(getDeliveryOrder());
		}
		catch(EJBException e){
			errorMessage = "DeliveryOrder not created: " + Util.getCausingMessage(e);
		}
		

		if(isError())
			return null;
		else
			return "/deliveryOrder/details.xhtml?faces-redirect=true&id="
			+ deliveryOrder.getId();
	}
	
	public boolean isError() {
		return errorMessage != null;
	}
	
	public String getErrorMessage() {
		return errorMessage != null ? errorMessage : "";
	}
}
		
