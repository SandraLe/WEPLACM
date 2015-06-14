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


import weplacm.entity.Customer;

@Stateless
public class CustomerServiceBean {
	@PersistenceContext
	private EntityManager em;
	
	public Customer create(Customer newCustomer){
		em.persist(newCustomer);
		return newCustomer;
	}
	
	public Collection<Customer> getAllCustomers(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> rootEntry = cq.from(Customer.class);
		return em.createQuery(cq.select(rootEntry)).getResultList();
	}
	
	public Customer getCustomer(long id) {
		return em.find(Customer.class, id);
	}
	
	public Customer updateMasterData(Customer customer){
		Customer attachedCustomer = getCustomer(customer.getId());
		
		if(attachedCustomer == null){
			throw new EJBException(new ConstraintViolationException("Customer with id " + customer.getId() +  " could not be found.", null));
     	}
     	attachedCustomer.setName(customer.getName());
     	attachedCustomer.setAddress(customer.getAddress());
     	attachedCustomer.setPhoneNumber(customer.getPhoneNumber());
		
		return customer;
	}
	
	public void remove(long id) {
		em.remove(getCustomer(id));
	}
	
	/*public Customer getCustomerWithFetchedReferences(long id) {
		Customer customer = getCustomer(id);
		forceLoadOfMandate(customer);
		return customer;
	}*/
	
	/*private void forceLoadOfMandate(Customer customer) {
		customer.getMandate().size();
	}*/
	
	
}
