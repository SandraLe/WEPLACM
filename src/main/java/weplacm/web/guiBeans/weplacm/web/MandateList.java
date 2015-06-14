package weplacm.web;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import weplacm.ejb.MandateServiceBean;

import weplacm.entity.Mandate;

@ManagedBean
public class MandateList {

	@EJB
	private MandateServiceBean mandateService;
	
	private Collection<Mandate> mandates;

	public Collection<Mandate> getAll() {
		if (mandates == null)
			mandates = mandateService.getAllMandates();
		return mandates;
	}
}
