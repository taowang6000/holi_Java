package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name="item")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private String itemid;

	@Column(length=255)
	private String city;

	@Column(length=255)
	private String country;

	@Column(name="option_value")
	private int optionValue;

	private double price;

	@Column(length=255)
	private String state;

	@Column(length=255)
	private String year;

	//bi-directional many-to-one association to CheckoutOrder
	@ManyToOne//(cascade = {CascadeType.MERGE})
	@JoinColumn(name="order_id")
	private CheckoutOrder checkoutOrder;

	public Item() {
	}

	public String getItemid() {
		return this.itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getOptionValue() {
		return this.optionValue;
	}

	public void setOptionValue(int optionValue) {
		this.optionValue = optionValue;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public CheckoutOrder getCheckoutOrder() {
		return this.checkoutOrder;
	}

	public void setCheckoutOrder(CheckoutOrder checkoutOrder) {
		this.checkoutOrder = checkoutOrder;
	}

}