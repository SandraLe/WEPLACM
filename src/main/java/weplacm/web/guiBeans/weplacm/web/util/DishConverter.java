package weplacm.web.util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import weplacm.ejb.DishServiceBean;
import weplacm.entity.Dish;

@ManagedBean(name="dishConverter")
public class DishConverter implements Converter {

	@EJB
	private DishServiceBean service;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return service.getDish(Long.parseLong(value));
	}

	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		return "" + ((Dish) entity).getId();
	}
}
