package org.assignment.estr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tientt - Entity of table "work"
 */
@Entity
@Table(name = "work")
@Getter
@Setter
public class WorkEntity extends AuditEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "starting_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startingDate;

	@Column(name = "ending_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endingDate;

	@Column(name = "status")
	private String status;

	public WorkEntity(Long id, String name, Date startingDate, Date endingDate, String status) {
		super();
		this.id = id;
		this.name = name;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.status = status;
	}

	public WorkEntity() {
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startingDate
	 */
	public Date getStartingDate() {
		return startingDate;
	}

	/**
	 * @param startingDate
	 *            the startingDate to set
	 */
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	/**
	 * @return the endingDate
	 */
	public Date getEndingDate() {
		return endingDate;
	}

	/**
	 * @param endingDate
	 *            the endingDate to set
	 */
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
