package org.assignment.estr.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.assignment.estr.constant.Status;
import org.assignment.estr.validator.CheckDateFormat;
import org.assignment.estr.validator.EnumFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author tientt - WorkForm
 */
public class WorkForm implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty
	private String name;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CheckDateFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String startingDate;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CheckDateFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String endingDate;

	@NotEmpty
	@EnumFormat(enumClazz = Status.class)
	private String status;

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
	public String getStartingDate() {
		return startingDate;
	}

	/**
	 * @param startingDate
	 *            the startingDate to set
	 */
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	/**
	 * @return the endingDate
	 */
	public String getEndingDate() {
		return endingDate;
	}

	/**
	 * @param endingDate
	 *            the endingDate to set
	 */
	public void setEndingDate(String endingDate) {
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

	public WorkForm(){};

	public WorkForm(Long id, @NotEmpty String name, @NotNull String startingDate, @NotNull String endingDate,
			@NotEmpty String status) {
		super();
		this.id = id;
		this.name = name;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.status = status;
	}
}
