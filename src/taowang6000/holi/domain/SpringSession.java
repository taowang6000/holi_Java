package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the spring_session database table.
 * 
 */
@Entity
@Table(name="spring_session")
@NamedQuery(name="SpringSession.findAll", query="SELECT s FROM SpringSession s")
public class SpringSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SESSION_ID", unique=true, nullable=false, length=36)
	private String sessionId;

	@Column(name="CREATION_TIME", nullable=false)
	private BigInteger creationTime;

	@Column(name="LAST_ACCESS_TIME", nullable=false)
	private BigInteger lastAccessTime;

	@Column(name="MAX_INACTIVE_INTERVAL", nullable=false)
	private int maxInactiveInterval;

	@Column(name="PRINCIPAL_NAME", length=100)
	private String principalName;

	//bi-directional many-to-one association to SpringSessionAttribute
	@OneToMany(mappedBy="springSession", cascade=CascadeType.REMOVE)
	private List<SpringSessionAttribute> springSessionAttributes;

	public SpringSession() {
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public BigInteger getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(BigInteger creationTime) {
		this.creationTime = creationTime;
	}

	public BigInteger getLastAccessTime() {
		return this.lastAccessTime;
	}

	public void setLastAccessTime(BigInteger lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}

	public String getPrincipalName() {
		return this.principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public List<SpringSessionAttribute> getSpringSessionAttributes() {
		return this.springSessionAttributes;
	}

	public void setSpringSessionAttributes(List<SpringSessionAttribute> springSessionAttributes) {
		this.springSessionAttributes = springSessionAttributes;
	}

	public SpringSessionAttribute addSpringSessionAttribute(SpringSessionAttribute springSessionAttribute) {
		getSpringSessionAttributes().add(springSessionAttribute);
		springSessionAttribute.setSpringSession(this);

		return springSessionAttribute;
	}

	public SpringSessionAttribute removeSpringSessionAttribute(SpringSessionAttribute springSessionAttribute) {
		getSpringSessionAttributes().remove(springSessionAttribute);
		springSessionAttribute.setSpringSession(null);

		return springSessionAttribute;
	}

}