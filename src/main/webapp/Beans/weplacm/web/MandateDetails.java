package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import weplacm.ejb.MandateServiceBean;
import weplacm.entity.Mandate;
import weplacm.entity.CandidateRating;
import weplacm.entity.Application;
import weplacm.entity.Candidate;
import weplacm.ejb.CandidateServiceBean;
import weplacm.entity.Candidate;
import weplacm.ejb.CandidateServiceBean;
import framework.web.Util;

@ManagedBean
@ViewScoped
public class MandateDetails {

	@EJB
	private MandateServiceBean mandateService;
@EJB
private CandidateServiceBean candidateService;
@EJB
private CandidateServiceBean candidateService;

	private int id;
	private Mandate mandate;
private String errorMessage;
private int quantityForNewCandidateRating;
private Candidate candidateForNewCandidateRating;
private Candidate candidateForNewApplication;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		init();
	}
	
	public Mandate getMandate() {
		if (mandate == null) {
			mandate = mandateService.getMandateWithFetchedReferences(id);
		}
		return mandate;
	}
public int getQuantityForNewCandidateRating() {
	return quantityForNewCandidateRating;
}

public void setQuantityForNewCandidateRating(int quantity) {
	this.quantityForNewCandidateRating = quantity;
}
public Candidate getCandidateForNewCandidateRating() {
	return candidateForNewCandidateRating;
}

public void setCandidateForNewCandidateRating(Candidate candidate) {
	this.candidateForNewCandidateRating = candidate;
}
public Collection<Candidate> getAllCandidates() {
	return candidateService.getAllCandidates();
}

public Candidate getCandidateForNewApplication() {
	return candidateForNewApplication;
}

public void setCandidateForNewApplication(Candidate candidate) {
	this.candidateForNewApplication = candidate;
}
public Collection<Candidate> getAllCandidates() {
	return candidateService.getAllCandidates();
}

	
	public void init() {
		mandate = null;
	}

	public void ensureInitialized() {
		try {
			if (getMandate() != null) {
				// Success
				return;
			}
		} catch (EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}
public boolean isError() {
	return errorMessage != null;
}

public String getErrorMessage() {
	return errorMessage != null ? errorMessage : "";
}

private void resetError() {
	errorMessage = null;
}

public String submitMasterDataChanges() {
	mandate = mandateService.updateMasterData(mandate);
	return toPage();
}

	private String toPage() {
		return "/mandate/details.xhtml?faces-redirect=true&id=" + id;
	}

	public String removeMandate() {
		mandateService.remove(id);
		return "/mandate/list.xhtml";
	}
				public String addToCandidateRatings() {
	resetError();
	try  {
		mandateService.addToCandidateRatings(id, quantityForNewCandidateRating, candidateForNewCandidateRating);
	} catch (EJBException e) {
		errorMessage = "Candidate Ratings could not be added: " + Util.getCausingMessage(e);
	}
				
	if (isError()) return null;
	else return toPage();
				}
				public String removeFromCandidateRatings(CandidateRating candidateRating) {
	mandateService.removeFromCandidateRatings(candidateRating);
	return toPage();
				}
				public String addToHiringInformation() {
	resetError();
	try  {
		mandateService.addToHiringInformation(id, );
	} catch (EJBException e) {
		errorMessage = "Hiring Information could not be added: " + Util.getCausingMessage(e);
	}
				
	if (isError()) return null;
	else return toPage();
				}
				public String removeFromHiringInformation(HiringInformation hiringInformation) {
	mandateService.removeFromHiringInformation(hiringInformation);
	return toPage();
				}
				public String addToApplication() {
	resetError();
	try  {
		mandateService.addToApplication(id, candidateForNewApplication);
	} catch (EJBException e) {
		errorMessage = "Application could not be added: " + Util.getCausingMessage(e);
	}
				
	if (isError()) return null;
	else return toPage();
				}
				public String removeFromApplication(Application application) {
	mandateService.removeFromApplication(application);
	return toPage();
				}
				public String addToJobProfile() {
	resetError();
	try  {
		mandateService.addToJobProfile(id, );
	} catch (EJBException e) {
		errorMessage = "Job Profile could not be added: " + Util.getCausingMessage(e);
	}
				
	if (isError()) return null;
	else return toPage();
				}
				public String removeFromJobProfile(JobProfile jobProfile) {
	mandateService.removeFromJobProfile(jobProfile);
	return toPage();
				}
}
