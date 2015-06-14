package weplacm.web.util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import weplacm.ejb.MandateServiceBean;
import weplacm.entity.Mandate;

@ManagedBean(name="mandateConverter")
public class MandateConverter implements Converter {

	@EJB
	private MandateServiceBean service;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return service.getMandate(Long.parseLong(value));
	}

	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		return "" + ((Mandate) entity).getId();
	}
}
