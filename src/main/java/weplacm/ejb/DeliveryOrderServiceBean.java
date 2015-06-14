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


import weplacm.entity.DeliveryOrder;
import weplacm.entity.OrderLine;
import weplacm.entity.Dish;

@Stateless
public class DeliveryOrderServiceBean {
	@PersistenceContext
	private EntityManager em;
	
	public DeliveryOrder create(DeliveryOrder newDeliveryOrder){
		em.persist(newDeliveryOrder);
		return newDeliveryOrder;
	}
	
	public Collection<DeliveryOrder> getAllDeliveryOrders(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DeliveryOrder> cq = cb.createQuery(DeliveryOrder.class);
		Root<DeliveryOrder> rootEntry = cq.from(DeliveryOrder.class);
		return em.createQuery(cq.select(rootEntry)).getResultList();
	}
	
	public DeliveryOrder getDeliveryOrder(long id) {
		return em.find(DeliveryOrder.class, id);
	}
	
	public DeliveryOrder updateMasterData(DeliveryOrder deliveryOrder){
		DeliveryOrder attachedDeliveryOrder = getDeliveryOrder(deliveryOrder.getId());
		
		if(attachedDeliveryOrder == null){
			throw new EJBException(new ConstraintViolationException("DeliveryOrder with id " + deliveryOrder.getId() +  " could not be found.", null));
     	}
     	attachedDeliveryOrder.setReceived(deliveryOrder.getReceived());
		
		return deliveryOrder;
	}
	
	public void remove(long id) {
		em.remove(getDeliveryOrder(id));
	}
	
	public DeliveryOrder getDeliveryOrderWithFetchedReferences(long id) {
		DeliveryOrder deliveryOrder = getDeliveryOrder(id);
		forceLoadOfOrderLines(deliveryOrder);
		return deliveryOrder;
	}
	
	private void forceLoadOfOrderLines(DeliveryOrder deliveryOrder) {
		deliveryOrder.getOrderLines().size();
	}
	
public void addToOrderLines(long id, int quantity, Dish dish) {
	DeliveryOrder deliveryOrder = getDeliveryOrder(id);
	OrderLine orderLine = new OrderLine();
	orderLine.setQuantity(quantity);
	orderLine.setDeliveryOrder(deliveryOrder);
	orderLine.setDish(dish);
	em.persist(orderLine);
}

public void removeFromOrderLines(OrderLine detachedOrderLine) {
	OrderLine orderLine = em.merge(detachedOrderLine);
	DeliveryOrder deliveryOrder = orderLine.getDeliveryOrder();
	deliveryOrder.getOrderLines().remove(orderLine);
	em.remove(orderLine);
}
	
}
