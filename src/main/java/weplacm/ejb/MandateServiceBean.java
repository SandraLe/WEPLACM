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


import weplacm.entity.Mandate;
import weplacm.entity.CandidateRating;
import weplacm.entity.Candidate;
import weplacm.entity.HiringInformation;
import weplacm.entity.Application;
import weplacm.entity.Candidate;
import weplacm.entity.JobProfile;

@Stateless
public class MandateServiceBean {
	@PersistenceContext
	private EntityManager em;
	
	public Mandate create(Mandate newMandate){
		em.persist(newMandate);
		return newMandate;
	}
	
	public Collection<Mandate> getAllMandates(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mandate> cq = cb.createQuery(Mandate.class);
		Root<Mandate> rootEntry = cq.from(Mandate.class);
		return em.createQuery(cq.select(rootEntry)).getResultList();
	}
	
	public Mandate getMandate(long id) {
		return em.find(Mandate.class, id);
	}
	
	public Mandate updateMasterData(Mandate mandate){
		Mandate attachedMandate = getMandate(mandate.getId());
		
		if(attachedMandate == null){
			throw new EJBException(new ConstraintViolationException("Mandate with id " + mandate.getId() +  " could not be found.", null));
     	}
     	attachedMandate.setReceived(mandate.getReceived());
		
		return mandate;
	}
	
	public void remove(long id) {
		em.remove(getMandate(id));
	}
	
	public Mandate getMandateWithFetchedReferences(long id) {
		Mandate mandate = getMandate(id);
		forceLoadOfCandidateRatings(mandate);
		forceLoadOfApplication(mandate);
		return mandate;
	}
	
	private void forceLoadOfCandidateRatings(Mandate mandate) {
		mandate.getCandidateRatings().size();
	}
	private void forceLoadOfApplication(Mandate mandate) {
		mandate.getApplication().size();
	}
	
public void addToCandidateRatings(long id, int quantity, Candidate candidate) {
	Mandate mandate = getMandate(id);
	CandidateRating candidateRating = new CandidateRating();
	candidateRating.setQuantity(quantity);
	candidateRating.setMandate(mandate);
	candidateRating.setCandidate(candidate);
	em.persist(candidateRating);
}

public void removeFromCandidateRatings(CandidateRating detachedCandidateRating) {
	CandidateRating candidateRating = em.merge(detachedCandidateRating);
	Mandate mandate = candidateRating.getMandate();
	mandate.getCandidateRatings().remove(candidateRating);
	em.remove(candidateRating);
}
public void addToHiringInformation(long id) {
	Mandate mandate = getMandate(id);
	HiringInformation hiringInformation = new HiringInformation();
	hiringInformation.setMandate(mandate);
	em.persist(hiringInformation);
}

public void removeFromHiringInformation(HiringInformation detachedHiringInformation) {
	HiringInformation hiringInformation = em.merge(detachedHiringInformation);
	Mandate mandate = hiringInformation.getMandate();
	mandate.setHiringInformation(null);
	em.remove(hiringInformation);
}
public void addToApplication(long id, Candidate candidate) {
	Mandate mandate = getMandate(id);
	Application application = new Application();
	application.setMandate(mandate);
	application.setCandidate(candidate);
	em.persist(application);
}

public void removeFromApplication(Application detachedApplication) {
	Application application = em.merge(detachedApplication);
	Mandate mandate = application.getMandate();
	mandate.getApplication().remove(application);
	em.remove(application);
}
public void addToJobProfile(long id) {
	Mandate mandate = getMandate(id);
	JobProfile jobProfile = new JobProfile();
	jobProfile.setMandate(mandate);
	em.persist(jobProfile);
}

public void removeFromJobProfile(JobProfile detachedJobProfile) {
	JobProfile jobProfile = em.merge(detachedJobProfile);
	Mandate mandate = jobProfile.getMandate();
	mandate.setJobProfile(null);
	em.remove(jobProfile);
}
	
}
