package weplacm.ejb;

import java.util.Collection;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ejb.EJBException;
import javax.validation.ConstraintViolationException;


import weplacm.entity.Dish;

@Stateless
public class DishServiceBean {
	@PersistenceContext
	private EntityManager em;
	
	public Dish create(Dish newDish){
		em.persist(newDish);
		return newDish;
	}
	
	public Collection<Dish> getAllDishs(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dish> cq = cb.createQuery(Dish.class);
		Root<Dish> rootEntry = cq.from(Dish.class);
		return em.createQuery(cq.select(rootEntry)).getResultList();
	}
	
	public Dish getDish(long id) {
		return em.find(Dish.class, id);
	}
	
	public Dish updateMasterData(Dish dish){
		Dish attachedDish = getDish(dish.getId());
		
		if(attachedDish == null){
			throw new EJBException(new ConstraintViolationException("Dish with id " + dish.getId() +  " could not be found.", null));
     	}
     	attachedDish.setName(dish.getName());
     	attachedDish.setDescription(dish.getDescription());
     	attachedDish.setPrice(dish.getPrice());
		
		return dish;
	}
	
	public void remove(long id) {
		em.remove(getDish(id));
	}
	
	public Dish getDishWithFetchedReferences(long id) {
		Dish dish = getDish(id);
		return dish;
	}
	
	
	
}
