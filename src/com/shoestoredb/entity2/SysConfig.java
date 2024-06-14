package com.shoestoredb.entity2;
// Generated Apr 19, 2024, 5:25:59 PM by Hibernate Tools 5.6.15.Final

import java.util.Date;

/**
 * SysConfig generated by hbm2java
 */
public class SysConfig implements java.io.Serializable {

	private String variable;
	private String value;
	private Date setTime;
	private String setBy;

	public SysConfig() {
	}

	public SysConfig(String variable) {
		this.variable = variable;
	}

	public SysConfig(String variable, String value, Date setTime, String setBy) {
		this.variable = variable;
		this.value = value;
		this.setTime = setTime;
		this.setBy = setBy;
	}

	public String getVariable() {
		return this.variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getSetTime() {
		return this.setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	public String getSetBy() {
		return this.setBy;
	}

	public void setSetBy(String setBy) {
		this.setBy = setBy;
	}

}