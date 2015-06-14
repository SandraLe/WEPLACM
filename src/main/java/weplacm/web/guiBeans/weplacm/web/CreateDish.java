package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;

import weplacm.ejb.DishServiceBean;
import weplacm.entity.Dish;
import de.wwu.pi.acse_group10.framework.web.Util;


@ManagedBean
public class CreateDish {
	
	private Dish dish = new Dish();

	private String errorMessage;
	
	@EJB
	private DishServiceBean dishService;

	public Dish getDish() {
		return dish;
	}
	
	
	public String persist(){
		// Action
		try{
			dish = dishService.create(getDish());
		}
		catch(EJBException e){
			errorMessage = "Dish not created: " + Util.getCausingMessage(e);
		}
		

		if(isError())
			return null;
		else
			return "/dish/details.xhtml?faces-redirect=true&id="
			+ dish.getId();
	}
	
	public boolean isError() {
		return errorMessage != null;
	}
	
	public String getErrorMessage() {
		return errorMessage != null ? errorMessage : "";
	}
}
		
