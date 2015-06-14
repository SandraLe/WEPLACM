package workflow_rescources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MandateEntity implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue
	protected Long id; 
	
	@Version
	protected Long version; 
	
	protected Long job_profile_id; 
	protected Long candidate_list_id; 
	
	public Long getJob_profile_id() {
		return job_profile_id;
	}
	public void setJob_profile_id(Long job_profile_id) {
		this.job_profile_id = job_profile_id;
	}
	public Long getCandidate_list_id() {
		return candidate_list_id;
	}
	public void setCandidate_list_id(Long candidate_list_id) {
		this.candidate_list_id = candidate_list_id;
	}
	
	

}
