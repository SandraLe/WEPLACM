package weplacm.entity;

import framework.data.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
public class Mandate extends AbstractEntity {
	
@NotNull
protected Date received;
	
@ManyToOne
@NotNull
protected Client client;

@OneToMany(mappedBy = "mandate", cascade = CascadeType.ALL
)
protected Collection<CandidateRating> candidateRatings = new ArrayList<CandidateRating>();

@ManyToOne
@NotNull
protected HiringInformation hiringInformation;

@OneToMany(mappedBy = "mandate", cascade = CascadeType.ALL
)
protected Collection<Application> application = new ArrayList<Application>();

@ManyToOne
@NotNull
protected JobProfile jobProfile;


	//Default Constructor
	public Mandate() {
		super();
	}
	
public Date getReceived() {
	return received;
};

public void setReceived(Date received) {
	this.received = received;
};

	
@ManyToOne
public Client getClient() {
	return client;
};

public void setClient(Client client) {
	this.client = client;
};

public Collection<CandidateRating> getCandidateRatings() {
	return candidateRatings;
};

public void addToCandidateRatings(CandidateRating value) {
	candidateRatings.add(value);
};
@ManyToOne
public HiringInformation getHiringInformation() {
	return hiringInformation;
};

public void setHiringInformation(HiringInformation hiringInformation) {
	this.hiringInformation = hiringInformation;
};

public Collection<Application> getApplication() {
	return application;
};

public void addToApplication(Application value) {
	application.add(value);
};
@ManyToOne
public JobProfile getJobProfile() {
	return jobProfile;
};

public void setJobProfile(JobProfile jobProfile) {
	this.jobProfile = jobProfile;
};

	
	@Override
	public String toString() {
		return "Mandate" + getId();
	}
}
