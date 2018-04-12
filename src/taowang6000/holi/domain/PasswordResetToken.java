package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the password_reset_token database table.
 * 
 */
@Entity
@Table(name="password_reset_token")
@NamedQuery(name="PasswordResetToken.findAll", query="SELECT p FROM PasswordResetToken p")
public class PasswordResetToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiry_date")
	private Date expiryDate;

	@Column(length=255)
	private String token;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="user_id", nullable=false)
	private UserAccount userAccount;

	public PasswordResetToken() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}