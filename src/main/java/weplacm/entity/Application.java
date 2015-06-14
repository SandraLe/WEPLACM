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
public class Application extends AbstractEntity {
	
	
@ManyToOne
@NotNull
protected Mandate mandate;

@ManyToOne
protected Candidate candidate;


	//Default Constructor
	public Application() {
		super();
	}
	
	
@ManyToOne
public Mandate getMandate() {
	return mandate;
};

public void setMandate(Mandate mandate) {
	this.mandate = mandate;
};

@ManyToOne
public Candidate getCandidate() {
	return candidate;
};

public void setCandidate(Candidate candidate) {
	this.candidate = candidate;
};

	
	@Override
	public String toString() {
		return "Application" + getId();
	}
}
