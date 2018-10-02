package com.charvikent.issuetracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String empId;
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String contactno;
	@Column
	private String whatsappno;	
	@Column
	private String email;
	@Column
	private String cemail;
	private String paddress;
	private String caddress;
	private String dob;
	private String designation;
	private String files;
	@Column
	private String enabled;
	@Column(unique = true)
	private String username;
	@Column
	private String password;
	@Transient
	private String npassword;
	@Transient
	private String cpassword;
	@Transient
	private String status;
	@Transient
	private String designationname;
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;
	
	public User() 
	{
		
	}	
	
	public User(User user) 
	{
		super();
		this.id = user.id;
		this.createdTime = user.createdTime;
		this.updatedTime = user.updatedTime;
		this.contactno = user.contactno;
		this.whatsappno = user.whatsappno;
		this.firstname = user.firstname;
		this.email = user.email;
		this.cemail = user.cemail;
		this.enabled = user.enabled;
		this.designation = user.designation;
		this.username = user.username;
		this.password = user.password;
		this.npassword = user.npassword;
		this.cpassword = user.cpassword;
		this.lastname = user.lastname;
		this.status = user.status;
		this.empId = user.empId;
		this.paddress = user.paddress;
		this.caddress = user.caddress;
		this.dob = user.dob;
		this.files=user.files;
		this.designationname=user.designationname;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Date getCreatedTime() 
	{
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) 
	{
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() 
	{
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) 
	{
		this.updatedTime = updatedTime;
	}

	public String getContactno() 
	{
		return contactno;
	}

	public void setContactno(String contactno) 
	{
		this.contactno = contactno;
	}

	public String getWhatsappno() 
	{
		return whatsappno;
	}

	public void setWhatsappno(String whatsappno) 
	{
		this.whatsappno = whatsappno;
	}

	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getCemail() 
	{
		return cemail;
	}

	public void setCemail(String cemail) 
	{
		this.cemail = cemail;
	}

	public String getEnabled() 
	{
		return enabled;
	}

	public void setEnabled(String enabled) 
	{
		this.enabled = enabled;
	}

	public String getDesignation() 
	{
		return designation;
	}

	public void setDesignation(String designation) 
	{
		this.designation = designation;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getNpassword() 
	{
		return npassword;
	}

	public void setNpassword(String npassword) 
	{
		this.npassword = npassword;
	}

	public String getCpassword() 
	{
		return cpassword;
	}

	public void setCpassword(String cpassword) 
	{
		this.cpassword = cpassword;
	}

	public String getLastname() 
	{
		return lastname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getEmpId() 
	{
		return empId;
	}

	public void setEmpId(String empId) 
	{
		this.empId = empId;
	}
	
	public String getPaddress() 
	{
		return paddress;
	}

	public void setPaddress(String paddress) 
	{
		this.paddress = paddress;
	}

	public String getCaddress() 
	{
		return caddress;
	}

	public void setCaddress(String caddress) 
	{
		this.caddress = caddress;
	}

	public String getDob() 
	{
		return dob;
	}

	public void setDob(String dob) 
	{
		this.dob = dob;
	}
	
	
	public String getFiles() 
	{
		return files;
	}

	public void setFiles(String files) 
	{
		this.files = files;
	}

	public String getDesignationname() {
		return designationname;
	}

	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", empId=" + empId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", contactno=" + contactno + ", whatsappno=" + whatsappno + ", email=" + email + ", cemail=" + cemail
				+ ", paddress=" + paddress + ", caddress=" + caddress + ", dob=" + dob + ", designation=" + designation
				+ ", files=" + files + ", enabled=" + enabled + ", username=" + username + ", password=" + password
				+ ", npassword=" + npassword + ", cpassword=" + cpassword + ", status=" + status + ", designationname="
				+ designationname + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

	

	

	

}