package com.whck.dmo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	
	private String emai;
	
	private String username;

	@Column(name = "activate_code")
	private String activateCode;

	private String address;

	private String cname;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cncl_date")
	private Date cnclDate;

	@Column(name = "is_admin")
	private Integer isAdmin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logon_date")
	private Date logonDate;

	private String name;

	@JsonIgnore
	private String password;

	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date")
	private Date regDate;

	private String remarks;

	private Integer state;

	public User() {
	}

	public String getActivateCode() {
		return this.activateCode;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCname() {
		return this.cname;
	}

	public Date getCnclDate() {
		return this.cnclDate;
	}

	public String getEmai() {
		return emai;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public Date getLogonDate() {
		return this.logonDate;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPhone() {
		return this.phone;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public Integer getState() {
		return this.state;
	}

	public String getUsername() {
		return this.username;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public void setCnclDate(Date cnclDate) {
		this.cnclDate = cnclDate;
	}

	public void setEmai(String emai) {
		this.emai = emai;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setLogonDate(Date logonDate) {
		this.logonDate = logonDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}