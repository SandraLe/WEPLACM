package weplacm.web.util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import weplacm.ejb.DeliveryOrderServiceBean;
import weplacm.entity.DeliveryOrder;

@ManagedBean(name="deliveryOrderConverter")
public class DeliveryOrderConverter implements Converter {

	@EJB
	private DeliveryOrderServiceBean service;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return service.getDeliveryOrder(Long.parseLong(value));
	}

	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		return "" + ((DeliveryOrder) entity).getId();
	}
}
