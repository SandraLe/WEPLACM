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
public class Client extends AbstractEntity {
	
@NotNull
protected String name;
@NotNull
protected String address;
@NotNull
protected String phoneNumber;
	
@OneToMany(mappedBy = "client")
protected Collection<Mandate> mandate = new ArrayList<Mandate>();


	//Default Constructor
	public Client() {
		super();
	}
	
public String getName() {
	return name;
};

public void setName(String name) {
	this.name = name;
};

public String getAddress() {
	return address;
};

public void setAddress(String address) {
	this.address = address;
};

public String getPhoneNumber() {
	return phoneNumber;
};

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
};

	
public Collection<Mandate> getMandate() {
	return mandate;
};

public void addToMandate(Mandate value) {
	mandate.add(value);
};
	
	@Override
	public String toString() {
		return "Client" + getId();
	}
}
