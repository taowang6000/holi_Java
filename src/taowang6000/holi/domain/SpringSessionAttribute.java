package taowang6000.holi.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the spring_session_attributes database table.
 * 
 */
@Entity
@Table(name = "spring_session_attributes")
@NamedQuery(name = "SpringSessionAttribute.findAll", query = "SELECT s FROM SpringSessionAttribute s")
public class SpringSessionAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpringSessionAttributePK id;

	@Lob
	@Column(name = "ATTRIBUTE_BYTES")
	private byte[] attributeBytes;

	// bi-directional many-to-one association to SpringSession
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "SESSION_ID", nullable = false, insertable = false, updatable = false)
	private SpringSession springSession;

	/*
	 * you have the tableA_ID column in two separate mappings, once as part of
	 * a @ManyToOne and again as a field (perhaps as part of an @EmbeddedId). JPA
	 * only lets you manage the underlying column through a single writeable
	 * mapping.
	 * 
	 * If you want both mappings, you have to pick which one you'll use to write,
	 * and designate the other as read-only with insertable=false, updatable=false.
	 * 
	 * For example:
	 * 
	 * @Entity pubilc class TableC {
	 * 
	 * // read-write mapping to manage via relationship
	 * 
	 * @ManyToOne(column="tableA_ID") private TableA tableA;
	 * 
	 * // read-only convenience method to get ID directly w/o navigating
	 * relationship
	 * 
	 * @Column(name="tableA_ID", insertable=false, updatable=false) private Long
	 * tableA_ID; }
	 */
	//next two lines were added afterwards:
	@Column(name = "SESSION_ID")
	private String sessionId;

	public SpringSessionAttribute() {
	}

	public SpringSessionAttributePK getId() {
		return this.id;
	}

	public void setId(SpringSessionAttributePK id) {
		this.id = id;
	}

	public byte[] getAttributeBytes() {
		return this.attributeBytes;
	}

	public void setAttributeBytes(byte[] attributeBytes) {
		this.attributeBytes = attributeBytes;
	}

	public SpringSession getSpringSession() {
		return this.springSession;
	}

	public void setSpringSession(SpringSession springSession) {
		this.springSession = springSession;
	}

}