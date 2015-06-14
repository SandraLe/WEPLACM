package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import weplacm.ejb.DishServiceBean;
import weplacm.entity.Dish;
import de.wwu.pi.acse_group10.framework.web.Util;

@ManagedBean
@ViewScoped
public class DishDetails {

	@EJB
	private DishServiceBean dishService;

	private int id;
	private Dish dish;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		init();
	}
	
	public Dish getDish() {
		if (dish == null) {
			dish = dishService.getDish(id);
		}
		return dish;
	}
	
	public void init() {
		dish = null;
	}

	public void ensureInitialized() {
		try {
			if (getDish() != null) {
				// Success
				return;
			}
		} catch (EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}
public String submitMasterDataChanges() {
	dish = dishService.updateMasterData(dish);
	return toPage();
}

	private String toPage() {
		return "/dish/details.xhtml?faces-redirect=true&id=" + id;
	}

	public String removeDish() {
		dishService.remove(id);
		return "/dish/list.xhtml";
	}
}
