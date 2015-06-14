package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import weplacm.ejb.CandidateServiceBean;
import weplacm.entity.Candidate;
import framework.web.Util;

@ManagedBean
@ViewScoped
public class CandidateDetails {

	@EJB
	private CandidateServiceBean candidateService;

	private int id;
	private Candidate candidate;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		init();
	}
	
	public Candidate getCandidate() {
		if (candidate == null) {
			candidate = candidateService.getCandidate(id);
		}
		return candidate;
	}
	
	public void init() {
		candidate = null;
	}

	public void ensureInitialized() {
		try {
			if (getCandidate() != null) {
				// Success
				return;
			}
		} catch (EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}
public String submitMasterDataChanges() {
	candidate = candidateService.updateMasterData(candidate);
	return toPage();
}

	private String toPage() {
		return "/candidate/details.xhtml?faces-redirect=true&id=" + id;
	}

	public String removeCandidate() {
		candidateService.remove(id);
		return "/candidate/list.xhtml";
	}
}
