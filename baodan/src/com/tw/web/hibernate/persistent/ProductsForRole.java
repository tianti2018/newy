package com.tw.web.hibernate.persistent;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="role_products")
public class ProductsForRole implements Serializable {


	
	private int RPId;
	private int roleId;
	private int productsId;
	
	private Role role;
	private Products products;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getRPId() {
		return RPId;
	}

	public void setRPId(int rPId) {
		RPId = rPId;
	}
	@Column(name="roleId", insertable = false, updatable = false)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="productsId", insertable = false, updatable = false)
	public int getProductsId() {
		return productsId;
	}

	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}
	

	public void setRole(Role role) {
		this.role = role;
	}

	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="productsId")	
	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
		

}
