package weplacm.entity;

import de.wwu.pi.acse_group10.framework.data.AbstractEntity;
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
public class DeliveryOrder extends AbstractEntity {
	
@NotNull
protected Date received;
	
@ManyToOne
@NotNull
protected Customer customer;

@OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL
)
protected Collection<OrderLine> orderLines = new ArrayList<OrderLine>();


	//Default Constructor
	public DeliveryOrder() {
		super();
	}
	
public Date getReceived() {
	return received;
};

public void setReceived(Date received) {
	this.received = received;
};

	
@ManyToOne
public Customer getCustomer() {
	return customer;
};

public void setCustomer(Customer customer) {
	this.customer = customer;
};

public Collection<OrderLine> getOrderLines() {
	return orderLines;
};

public void addToOrderLines(OrderLine value) {
	orderLines.add(value);
};
	
	@Override
	public String toString() {
		return "DeliveryOrder" + getId();
	}
}
