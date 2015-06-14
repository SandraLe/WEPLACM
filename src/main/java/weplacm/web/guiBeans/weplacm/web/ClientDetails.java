package weplacm.web;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import weplacm.ejb.ClientServiceBean;
import weplacm.entity.Client;
import framework.web.Util;

@ManagedBean
@ViewScoped
public class ClientDetails {

	@EJB
	private ClientServiceBean clientService;

	private int id;
	private Client client;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		init();
	}
	
	public Client getClient() {
		if (client == null) {
			client = clientService.getClientWithFetchedReferences(id);
		}
		return client;
	}
	
	public void init() {
		client = null;
	}

	public void ensureInitialized() {
		try {
			if (getClient() != null) {
				// Success
				return;
			}
		} catch (EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}
public String submitMasterDataChanges() {
	client = clientService.updateMasterData(client);
	return toPage();
}

	private String toPage() {
		return "/client/details.xhtml?faces-redirect=true&id=" + id;
	}

	public String removeClient() {
		clientService.remove(id);
		return "/client/list.xhtml";
	}
}
