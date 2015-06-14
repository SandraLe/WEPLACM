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


import weplacm.entity.Candidate;

@Stateless
public class CandidateServiceBean {
	@PersistenceContext
	private EntityManager em;
	
	public Candidate create(Candidate newCandidate){
		em.persist(newCandidate);
		return newCandidate;
	}
	
	public Collection<Candidate> getAllCandidates(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Candidate> cq = cb.createQuery(Candidate.class);
		Root<Candidate> rootEntry = cq.from(Candidate.class);
		return em.createQuery(cq.select(rootEntry)).getResultList();
	}
	
	public Candidate getCandidate(long id) {
		return em.find(Candidate.class, id);
	}
	
	public Candidate updateMasterData(Candidate candidate){
		Candidate attachedCandidate = getCandidate(candidate.getId());
		
		if(attachedCandidate == null){
			throw new EJBException(new ConstraintViolationException("Candidate with id " + candidate.getId() +  " could not be found.", null));
     	}
     	attachedCandidate.setName(candidate.getName());
     	attachedCandidate.setDescription(candidate.getDescription());
     	attachedCandidate.setPrice(candidate.getPrice());
		
		return candidate;
	}
	
	public void remove(long id) {
		em.remove(getCandidate(id));
	}
	
	public Candidate getCandidateWithFetchedReferences(long id) {
		Candidate candidate = getCandidate(id);
		return candidate;
	}
	
	
	
}
