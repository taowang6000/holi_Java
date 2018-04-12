package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the checkout_order database table.
 * 
 */
@Entity
@Table(name="checkout_order")
@NamedQueries (value = {
	@NamedQuery(name="CheckoutOrder.findAll", query="SELECT c FROM CheckoutOrder c"),
	@NamedQuery(name="CheckoutOrder.join_1", query="select c.orderId, c.dateTime, u.userName "
			+ "from CheckoutOrder c join c.userAccount u")
})
@NamedQuery(name="CheckoutOrder.findAll", query="SELECT c FROM CheckoutOrder c")
public class CheckoutOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id", unique=true, nullable=false)
	private String orderId;

	@Column(name="date_time", length=255)
	private String dateTime;

	private double total;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne//(cascade = CascadeType.REFRESH)
	@JoinColumn(name="user_id")
	private UserAccount userAccount;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="checkoutOrder", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Item> items;

	public CheckoutOrder() {
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setCheckoutOrder(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCheckoutOrder(null);

		return item;
	}

}