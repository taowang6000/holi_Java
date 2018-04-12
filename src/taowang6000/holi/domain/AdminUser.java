package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the ADMIN_USER database table.
 * 
 */
@Entity
@Table(name="ADMIN_USER")
@NamedQuery(name="AdminUser.findAll", query="SELECT a FROM AdminUser a")
public class AdminUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_NAME", unique=true, nullable=false, length=100)
	private String userName;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false, length=100)
	private String password;

	@Column(nullable=false)
	private Timestamp ts;

	public AdminUser() {
	}

	public AdminUser(String username, String email, String password) {
		super();
		this.userName = username;
		this.email = email;
		this.password = password;
		
		java.util.Date now = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
		this.ts = timestamp;	
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Timestamp getTs() {
		return this.ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

}