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
public class Dish extends AbstractEntity {
	
@NotNull
protected String name;
@NotNull
protected String description;
@NotNull
protected int price;
	

	//Default Constructor
	public Dish() {
		super();
	}
	
public String getName() {
	return name;
};

public void setName(String name) {
	this.name = name;
};

public String getDescription() {
	return description;
};

public void setDescription(String description) {
	this.description = description;
};

public int getPrice() {
	return price;
};

public void setPrice(int price) {
	this.price = price;
};

	
	
	@Override
	public String toString() {
		return "Dish" + getId();
	}
}
