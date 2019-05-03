package com.yousoff.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "USER")
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Access(AccessType.PROPERTY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name; 
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="EMAIL")
	private String email;
	
	@JoinColumn(name="USER_ID")
	@OneToMany(fetch = FetchType.LAZY)
	@OrderBy("id")
	@ToString.Exclude
	private List<Item> items;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="ENABLED")
	private char enabled;
	
}
