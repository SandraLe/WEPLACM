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


import weplacm.entity.Client;

@Stateless
public class ClientServiceBean {
	@PersistenceContext
	private EntityManager em;
	
	public Client create(Client newClient){
		em.persist(newClient);
		return newClient;
	}
	
	public Collection<Client> getAllClients(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> rootEntry = cq.from(Client.class);
		return em.createQuery(cq.select(rootEntry)).getResultList();
	}
	
	public Client getClient(long id) {
		return em.find(Client.class, id);
	}
	
	public Client updateMasterData(Client client){
		Client attachedClient = getClient(client.getId());
		
		if(attachedClient == null){
			throw new EJBException(new ConstraintViolationException("Client with id " + client.getId() +  " could not be found.", null));
     	}
     	attachedClient.setName(client.getName());
     	attachedClient.setAddress(client.getAddress());
     	attachedClient.setPhoneNumber(client.getPhoneNumber());
		
		return client;
	}
	
	public void remove(long id) {
		em.remove(getClient(id));
	}
	
	public Client getClientWithFetchedReferences(long id) {
		Client client = getClient(id);
		forceLoadOfMandate(client);
		return client;
	}
	
	private void forceLoadOfMandate(Client client) {
		client.getMandate().size();
	}
	
	
}
