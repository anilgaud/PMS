package com.kgate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Holiday")

public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int daysId;

	@Column
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	@Temporal(TemporalType.DATE)
	private Date hDays;

	public int getDaysId() {
		return daysId;
	}

	public void setDaysId(int daysId) {
		this.daysId = daysId;
	}

	public Date gethDays() {
		return hDays;
	}

	public void sethDays(Date hDays) {
		this.hDays = hDays;
	}

}
