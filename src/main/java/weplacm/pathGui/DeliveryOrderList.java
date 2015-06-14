package weplacm.web;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import weplacm.ejb.DeliveryOrderServiceBean;

import weplacm.entity.DeliveryOrder;

@ManagedBean
public class DeliveryOrderList {

	@EJB
	private DeliveryOrderServiceBean deliveryOrderService;
	
	private Collection<DeliveryOrder> deliveryOrders;

	public Collection<DeliveryOrder> getAll() {
		if (deliveryOrders == null)
			deliveryOrders = deliveryOrderService.getAllDeliveryOrders();
		return deliveryOrders;
	}
}
