package weplacm.web;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import weplacm.ejb.DishServiceBean;

import weplacm.entity.Dish;

@ManagedBean
public class DishList {

	@EJB
	private DishServiceBean dishService;
	
	private Collection<Dish> dishs;

	public Collection<Dish> getAll() {
		if (dishs == null)
			dishs = dishService.getAllDishs();
		return dishs;
	}
}
