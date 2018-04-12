package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name="user_account")
@NamedQuery(name="UserAccount.findAll", query="SELECT u FROM UserAccount u")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id", unique=true, nullable=false)
	private String userId;

	@Column(name="contact_no", length=255)
	private String contactNo;

	@Column(length=255)
	private String email;

	@Column(length=100)
	private String password;

	@Column(name="user_name", length=255)
	private String userName;

	//bi-directional many-to-one association to CheckoutOrder
	@OneToMany(mappedBy="userAccount", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<CheckoutOrder> checkoutOrders;

	//bi-directional many-to-one association to PasswordResetToken
	@OneToMany(mappedBy="userAccount", cascade=CascadeType.REMOVE)
	private List<PasswordResetToken> passwordResetTokens;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="userAccounts")
	private List<Role> roles;

	public UserAccount() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<CheckoutOrder> getCheckoutOrders() {
		return this.checkoutOrders;
	}

	public void setCheckoutOrders(List<CheckoutOrder> checkoutOrders) {
		this.checkoutOrders = checkoutOrders;
	}

	public CheckoutOrder addCheckoutOrder(CheckoutOrder checkoutOrder) {
		getCheckoutOrders().add(checkoutOrder);
		checkoutOrder.setUserAccount(this);

		return checkoutOrder;
	}

	public CheckoutOrder removeCheckoutOrder(CheckoutOrder checkoutOrder) {
		getCheckoutOrders().remove(checkoutOrder);
		checkoutOrder.setUserAccount(null);

		return checkoutOrder;
	}

	public List<PasswordResetToken> getPasswordResetTokens() {
		return this.passwordResetTokens;
	}

	public void setPasswordResetTokens(List<PasswordResetToken> passwordResetTokens) {
		this.passwordResetTokens = passwordResetTokens;
	}

	public PasswordResetToken addPasswordResetToken(PasswordResetToken passwordResetToken) {
		getPasswordResetTokens().add(passwordResetToken);
		passwordResetToken.setUserAccount(this);

		return passwordResetToken;
	}

	public PasswordResetToken removePasswordResetToken(PasswordResetToken passwordResetToken) {
		getPasswordResetTokens().remove(passwordResetToken);
		passwordResetToken.setUserAccount(null);

		return passwordResetToken;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}